package com.kovalevich.banksystembgpb.services.account.abstraction

import com.kovalevich.banksystembgpb.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface CloseBankDayService extends LoggerService {

    def closeBankDay(args)

}
