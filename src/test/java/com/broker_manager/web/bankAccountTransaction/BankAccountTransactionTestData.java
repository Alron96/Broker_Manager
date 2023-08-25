package com.broker_manager.web.bankAccountTransaction;

import com.broker_manager.MatcherFactory;
import com.broker_manager.model.BankAccountTransaction;
import com.broker_manager.model.enums.Operation;

import java.time.LocalDateTime;
import java.util.List;

import static com.broker_manager.web.bankAccount.BankAccountTestData.*;
import static com.broker_manager.web.stok.StockTestData.*;
import static com.broker_manager.web.user.UserTestData.*;

public class BankAccountTransactionTestData {
    public static final MatcherFactory.Matcher<BankAccountTransaction> BANK_ACCOUNT_TRANSACTION_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(BankAccountTransaction.class);

    public static final int BANK_ACCOUNT_TRANSACTION_BUY_ID = 1;
    public static final int BANK_ACCOUNT_TRANSACTION_SELL_ID = 2;
    public static final int BANK_ACCOUNT_TRANSACTION_SALARY_ID = 3;
    public static final int BANK_ACCOUNT_TRANSACTION_TRANSFER_ID = 4;

    public static final BankAccountTransaction BANK_ACCOUNT_TRANSACTION_BUY = new BankAccountTransaction(BANK_ACCOUNT_TRANSACTION_BUY_ID,
            Operation.BUY, 5020.00, LocalDateTime.of(2023, 8, 20, 18, 20), BANK_ACCOUNT_CONSULTING,
            BANK_ACCOUNT_EXCHANGE, BROKER_CONSULTING_1, 50,100.4,  STOCK_1);
    public static final BankAccountTransaction BANK_ACCOUNT_TRANSACTION_SELL = new BankAccountTransaction(BANK_ACCOUNT_TRANSACTION_SELL_ID,
            Operation.SELL, 24030, LocalDateTime.of(2023, 8, 22, 13, 45), BANK_ACCOUNT_EXCHANGE,
            BANK_ACCOUNT_CONSULTING, BROKER_CONSULTING_2, 30, 801.00, STOCK_8);
    public static final BankAccountTransaction BANK_ACCOUNT_TRANSACTION_SALARY = new BankAccountTransaction(BANK_ACCOUNT_TRANSACTION_SALARY_ID,
            Operation.SALARY, 110.56, LocalDateTime.of(2023, 8, 23, 10, 10),
            BANK_ACCOUNT_COMPANY, BANK_ACCOUNT_PERSONAL_EMPTY, DIRECTOR, 0, 0, null);
    public static final BankAccountTransaction BANK_ACCOUNT_TRANSACTION_TRANSFER = new BankAccountTransaction(BANK_ACCOUNT_TRANSACTION_TRANSFER_ID,
            Operation.TRANSFER, 200_000.00, LocalDateTime.of(2023, 8, 22, 9, 25),
            BANK_ACCOUNT_COMPANY, BANK_ACCOUNT_CONSULTING, DIRECTOR, 0, 0, null);

    public static final List<BankAccountTransaction> TRANSACTIONS_FOR_DIRECTOR = List.of(BANK_ACCOUNT_TRANSACTION_BUY,
            BANK_ACCOUNT_TRANSACTION_SELL, BANK_ACCOUNT_TRANSACTION_SALARY, BANK_ACCOUNT_TRANSACTION_TRANSFER);
    public static final List<BankAccountTransaction> TRANSACTIONS_FOR_CHIEF_BROKER_CONSULTING = List.of(BANK_ACCOUNT_TRANSACTION_BUY,
            BANK_ACCOUNT_TRANSACTION_SELL, BANK_ACCOUNT_TRANSACTION_TRANSFER);
    public static final List<BankAccountTransaction> TRANSACTIONS_FOR_BROKER_ANALYTICAL_1 = List.of(BANK_ACCOUNT_TRANSACTION_SALARY);
}