package com.kovalevich.banksystembgpb.repositories.account

import com.kovalevich.banksystembgpb.models.account.AccountType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountTypeRepository extends CrudRepository<AccountType, Long> {

    AccountType findById(long id)

    AccountType findByName(String name)

}
