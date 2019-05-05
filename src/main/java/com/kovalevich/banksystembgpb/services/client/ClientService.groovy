package com.kovalevich.banksystembgpb.services.client

import com.kovalevich.banksystembgpb.exceptions.ResourceAlreadyExistsException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotValidException
import com.kovalevich.banksystembgpb.models.client.Client
import com.kovalevich.banksystembgpb.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface ClientService extends LoggerService {

    List<Client> findAll()

    Client findById(long id) throws ResourceNotFoundException

    Client findByPassportId(id) throws ResourceNotFoundException

    Client save(Client client) throws ResourceAlreadyExistsException, ResourceNotValidException

    Client update(long id, Client client) throws ResourceNotFoundException, ResourceNotValidException

    def delete(long id) throws ResourceNotFoundException

    def isEmailInUse(String email)

    def isMobilePhoneNumberInUse(String mobilePhoneNumber)

}