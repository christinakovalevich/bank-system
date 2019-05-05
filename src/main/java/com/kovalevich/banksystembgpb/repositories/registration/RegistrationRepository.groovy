package com.kovalevich.banksystembgpb.repositories.registration

import com.kovalevich.banksystembgpb.models.address.Address
import com.kovalevich.banksystembgpb.models.registration.Registration
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RegistrationRepository extends CrudRepository<Registration, Long> {

    Registration findById(long id)

    List<Registration> findAllByAddress(Address address)

}