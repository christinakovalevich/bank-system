package com.kovalevich.banksystembgpb.services.account.implementation

import com.kovalevich.banksystembgpb.exceptions.ResourceAlreadyExistsException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotValidException
import com.kovalevich.banksystembgpb.models.account.AccountType
import com.kovalevich.banksystembgpb.repositories.account.AccountTypeRepository
import com.kovalevich.banksystembgpb.services.account.abstraction.AccountTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountTypeServiceImpl implements AccountTypeService {

    @Autowired
    AccountTypeRepository accountTypeRepository

    @Override
    List<AccountType> findAll() {
        return accountTypeRepository.findAll() as List<AccountType>
    }

    @Override
    AccountType findById(long id) {
        AccountType accountType = accountTypeRepository.findById(id)
        if (!accountType)
            throw new ResourceNotFoundException('AccountType with id: ' + id + ' not found.')

        return accountType
    }

    @Override
    AccountType findByName(String name) {
        AccountType accountType = accountTypeRepository.findByName(name)
        if (!accountType)
            throw new ResourceNotFoundException('AccountType with name: ' + name + ' not found.')

        return accountType
    }

    @Override
    AccountType save(AccountType accountType) {
        try {
            findById(accountType.id)
            throw new ResourceAlreadyExistsException('AccountType already exists. Details: ' + accountType)

        } catch (ResourceNotFoundException ignored) {
            if (!accountType)
                throw new ResourceNotValidException('AccountType is not valid')

            accountType = accountTypeRepository.save(accountType)
            return findById(accountType.id)
        }
    }

    @Override
    AccountType update(long id, AccountType accountType) {
        accountType.id = id
        findById(accountType.id)

        if (!accountType)
            throw new ResourceNotValidException('AccountType is not valid')

        accountType = accountTypeRepository.save(accountType)
        return findById(accountType.id)
    }

    @Override
    def delete(long id) {
        try {
            def accountType = findById(id)
            accountTypeRepository.delete(accountType)

        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
        }
    }
}
