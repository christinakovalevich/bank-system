package com.kovalevich.banksystembgpb.repositories.country

import com.kovalevich.banksystembgpb.models.country.Country
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CountryRepository extends CrudRepository<Country, String> {

    Country findByIso3code(String iso3code)

}
