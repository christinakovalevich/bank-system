package com.kovalevich.banksystembgpb.services.account.implementation

import com.kovalevich.banksystembgpb.config.CalendarConfig
import com.kovalevich.banksystembgpb.services.account.abstraction.AccountService
import com.kovalevich.banksystembgpb.services.account.abstraction.CloseBankDayService
import com.kovalevich.banksystembgpb.services.account.abstraction.ContractService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CloseBankDayServiceImpl implements CloseBankDayService {

    @Autowired
    ContractService contractService

    @Autowired
    AccountService accountService

    @Override
    def closeBankDay(def args) {

        def contracts = contractService.findAll()
        def currentDate = new Date()

        for (contract in contracts) {

            def dateOfIssue = contract.dateOfIssue
            def percentage = contract.percentage

            if (currentDate.time > dateOfIssue.time) {
                def diff = currentDate.time - dateOfIssue.time

                long diffDays = getPassedDays(diff)

                log.info('Time in days: ' + diffDays  + ' days.')

                def currentValue = contract.current_account.value

                def todayPercentValue = new BigDecimal(BigDecimal.ZERO)

                if (diffDays > 0) {
                    def yearPercentValue = new BigDecimal((currentValue / 100) * percentage)
                    todayPercentValue = (yearPercentValue / CalendarConfig.DAYS_IN_YEAR) * diffDays
                }

                accountService.changeValue(contract.percent_account, todayPercentValue)

                log.info('percentValue: ' + contract.percent_account.value)
            }
        }

        return null
    }

    static long getPassedDays(long diff) {
        return diff /
                (CalendarConfig.HOURS_IN_DAY *
                        CalendarConfig.MINUTES_IN_HOUR *
                        CalendarConfig.SECONDS_IN_MINUTE *
                        CalendarConfig.MILLISECONDS_IN_SECOND)
    }

}
