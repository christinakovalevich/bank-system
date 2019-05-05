package com.kovalevich.banksystembgpb.services.worker

import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.worker.WorkerNotAuthorizedException
import com.kovalevich.banksystembgpb.exceptions.worker.WrongPasswordException
import com.kovalevich.banksystembgpb.models.worker.Worker
import com.kovalevich.banksystembgpb.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface WorkerService extends LoggerService{

    List<Worker> findAll()

    Worker findById(long id)

    Worker findByUsername(String username) throws ResourceNotFoundException

    Worker findByUsernameAndPassword(String username, String password)
            throws ResourceNotFoundException, WrongPasswordException

    Worker save(Worker user)

    Worker update(long id, Worker user)

    def delete(long id)

    void checkAuthorization() throws WorkerNotAuthorizedException

}
