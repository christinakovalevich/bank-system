package com.kovalevich.banksystembgpb.repositories.address

import com.kovalevich.banksystembgpb.models.address.Address
import com.kovalevich.banksystembgpb.models.country.Country
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository extends CrudRepository<Address, Long> {

    Address findById(long id)

    Address findByCountryAndCityAndStreetAndBuildingNumberAndApartmentNumberAndPostCode(Country country, String city, String street, short buildingNumber, short apartmentNumber, int postCode)

}