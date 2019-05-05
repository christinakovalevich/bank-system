package com.kovalevich.banksystembgpb.controllers.accessLevel

import com.kovalevich.banksystembgpb.models.accessLevel.AccessLevel
import com.kovalevich.banksystembgpb.services.accessLevel.AccessLevelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = '/api/accessLevel', produces = 'application/json')
class AccessLevelRestController {

    @Autowired
    AccessLevelService accessLevelService

    @GetMapping
    def findAll() {
        try {
            return accessLevelService.findAll()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping(value = '/{id}')
    def findById(@PathVariable short id) {
        try {
            return accessLevelService.findById(id)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping
    def create(@RequestBody AccessLevel accessLevel) {
        try {
            return accessLevelService.save(accessLevel)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PutMapping(value = '/{id}')
    def update(@PathVariable short id, @RequestBody AccessLevel accessLevel) {
        try {
            return accessLevelService.update(id, accessLevel)

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @DeleteMapping(value = '/{id}')
    def delete(@PathVariable short id) {
        try {
            accessLevelService.delete(id)
            return ResponseEntity.ok().build()

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }
}