package com.kovalevich.banksystembgpb.services.account.abstraction

import com.kovalevich.banksystembgpb.models.account.Contract
import com.kovalevich.banksystembgpb.models.client.Client
import com.kovalevich.banksystembgpb.models.worker.Worker
import com.kovalevich.banksystembgpb.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface ContractService extends LoggerService {

    Contract findById(long id)

    List<Contract> findAll()

    List<Contract> findAllByClient(Client client)

    List<Contract> findAllByWorker(Worker worker)

    Contract save(Contract contract)

    Contract update(long id, Contract contract)

    def delete(long id)

    def setDateOfIssue(Contract contract)

    def setDateOfExpire(Contract contract, int years)

}
