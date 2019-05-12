package com.kovalevich.banksystembgpb.services.account.abstraction

import com.kovalevich.banksystembgpb.models.account.Account
import com.kovalevich.banksystembgpb.models.account.AccountType
import com.kovalevich.banksystembgpb.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface AccountService extends LoggerService {

    Account findById(long id)

    List<Account> findAll()

    List<Account> findAllByCurrency(Currency currency)

    List<Account> findAllByAccountType(AccountType currency)

    Account save(Account account)

    Account update(long id, Account account)

    def delete(long id)

    def changeValue(Account account, BigDecimal value)

}
