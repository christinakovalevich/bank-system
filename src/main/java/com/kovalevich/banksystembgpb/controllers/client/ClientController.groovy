package com.kovalevich.banksystembgpb.controllers.client

import com.kovalevich.banksystembgpb.exceptions.worker.WorkerNotAuthorizedException
import com.kovalevich.banksystembgpb.services.client.ClientService
import com.kovalevich.banksystembgpb.services.country.CountryService
import com.kovalevich.banksystembgpb.services.worker.WorkerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping(value = '/client')
class ClientController {

    @Autowired
    WorkerService workerService

    @Autowired
    ClientService clientService

    @Autowired
    CountryService countryService

    @RequestMapping
    def index(Model model) {
        try {
            workerService.checkAuthorization()
            def clients = clientService.findAll() as Set
            model.addAttribute("clients", clients)

        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            return 'redirect:/worker/logIn'
        }
        return 'client/index'
    }

    @RequestMapping(value = '/{id}')
    def show(@PathVariable long id, Model model) {
        try {
            workerService.checkAuthorization()
            def client = clientService.findById(id)
            model.addAttribute("client", client)

        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.message, e)
        }
        return 'client/show'
    }

    @RequestMapping(value = '/create')
    def create(Model model) {
        try {
            workerService.checkAuthorization()
            def countries = countryService.findAll() as Set
            model.addAttribute("countries", countries)

        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.message, e)
        }
        return 'client/create'
    }

    @RequestMapping(value = '/edit/{id}')
    def edit(@PathVariable long id, Model model) {
        try {
            workerService.checkAuthorization()
            def client = clientService.findById(id)
            model.addAttribute("client", client)

        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.message, e)
        }
        return 'client/edit'
    }

}
