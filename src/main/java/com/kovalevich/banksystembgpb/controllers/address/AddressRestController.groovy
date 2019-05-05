package com.kovalevich.banksystembgpb.controllers.address

import com.kovalevich.banksystembgpb.models.address.Address
import com.kovalevich.banksystembgpb.services.address.AddressService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = '/api/address', produces = 'application/json')
class AddressRestController {

    @Autowired
    AddressService addressService

    @GetMapping
    def findAll() {
        try {
            return addressService.findAll()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/{id}')
    def findById(@PathVariable long id) {
        try {
            return addressService.findById(id)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping
    def create(@RequestBody Address address) {
        try {
            return addressService.save(address)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PutMapping(value = '/{id}')
    def update(@PathVariable long id, @RequestBody Address address) {
        try {
            return addressService.update(id, address)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @DeleteMapping(value = '/{id}')
    def delete(@PathVariable long id) {
        try {
            addressService.delete(id)
            return ResponseEntity.ok().build()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping(value = 'findByCountryAndCityAndStreetAndBuildingNumberAndApartmentNumberAndPostCode')
    def findAllByCountryAndCityAndBuildingNumber(@RequestBody Address address) {
        try {
            return addressService.findByCountryAndCityAndStreetAndBuildingNumberAndApartmentNumberAndPostCode(address.country,
                    address.city, address.street, address.buildingNumber, address.apartmentNumber, address.postCode)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

}
