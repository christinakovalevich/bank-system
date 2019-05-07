package com.kovalevich.banksystembgpb.repositories.account

import com.kovalevich.banksystembgpb.models.account.Account
import com.kovalevich.banksystembgpb.models.client.Client
import com.kovalevich.banksystembgpb.models.worker.Worker
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository extends CrudRepository<Account, Long> {

    Account findById(long id)

    List<Account> findAllByClient(Client client)

    List<Account> findAllByWorker(Worker worker)

    List<Account> findAllByCurrency(Currency currency)

}