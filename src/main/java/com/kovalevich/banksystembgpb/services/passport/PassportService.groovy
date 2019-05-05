package com.kovalevich.banksystembgpb.services.passport

import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.ResourceNotValidException
import com.kovalevich.banksystembgpb.models.passport.Passport
import com.kovalevich.banksystembgpb.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface PassportService extends LoggerService {

    List<Passport> findAll()

    Passport findById(String id) throws ResourceNotFoundException

    Passport save(Passport passport) throws ResourceNotValidException

    Passport update(String id, Passport passport) throws ResourceNotFoundException, ResourceNotValidException

    def delete(String id) throws ResourceNotFoundException

    def isPassportIdInUse(String id)

    def isPassportNumberInUse(int number)

}