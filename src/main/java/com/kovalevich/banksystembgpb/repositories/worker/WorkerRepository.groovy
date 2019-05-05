package com.kovalevich.banksystembgpb.repositories.worker

import com.kovalevich.banksystembgpb.models.worker.Worker
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkerRepository extends CrudRepository<Worker, Long> {

    Worker findById(long id)

    Worker findByUsername(String username)

    Worker findByUsernameAndPassword(String username, String password)

}
