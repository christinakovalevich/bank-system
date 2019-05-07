package com.kovalevich.banksystembgpb.services.account

import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotValidException
import com.kovalevich.banksystembgpb.models.account.Currency
import com.kovalevich.banksystembgpb.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface CurrencyService extends LoggerService {

    List<Currency> findAll()

    Currency findById(long id) throws ResourceNotFoundException

    Currency findByName(String name) throws ResourceNotFoundException

    Currency save(Currency currency) throws ResourceNotValidException

    Currency update(long id, Currency currency) throws ResourceNotFoundException, ResourceNotValidException

    def delete(long id) throws ResourceNotFoundException

}
