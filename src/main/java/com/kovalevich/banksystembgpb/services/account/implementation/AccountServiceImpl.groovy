package com.kovalevich.banksystembgpb.services.account.implementation

import com.kovalevich.banksystembgpb.config.BankConfig
import com.kovalevich.banksystembgpb.exceptions.ResourceAlreadyExistsException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotValidException
import com.kovalevich.banksystembgpb.models.account.Account
import com.kovalevich.banksystembgpb.models.account.AccountType
import com.kovalevich.banksystembgpb.repositories.account.AccountRepository
import com.kovalevich.banksystembgpb.services.account.abstraction.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository

    @Override
    List<Account> findAll() {
        return accountRepository.findAll() as List<Account>
    }

    @Override
    List<Account> findAllByCurrency(Currency currency) {
        return accountRepository.findAllByCurrency(currency)
    }

    @Override
    List<Account> findAllByAccountType(AccountType accountType) {
        return accountRepository.findAllByAccountType(accountType)
    }

    @Override
    Account findById(long id) {
        Account account = accountRepository.findById(id).orElse(null)
        if (account) {
            return account

        } else {
            throw new ResourceNotFoundException('Account not found. Id: ' + id)
        }
    }

    @Override
    Account save(Account account) {
        try {
            AccountType accountType = account.accountType
            account.number = accountType.id == BankConfig.ACCOUNT_TYPE_ACTIVE_ID ?
                    BankConfig.CREDIT_ACCOUNT_NUMBER : BankConfig.DEPOSIT_ACCOUNT_NUMBER

            account.name = accountType.id == BankConfig.ACCOUNT_TYPE_ACTIVE_ID ?
                    (account.isCurrent ? BankConfig.CREDIT_ACCOUNT_CURRENT_NAME : BankConfig.CREDIT_ACCOUNT_PERCENT_NAME) :
                    (account.isCurrent ? BankConfig.DEPOSIT_ACCOUNT_CURRENT_NAME : BankConfig.DEPOSIT_ACCOUNT_PERCENT_NAME)

            if (!account.value) {
                account.value = new BigDecimal(0.0)
            }

            findById(account.id)
            throw new ResourceAlreadyExistsException('Account already exists. Passport: ' + account)

        } catch (ResourceNotFoundException ignored) {
            if (!account)
                throw new ResourceNotValidException("Account is not valid. ${account}.")

            account = accountRepository.save(account)
            return findById(account.id)
        }
    }

    @Override
    Account update(long id, Account account) {
        account.id = id
        findById(account.id)
        AccountType accountType = account.accountType
        account.number = accountType.id == BankConfig.ACCOUNT_TYPE_ACTIVE_ID ?
                BankConfig.CREDIT_ACCOUNT_NUMBER : BankConfig.DEPOSIT_ACCOUNT_NUMBER

        account.name = accountType.id == BankConfig.ACCOUNT_TYPE_ACTIVE_ID ?
                (account.isCurrent ? BankConfig.CREDIT_ACCOUNT_CURRENT_NAME : BankConfig.CREDIT_ACCOUNT_PERCENT_NAME) :
                (account.isCurrent ? BankConfig.DEPOSIT_ACCOUNT_CURRENT_NAME : BankConfig.DEPOSIT_ACCOUNT_PERCENT_NAME)

        if (!account)
            throw new ResourceNotValidException("Account is not valid. ${account}.")

        account = accountRepository.save(account)
        return findById(account.id)
    }

    @Override
    def delete(long id) {
        accountRepository.delete(findById(id))
    }

    @Override
    def changeValue(Account account, BigDecimal value) {
        account.value = value
        return update(account.id, account)
    }
}
