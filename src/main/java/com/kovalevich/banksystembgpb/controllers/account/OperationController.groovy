package com.kovalevich.banksystembgpb.controllers.account

import com.kovalevich.banksystembgpb.config.BankConfig
import com.kovalevich.banksystembgpb.exceptions.worker.WorkerNotAuthorizedException
import com.kovalevich.banksystembgpb.services.account.abstraction.AccountService
import com.kovalevich.banksystembgpb.services.account.abstraction.AccountTypeService
import com.kovalevich.banksystembgpb.services.account.abstraction.CloseBankDayService
import com.kovalevich.banksystembgpb.services.account.abstraction.ContractService
import com.kovalevich.banksystembgpb.services.account.abstraction.CurrencyService
import com.kovalevich.banksystembgpb.services.client.ClientService
import com.kovalevich.banksystembgpb.services.security.AuthorizationService
import com.kovalevich.banksystembgpb.services.worker.WorkerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping(value = '/operation')
class OperationController {

    @Autowired
    WorkerService workerService

    @Autowired
    AuthorizationService authorizationService

    @Autowired
    CurrencyService currencyService

    @Autowired
    ClientService clientService

    @Autowired
    ContractService contractService

    @Autowired
    AccountService accountService

    @Autowired
    AccountTypeService accountTypeService

    @Autowired
    CloseBankDayService closeBankDayService

    @RequestMapping
    def index(Model model) {
        try {
            workerService.checkAuthorization()
            def worker = authorizationService.workerFromSession
            model.addAttribute('worker', worker)

        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            return 'redirect:/worker/logIn'
        }
        return 'operation/index'
    }

    @RequestMapping(value = '/credit')
    def credit(Model model) {
        try {
            workerService.checkAuthorization()

            def contracts = contractService.findAll()
            def activeAccounts = [] as Set
            for (contract in contracts) {

                if (contract.current_account.accountType.id == BankConfig.ACCOUNT_TYPE_ACTIVE_ID ||
                        contract.percent_account.accountType.id == BankConfig.ACCOUNT_TYPE_ACTIVE_ID) {

                    def currentAccount = [:]
                    currentAccount.id = contract.current_account.id
                    currentAccount.name = contract.current_account.name
                    currentAccount.currency = contract.current_account.currency
                    currentAccount.accountType = contract.current_account.accountType
                    currentAccount.number = contract.current_account.number
                    currentAccount.type = contract.current_account.accountType.id == BankConfig.ACCOUNT_TYPE_ACTIVE_ID ?
                            'Активный' : 'Пассивный'
                    currentAccount.dateOfIssue = contract.dateOfIssue
                    currentAccount.value = contract.current_account.value
                    currentAccount.percentage = contract.percentage
                    def worker = [:]
                    worker.id = contract.worker.id
                    worker.name = workerService.getInitials(contract.worker)
                    currentAccount.worker = worker
                    def client = [:]
                    client.id = contract.client.id
                    client.name = clientService.getInitials(contract.client)
                    currentAccount.client = client
                    currentAccount.contract = contract

                    def percentAccount = [:]
                    percentAccount.id = contract.percent_account.id
                    percentAccount.name = contract.percent_account.name
                    percentAccount.currency = contract.percent_account.currency
                    percentAccount.accountType = contract.percent_account.accountType
                    percentAccount.number = contract.percent_account.number
                    percentAccount.type = contract.percent_account.accountType.id == BankConfig.ACCOUNT_TYPE_ACTIVE_ID ?
                            'Активный' : 'Пассивный'
                    percentAccount.dateOfIssue = contract.dateOfIssue
                    percentAccount.value = contract.percent_account.value
                    percentAccount.percentage = contract.percentage
                    percentAccount.worker = worker
                    percentAccount.client = client
                    percentAccount.contract = contract

                    activeAccounts.add(currentAccount)
                    activeAccounts.add(percentAccount)
                }
            }

            def currencies = currencyService.findAll()
            def clients = clientService.findAll()

            def worker = authorizationService.workerFromSession
            def accountType = accountTypeService.findById(BankConfig.ACCOUNT_TYPE_ACTIVE_ID)
            model.addAttribute('worker', worker)
            model.addAttribute('currencies', currencies)
            model.addAttribute('clients', clients)
            model.addAttribute('activeAccounts', activeAccounts)
            model.addAttribute('accountType', accountType)
        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            return 'redirect:/worker/logIn'
        }
        return 'operation/credit'
    }

    @RequestMapping(value = '/deposit')
    def deposit(Model model) {
        try {
            workerService.checkAuthorization()

            def contracts = contractService.findAll()
            def passiveAccounts = [] as Set
            for (contract in contracts) {

                if (contract.current_account.accountType.id == BankConfig.ACCOUNT_TYPE_PASSIVE_ID ||
                        contract.percent_account.accountType.id == BankConfig.ACCOUNT_TYPE_PASSIVE_ID) {

                    def currentAccount = [:]
                    currentAccount.id = contract.current_account.id
                    currentAccount.name = contract.current_account.name
                    currentAccount.currency = contract.current_account.currency
                    currentAccount.accountType = contract.current_account.accountType
                    currentAccount.number = contract.current_account.number
                    currentAccount.type = contract.current_account.accountType.id == BankConfig.ACCOUNT_TYPE_ACTIVE_ID ?
                            'Активный' : 'Пассивный'
                    currentAccount.dateOfIssue = contract.dateOfIssue
                    currentAccount.value = contract.current_account.value
                    currentAccount.percentage = contract.percentage
                    def worker = [:]
                    worker.id = contract.worker.id
                    worker.name = workerService.getInitials(contract.worker)
                    currentAccount.worker = worker
                    def client = [:]
                    client.id = contract.client.id
                    client.name = clientService.getInitials(contract.client)
                    currentAccount.client = client
                    currentAccount.contract = contract

                    def percentAccount = [:]
                    percentAccount.id = contract.percent_account.id
                    percentAccount.name = contract.percent_account.name
                    percentAccount.currency = contract.percent_account.currency
                    percentAccount.accountType = contract.percent_account.accountType
                    percentAccount.number = contract.percent_account.number
                    percentAccount.type = contract.percent_account.accountType.id == BankConfig.ACCOUNT_TYPE_ACTIVE_ID ?
                            'Активный' : 'Пассивный'
                    percentAccount.dateOfIssue = contract.dateOfIssue
                    percentAccount.value = contract.percent_account.value
                    percentAccount.percentage = contract.percentage
                    percentAccount.worker = worker
                    percentAccount.client = client
                    percentAccount.contract = contract

                    passiveAccounts.add(currentAccount)
                    passiveAccounts.add(percentAccount)
                }
            }

            def currencies = currencyService.findAll()
            def clients = clientService.findAll()

            def worker = authorizationService.workerFromSession
            def accountType = accountTypeService.findById(BankConfig.ACCOUNT_TYPE_PASSIVE_ID)
            model.addAttribute('worker', worker)
            model.addAttribute('currencies', currencies)
            model.addAttribute('clients', clients)
            model.addAttribute('passiveAccounts', passiveAccounts)
            model.addAttribute('accountType', accountType)
        } catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            return 'redirect:/worker/logIn'
        }
        return 'operation/deposit'
    }

    @RequestMapping(value = '/closeBankDay')
    def closeBankDay() {
        try {
            workerService.checkAuthorization()
            def worker = authorizationService.workerFromSession

            def args = [:]
            args.worker = worker

            closeBankDayService.closeBankDay(args)

            return 'redirect:/operation'
        }
        catch (WorkerNotAuthorizedException e) {
            e.printStackTrace()
            return 'redirect:/worker/logIn'
        }
    }
}
