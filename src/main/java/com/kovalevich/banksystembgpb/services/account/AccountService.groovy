package com.kovalevich.banksystembgpb.services.account

import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotValidException
import com.kovalevich.banksystembgpb.models.account.Account
import com.kovalevich.banksystembgpb.models.client.Client
import com.kovalevich.banksystembgpb.models.worker.Worker
import com.kovalevich.banksystembgpb.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface AccountService extends LoggerService {

    List<Account> findAll()

    List<Account> findAllByClient(Client client)

    List<Account> findAllByWorker(Worker worker)

    List<Account> findAllByCurrency(Currency currency)

    Account findById(long id) throws ResourceNotFoundException

    Account save(Account account) throws ResourceNotValidException

    Account update(long id, Account account) throws ResourceNotFoundException, ResourceNotValidException

    def delete(long id) throws ResourceNotFoundException

    def setDateOfIssue(Account account)

    def setDateOfExpire(Account account, int years)
}
