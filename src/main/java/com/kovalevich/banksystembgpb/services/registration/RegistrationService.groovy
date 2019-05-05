package com.kovalevich.banksystembgpb.services.registration

import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotValidException
import com.kovalevich.banksystembgpb.models.address.Address
import com.kovalevich.banksystembgpb.models.registration.Registration
import com.kovalevich.banksystembgpb.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface RegistrationService extends LoggerService {

    List<Registration> findAll()

    List<Registration> findAllByAddress(Address address) throws ResourceNotFoundException

    Registration findById(long id) throws ResourceNotFoundException

    Registration save(Registration registration) throws ResourceNotValidException

    Registration update(long id, Registration registration) throws ResourceNotFoundException, ResourceNotValidException

    def delete(long id) throws ResourceNotFoundException

}
