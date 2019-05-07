package com.kovalevich.banksystembgpb.services.account

import com.kovalevich.banksystembgpb.config.BankConfig
import com.kovalevich.banksystembgpb.exceptions.ResourceAlreadyExistsException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotValidException
import com.kovalevich.banksystembgpb.models.account.Account
import com.kovalevich.banksystembgpb.models.client.Client
import com.kovalevich.banksystembgpb.models.worker.Worker
import com.kovalevich.banksystembgpb.repositories.account.AccountRepository
import com.kovalevich.banksystembgpb.services.security.AuthorizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository

    @Autowired
    AuthorizationService authorizationService

    @Override
    List<Account> findAll() {
        return accountRepository.findAll() as List<Account>
    }

    @Override
    List<Account> findAllByClient(Client client) {
        return accountRepository.findAllByClient(client)
    }

    @Override
    List<Account> findAllByWorker(Worker worker) {
        return accountRepository.findAllByWorker(worker)
    }

    @Override
    List<Account> findAllByCurrency(Currency currency) {
        return accountRepository.findAllByCurrency(currency)
    }

    @Override
    Account findById(long id) throws ResourceNotFoundException {
        Account account = accountRepository.findById(id)
        if (!account)
            throw new ResourceNotFoundException('Account with id: ' + id + ' not found.')

        return account
    }

    @Override
    Account save(Account account) throws ResourceNotValidException {
        try {
            findById(account.id)
            throw new ResourceAlreadyExistsException('Account already exists. Details: ' + account)

        } catch (ResourceNotFoundException ignored) {
            if (!account)
                throw new ResourceNotValidException('Account is not valid')

            setDateOfIssue(account)
            setDateOfExpire(account, BankConfig.ACCOUNT_DURANCE)
            account.worker = authorizationService.getWorkerFromSession()

            account = accountRepository.save(account)
            return findById(account.id)
        }
    }

    @Override
    Account update(long id, Account account) throws ResourceNotFoundException, ResourceNotValidException {
        account.id = id
        findById(account.id)

        if (!account)
            throw new ResourceNotValidException('Account is not valid')

        account = accountRepository.save(account)
        return findById(account.id)
    }

    @Override
    def delete(long id) throws ResourceNotFoundException {
        try {
            def accountFromDB = findById(id)
            accountRepository.delete(accountFromDB)

        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
        }
    }

    @Override
    def setDateOfIssue(Account account) {
        account.dateOfIssue = new Date()
    }

    @Override
    def setDateOfExpire(Account account, int years) {
        if (!account.dateOfIssue) {
            account.setDateOfIssue()
        }
        Calendar calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, years)
        account.dateOfExpire = calendar.getTime()
    }
}
