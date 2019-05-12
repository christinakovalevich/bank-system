package com.kovalevich.banksystembgpb.controllers.account

import com.kovalevich.banksystembgpb.models.account.Contract
import com.kovalevich.banksystembgpb.services.account.abstraction.ContractService
import com.kovalevich.banksystembgpb.services.client.ClientService
import com.kovalevich.banksystembgpb.services.worker.WorkerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = '/api/contract')
class ContractRestController {

    @Autowired
    ContractService contractService

    @Autowired
    WorkerService workerService

    @Autowired
    ClientService clientService

    @GetMapping(value = '/{id}')
    def findById(@PathVariable long id) {
        try {
            return contractService.findById(id)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping
    def findAll() {
        try {
            return contractService.findAll()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/byClient/{id}')
    def findAllByClient(@PathVariable long id) {
        try {
            return contractService.findAllByClient(clientService.findById(id))

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/byWorker/{id}')
    def findAllByWorker(@PathVariable long id) {
        try {
            return contractService.findAllByWorker(workerService.findById(id))

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping
    def create(@RequestBody Contract contract) {
        try {
            return contractService.save(contract)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PutMapping(value = '/{id}')
    def update(@PathVariable long id, @RequestBody Contract contract) {
        try {
            return contractService.update(id, contract)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @DeleteMapping(value = '/{id}')
    def delete(@PathVariable long id) {
        try {
            contractService.delete(id)
            return ResponseEntity.ok().build()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }
}
