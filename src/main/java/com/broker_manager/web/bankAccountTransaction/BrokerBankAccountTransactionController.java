package com.broker_manager.web.bankAccountTransaction;

import com.broker_manager.model.BankAccountTransaction;
import com.broker_manager.model.Stock;
import com.broker_manager.model.enums.Operation;
import com.broker_manager.service.bankAccountTransaction.BrokerBankAccountTransactionService;
import com.broker_manager.web.AuthorizedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BrokerBankAccountTransactionController.REST_URL)
public class BrokerBankAccountTransactionController {
    static final String REST_URL = "/broker/transactions";

    private final BrokerBankAccountTransactionService bankAccountTransactionService;

    public BrokerBankAccountTransactionController(BrokerBankAccountTransactionService bankAccountTransactionService) {
        this.bankAccountTransactionService = bankAccountTransactionService;
    }


    @GetMapping
    public List<BankAccountTransaction> getAllBankAccountTransactionsByUser(@AuthenticationPrincipal AuthorizedUser authUser) {
        // Вывести список BankAccountTransaction сортированный по дате исполнение от новых к старым, которые совершал данный User
        return bankAccountTransactionService.getAllBankAccountTransactionsByUser(authUser);
    }

    @GetMapping("/{id}")
    public BankAccountTransaction getBankAccountTransaction(@PathVariable Integer id) {
        // Вывести BankAccountTransaction
        return bankAccountTransactionService.getBankAccountTransaction(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccountTransaction createBankAccountTransaction(@Valid @RequestBody Stock stock, Integer amount, Operation operation,
                                                               @AuthenticationPrincipal AuthorizedUser authUser) {
        // Создать новую BankAccountTransaction с автопростановкой текущих даты и времени
        // sender_bank_account - это BankAccount отдела
        // recipient_bank_account - это BankAccount биржи (он будет создан в качестве заглушки и для всех един)
        // Если покупка, то нужно также приписать StockInBankAccount к данному счету. Если продажа, то соответственно, удалить
        return bankAccountTransactionService.createBankAccountTransaction(stock, amount, operation, authUser);
    }
}
