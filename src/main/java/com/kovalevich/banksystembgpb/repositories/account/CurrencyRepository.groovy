package com.kovalevich.banksystembgpb.repositories.account

import com.kovalevich.banksystembgpb.models.account.Currency
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyRepository extends CrudRepository<Currency, Long> {

    Currency findById(long id)

    Currency findByName(String name)

}
