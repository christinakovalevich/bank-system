package com.kovalevich.banksystembgpb.services.country

import com.kovalevich.banksystembgpb.exceptions.ResourceAlreadyExistsException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotValidException
import com.kovalevich.banksystembgpb.models.country.Country
import com.kovalevich.banksystembgpb.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface CountryService extends LoggerService {

    List<Country> findAll()

    Country findByIso3code(String iso3code) throws ResourceNotFoundException

    Country save(Country country) throws ResourceAlreadyExistsException, ResourceNotValidException

    Country update(String iso3code, Country country) throws ResourceNotFoundException, ResourceNotValidException

    def delete(String iso3code) throws ResourceNotFoundException

}