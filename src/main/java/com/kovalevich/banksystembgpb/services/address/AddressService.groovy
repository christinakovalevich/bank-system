package com.kovalevich.banksystembgpb.services.address

import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotValidException
import com.kovalevich.banksystembgpb.models.address.Address
import com.kovalevich.banksystembgpb.models.country.Country
import com.kovalevich.banksystembgpb.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface AddressService extends LoggerService {

    List<Address> findAll()

    Address findByCountryAndCityAndStreetAndBuildingNumberAndApartmentNumberAndPostCode(Country country, String city, String street, short buildingNumber, short apartmentNumber, int postCode) throws ResourceNotFoundException

    Address findById(long id) throws ResourceNotFoundException

    Address save(Address address) throws ResourceNotValidException

    Address update(long id, Address address) throws ResourceNotFoundException, ResourceNotValidException

    def delete(long id) throws ResourceNotFoundException

}
