package com.kovalevich.banksystembgpb.services.address

import com.kovalevich.banksystembgpb.exceptions.ResourceAlreadyExistsException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotValidException
import com.kovalevich.banksystembgpb.models.address.Address
import com.kovalevich.banksystembgpb.models.country.Country
import com.kovalevich.banksystembgpb.repositories.address.AddressRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository

    @Override
    List<Address> findAll() {
        log.debug('findAll')
        return addressRepository.findAll() as List<Address>
    }

    @Override
    Address findByCountryAndCityAndStreetAndBuildingNumberAndApartmentNumberAndPostCode(Country country, String city, String street, short buildingNumber, short apartmentNumber, int postCode) throws ResourceNotFoundException {

        log.debug('findAllByCountryAndCityAndBuildingNumber')

        Address address = addressRepository.findByCountryAndCityAndStreetAndBuildingNumberAndApartmentNumberAndPostCode(country, city, street, buildingNumber, apartmentNumber, postCode)

        if (!address)
            throw new ResourceNotFoundException("Address not found: ${address}" )

        return address
    }

    @Override
    Address findById(long id) throws ResourceNotFoundException {
        log.debug("findById, id: ${id}.")

        Address address = addressRepository.findById(id)

        if (!address)
            throw new ResourceNotFoundException("Address not found, id: ${id}.")

        return address
    }

    @Override
    Address save(Address address) throws ResourceNotValidException {
        log.debug("save, ${address}.")

        try {
            def addressFromDB = findById(address.id)
            throw new ResourceAlreadyExistsException("Address already exists. ${addressFromDB}")

        } catch (ResourceNotFoundException ignored) {
            if (!address) {
                throw new ResourceNotValidException("Address not valid. ${address}")
            }

            address = addressRepository.save(address)
            return findById(address.id)
        }
    }

    @Override
    Address update(long id, Address address) throws ResourceNotFoundException, ResourceNotValidException {
        address.id = id
        log.debug("update, ${address}.")

        findById(address.id)

        if (!address) {
            throw new ResourceNotValidException("Address not valid. ${address}")
        }

        address = addressRepository.save(address)
        return findById(address.id)
    }

    @Override
    def delete(long id) throws ResourceNotFoundException {
        log.debug("delete, id: ${id}.")
        addressRepository.delete(findById(id))
    }
}
