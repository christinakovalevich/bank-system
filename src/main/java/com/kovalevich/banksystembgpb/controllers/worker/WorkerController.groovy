package com.kovalevich.banksystembgpb.controllers.worker

import com.kovalevich.banksystembgpb.exceptions.ResourceNotFoundException
import com.kovalevich.banksystembgpb.exceptions.worker.WorkerNotAuthorizedException
import com.kovalevich.banksystembgpb.models.worker.Worker
import com.kovalevich.banksystembgpb.services.security.AuthorizationService
import com.kovalevich.banksystembgpb.services.worker.WorkerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping(value = '/worker')
class WorkerController {

    @Autowired
    WorkerService workerService

    @Autowired
    AuthorizationService authorizationService

    @GetMapping(value = '/profile')
    def index(Model model) {
        try {
            workerService.checkAuthorization()
            Worker worker = authorizationService.getWorkerFromSession()
            model.addAttribute('worker', worker)
            return 'worker/profile'
        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            return 'redirect:/worker/logIn'
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.localizedMessage)
        }
    }

    @RequestMapping(value = '/authenticate')
    def authenticate(@RequestParam String username, @RequestParam String password) {
        try {
            workerService.findByUsernameAndPassword(username, password)
            return 'redirect:/worker/profile'
        } catch (ResourceNotFoundException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.localizedMessage)
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.localizedMessage)
        }
    }

    @RequestMapping(value = '/logIn')
    def logIn() {
        return 'worker/logIn'
    }

    @RequestMapping(value = '/logUp')
    def logUp() {
        return 'worker/logUp'
    }

    @RequestMapping(value = '/logOut')
    def logOut() {
        try {
            authorizationService.removeWorkerFromSession()
            workerService.checkAuthorization()
        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            return 'redirect:/'
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.localizedMessage)
        }
    }
}
