package com.kovalevich.banksystembgpb.services.security

import com.kovalevich.banksystembgpb.models.worker.Worker
import com.kovalevich.banksystembgpb.services.logger.LoggerService

interface AuthorizationService extends LoggerService {

    Worker getWorkerFromSession()

    void putWorkerInSession(Worker worker)

    void removeWorkerFromSession()

}