package com.kovalevich.banksystembgpb.services.account.abstraction

import com.kovalevich.banksystembgpb.models.account.AccountType
import com.kovalevich.banksystembgpb.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface AccountTypeService extends LoggerService {

    List<AccountType> findAll()

    AccountType findById(long id)

    AccountType findByName(String name)

    AccountType save(AccountType accountType)

    AccountType update(long id, AccountType accountType)

    def delete(long id)

}