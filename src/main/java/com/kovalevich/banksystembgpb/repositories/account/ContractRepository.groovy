package com.kovalevich.banksystembgpb.repositories.account

import com.kovalevich.banksystembgpb.models.account.Contract
import com.kovalevich.banksystembgpb.models.client.Client
import com.kovalevich.banksystembgpb.models.worker.Worker
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ContractRepository extends CrudRepository<Contract, Long> {

    Contract findById(long id)

    List<Contract> findAllByClient(Client client)

    List<Contract> findAllByWorker(Worker worker)

}
