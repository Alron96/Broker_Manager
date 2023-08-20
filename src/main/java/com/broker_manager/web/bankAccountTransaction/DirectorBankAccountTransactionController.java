package com.broker_manager.web.bankAccountTransaction;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.BankAccountTransaction;
import com.broker_manager.web.AuthorizedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DirectorBankAccountTransactionController.REST_URL)
public class DirectorBankAccountTransactionController {
    static final String REST_URL = "/director/transactions";

    @GetMapping
    public List<BankAccountTransaction> getAllBankAccountTransactions() {
        // Вывести список BankAccountTransaction сортированный по дате исполнение от новых к старым
        return null;
    }

    @GetMapping("/{id}")
    public BankAccountTransaction getBankAccountTransaction(@PathVariable Integer id) {
        // Вывести BankAccountTransaction
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccountTransaction createBankAccountTransaction(@Valid @RequestBody BankAccount senderBankAccount, @Valid @RequestBody BankAccount recipientBankAccount,
                                                               Double amount, @AuthenticationPrincipal AuthorizedUser authUser) {
        // Создать новую BankAccountTransaction с автопростановкой текущих даты и времени и изменением в счетах BankAccount, указанных в BankAccountTransaction
        return null;
    }
}
