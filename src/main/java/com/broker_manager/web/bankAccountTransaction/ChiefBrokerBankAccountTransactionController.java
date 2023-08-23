package com.broker_manager.web.bankAccountTransaction;

import com.broker_manager.model.BankAccountTransaction;
import com.broker_manager.model.Stock;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Operation;
import com.broker_manager.service.bankAccountTransaction.ChiefBrokerBankAccountTransactionService;
import com.broker_manager.util.error.InsufficientStocksException;
import com.broker_manager.web.AuthorizedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ChiefBrokerBankAccountTransactionController.REST_URL)
public class ChiefBrokerBankAccountTransactionController {
    static final String REST_URL = "/chief_broker/transactions";

    private final ChiefBrokerBankAccountTransactionService transactionService;

    public ChiefBrokerBankAccountTransactionController(ChiefBrokerBankAccountTransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping
    public List<BankAccountTransaction> getAllBankAccountTransactionsByDepartment(@AuthenticationPrincipal AuthorizedUser authUser, Department department) {
        // Вывести список BankAccountTransaction сортированный по дате исполнение от новых к старым, которые принадлежат данному Department
        return transactionService.getAllBankAccountTransactionsByDepartment(department);
    }

    @GetMapping("/{id}")
    public BankAccountTransaction getBankAccountTransaction(@PathVariable Integer id) {
        // Вывести BankAccountTransaction
        return transactionService.getBankAccountTransaction(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccountTransaction createBankAccountTransaction(@Valid @RequestBody Stock stock, Integer amount, Operation operation,
                                                               @AuthenticationPrincipal AuthorizedUser authUser) throws InsufficientStocksException {
        // Создать новую BankAccountTransaction с автопростановкой текущих даты и времени
        // sender_bank_account - это BankAccount отдела
        // recipient_bank_account - это BankAccount биржи (он будет создан в качестве заглушки и для всех един)
        // Если покупка, то нужно также приписать StockInBankAccount к данному счету. Если продажа, то соответственно, удалить
        Department department = authUser.getUser().getDepartment();
        return transactionService.createBankAccountTransaction(stock, amount, operation, department);
    }
}
