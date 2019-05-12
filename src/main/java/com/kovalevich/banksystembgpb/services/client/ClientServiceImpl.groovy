package com.kovalevich.banksystembgpb.services.client

import com.kovalevich.banksystembgpb.exceptions.ResourceAlreadyExistsException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotValidException
import com.kovalevich.banksystembgpb.models.client.Client
import com.kovalevich.banksystembgpb.repositories.client.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository

    @Override
    List<Client> findAll() {
        return clientRepository.findAll() as List<Client>
    }

    @Override
    Client findById(long id) throws ResourceNotFoundException {
        log.debug("findById, id: ${id}.")

        Client registrationFromDB = clientRepository.findById(id)

        if (!registrationFromDB)
            throw new ResourceNotFoundException("Client not found. Id: ${id}")

        return registrationFromDB
    }

    @Override
    Client findByPassportId(Object id) throws ResourceNotFoundException {
        return null
    }

    @Override
    Client save(Client client) throws ResourceNotValidException {
        log.debug("save, ${client}.")

        try {
            def clientFromDB = findById(client.id)
            throw new ResourceAlreadyExistsException("Client already exists. ${clientFromDB}")

        } catch (ResourceNotFoundException ignored) {
            if (!client) {
                throw new ResourceNotValidException("Client not valid. ${client}")
            }

            client = clientRepository.save(client)
            return findById(client.id)
        }
    }

    @Override
    Client update(long id, Client client) throws ResourceNotFoundException, ResourceNotValidException {
        client.id = id
        log.debug("update, ${client}.")

        findById(client.id)

        if (!client) {
            throw new ResourceNotValidException("Client not valid. ${client}")
        }

        client = clientRepository.save(client)
        return findById(client.id)
    }

    @Override
    def delete(long id) throws ResourceNotFoundException {
        log.debug("delete, id: ${id}.")

        clientRepository.delete(findById(id))
    }

    @Override
    def isEmailInUse(String email) {
        return clientRepository.findByEmail(email) ? true : false
    }

    @Override
    def isMobilePhoneNumberInUse(String mobilePhoneNumber) {
        return clientRepository.findByMobilePhoneNumber(mobilePhoneNumber) ? true : false
    }

    @Override

    def getInitials(Client client) {
        return client.passport.lastName + ' ' +
                client.passport.firstName.charAt(0) + '.' + ' ' +
                client.passport.middleName.charAt(0)+ '.'
    }
}
