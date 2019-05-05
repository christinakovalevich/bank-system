package com.kovalevich.banksystembgpb.repositories.client

import com.kovalevich.banksystembgpb.models.client.Client
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository extends CrudRepository<Client, Long> {

    Client findById(long id)

    Client findByPassportId(String passportId)

    Client findByEmail(String email)

    Client findByMobilePhoneNumber(String mobilePhoneNumber)

}
