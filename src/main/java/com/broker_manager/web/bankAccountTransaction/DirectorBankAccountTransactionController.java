package com.broker_manager.web.bankAccountTransaction;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.BankAccountTransaction;
import com.broker_manager.service.bankAccountTransaction.DirectorBankAccountTransactionService;
import com.broker_manager.web.AuthorizedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(DirectorBankAccountTransactionController.REST_URL)
public class DirectorBankAccountTransactionController {
    static final String REST_URL = "/director/transactions";

    private final DirectorBankAccountTransactionService directorBankAccountTransactionService;

    public DirectorBankAccountTransactionController(DirectorBankAccountTransactionService directorBankAccountTransactionService) {
        this.directorBankAccountTransactionService = directorBankAccountTransactionService;
    }

    @GetMapping
    public List<BankAccountTransaction> getAllBankAccountTransactions() {
        // Вывести список BankAccountTransaction сортированный по дате исполнение от новых к старым
            return directorBankAccountTransactionService.getAllBankAccountTransactions();
    }

    @GetMapping("/{id}")
    public BankAccountTransaction getBankAccountTransaction(@PathVariable Integer id) {
        // Вывести BankAccountTransaction
        return directorBankAccountTransactionService.getBankAccountTransaction(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccountTransaction createBankAccountTransaction(@Valid @RequestBody BankAccount senderBankAccount, @Valid @RequestBody BankAccount recipientBankAccount,
                                                               int amount, @AuthenticationPrincipal AuthorizedUser authUser) {
        // Создать новую BankAccountTransaction с автопростановкой текущих даты и времени и изменением в счетах BankAccount, указанных в BankAccountTransaction
        BankAccountTransaction bankAccountTransaction = new BankAccountTransaction();
        bankAccountTransaction.setSenderBankAccountId(senderBankAccount);
        bankAccountTransaction.setRecipientBankAccountId(recipientBankAccount);
        bankAccountTransaction.setAmountStock(amount);
        bankAccountTransaction.setWhenDone(LocalDateTime.now());

        // Вызов метода createBankAccountTransaction() из сервисного класса
        return directorBankAccountTransactionService.createBankAccountTransaction(senderBankAccount, recipientBankAccount, amount, authUser);
    }

}
