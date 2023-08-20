package com.broker_manager.web.bankAccount;

import com.broker_manager.model.BankAccount;
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

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        // Вывести список BankAccount без StockInBankAccount
        return null;
    }

    @GetMapping("/{id}")
    public BankAccount getBankAccount(@PathVariable Integer id) {
        // Вывести BankAccount c StockInBankAccount и BankAccountTransaction
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccount createBankAccount(@Valid @RequestBody BankAccount bankAccount, @AuthenticationPrincipal AuthorizedUser authUser) {
        // Создать новый BankAccount
        // Проверка, что у отдела или у юзера не может быть больше 1 счета
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBankAccount(@PathVariable Integer id) {
        // Закрыть BankAccount с условием, что на балансе 0 рублей и у данного BankAccount нет StockInBankAccount
    }
}
