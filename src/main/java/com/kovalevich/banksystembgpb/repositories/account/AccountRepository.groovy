package com.kovalevich.banksystembgpb.repositories.account

import com.kovalevich.banksystembgpb.models.account.Account
import com.kovalevich.banksystembgpb.models.account.AccountType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findAllByCurrency(Currency currency)

    List<Account> findAllByAccountType(AccountType accountType)

}