package com.broker_manager.web.bankAccount;

import com.broker_manager.model.BankAccount;
import com.broker_manager.service.bankAccount.DirectorBankAccountService;
import com.broker_manager.web.AuthorizedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DirectorBankAccountController.REST_URL)
public class DirectorBankAccountController {
    static final String REST_URL = "/director/bankAccounts";

    private final DirectorBankAccountService directorBankAccountService;

    public DirectorBankAccountController(DirectorBankAccountService directorBankAccountService) {
        this.directorBankAccountService = directorBankAccountService;
    }

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        // Вывести список BankAccount без StockInBankAccount
        return directorBankAccountService.getAllBankAccounts();
    }

    @GetMapping("/{id}")
    public BankAccount getBankAccount(@PathVariable Integer id) {
        // Вывести BankAccount c StockInBankAccount и BankAccountTransaction
        return directorBankAccountService.getBankAccount(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccount createBankAccount(@Valid @RequestBody BankAccount bankAccount, @RequestParam(required = false) Integer userId) {
        // Создать новый BankAccount
        // Проверка, что у отдела или у юзера не может быть больше 1 счета
        return directorBankAccountService.createBankAccount(bankAccount);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBankAccount(@PathVariable Integer id) {
        // Закрыть BankAccount с условием, что на балансе 0 рублей и у данного BankAccount нет StockInBankAccount
        directorBankAccountService.deleteBankAccount(id);
    }
}
