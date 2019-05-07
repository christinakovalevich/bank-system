package com.kovalevich.banksystembgpb.services.account

import com.kovalevich.banksystembgpb.exceptions.ResourceAlreadyExistsException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotValidException
import com.kovalevich.banksystembgpb.models.account.Currency
import com.kovalevich.banksystembgpb.repositories.account.CurrencyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository

    @Override
    List<Currency> findAll() {
        return currencyRepository.findAll() as List<Currency>
    }

    @Override
    Currency findById(long id) throws ResourceNotFoundException {
        Currency currency = currencyRepository.findById(id)
        if (!currency)
            throw new ResourceNotFoundException('Currency with id: ' + id + ' not found.')

        return currency
    }

    @Override
    Currency findByName(String name) throws ResourceNotFoundException {
        Currency currency = currencyRepository.findByName(name)
        if (!currency)
            throw new ResourceNotFoundException('Currency with name: ' + name + ' not found.')

        return currency
    }

    @Override
    Currency save(Currency currency) throws ResourceNotValidException {
        try {
            findById(currency.id)
            throw new ResourceAlreadyExistsException('Currency already exists. Details: ' + currency)

        } catch (ResourceNotFoundException ignored) {
            if (!currency)
                throw new ResourceNotValidException('Currency is not valid')

            currency = currencyRepository.save(currency)
            return findById(currency.id)
        }
    }

    @Override
    Currency update(long id, Currency currency) throws ResourceNotFoundException, ResourceNotValidException {
        currency.id = id
        findById(currency.id)

        if (!currency)
            throw new ResourceNotValidException('Currency is not valid')

        currency = currencyRepository.save(currency)
        return findById(currency.id)
    }

    @Override
    def delete(long id) throws ResourceNotFoundException {
        try {
            def currencyFromDB = findById(id)
            currencyRepository.delete(currencyFromDB)

        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
        }
    }
}
