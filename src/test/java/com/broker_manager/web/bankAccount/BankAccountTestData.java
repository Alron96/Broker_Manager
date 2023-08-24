package com.broker_manager.web.bankAccount;

import com.broker_manager.MatcherFactory;
import com.broker_manager.model.BankAccount;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Type;

import java.util.List;

import static com.broker_manager.web.stockInBankAccount.StockInBankAccountTestData.STOCKS_IN_BANK_ACCOUNT_CONSULTING;
import static com.broker_manager.web.user.UserTestData.*;

public class BankAccountTestData {
    public static final MatcherFactory.Matcher<BankAccount> BANK_ACCOUNT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(BankAccount.class);
    public static final MatcherFactory.Matcher<BankAccount> BANK_ACCOUNT_MATCHER_WITHOUT_STOCKS = MatcherFactory.usingIgnoringFieldsComparator(BankAccount.class, "stockInBankAccount");

    public static final int BANK_ACCOUNT_COMPANY_ID = 1;
    public static final int BANK_ACCOUNT_CONSULTING_ID = 2;
    public static final int BANK_ACCOUNT_PERSONAL_EMPTY_ID = 3;
    public static final int BANK_ACCOUNT_EXCHANGE_ID = 4;

    public static final BankAccount BANK_ACCOUNT_COMPANY = new BankAccount(BANK_ACCOUNT_COMPANY_ID, "COMPANY_ACCOUNT",
            901_123_321.05, Department.COMPANY, Type.COMPANY, List.of(DIRECTOR), null);
    public static final BankAccount BANK_ACCOUNT_CONSULTING = new BankAccount(BANK_ACCOUNT_CONSULTING_ID, "CONSULTING_ACCOUNT",
            1_566_018.33, Department.CONSULTING, Type.DEPARTMENT, List.of(DIRECTOR, CHIEF_BROKER_CONSULTING, BROKER_CONSULTING_1, BROKER_CONSULTING_2),
            STOCKS_IN_BANK_ACCOUNT_CONSULTING);
    public static final BankAccount BANK_ACCOUNT_PERSONAL_EMPTY = new BankAccount(BANK_ACCOUNT_PERSONAL_EMPTY_ID, "PERSONAL_ACCOUNT",
            0.00, Department.ANALYTICAL, Type.PERSONAL, List.of(BROKER_ANALYTICAL_1), null);
    public static final BankAccount BANK_ACCOUNT_EXCHANGE = new BankAccount(BANK_ACCOUNT_EXCHANGE_ID, "EXCHANGE", 100_000_000_000.00,
            Department.EXCHANGE, Type.EXCHANGE, List.of(), null);

    public static final List<BankAccount> BANK_ACCOUNTS_FOR_DIRECTOR = List.of(BANK_ACCOUNT_COMPANY, BANK_ACCOUNT_CONSULTING,
            BANK_ACCOUNT_PERSONAL_EMPTY);
    public static final List<BankAccount> BANK_ACCOUNTS_FOR_CONSULTING = List.of(BANK_ACCOUNT_CONSULTING);

    public static BankAccount getNewBankAccount() {
        return new BankAccount(null, "ANALYTICAL_ACCOUNT", 2_000_000.00, Department.ANALYTICAL, Type.DEPARTMENT,
                List.of(DIRECTOR, CHIEF_BROKER_ANALYTICAL, BROKER_ANALYTICAL_1, BROKER_ANALYTICAL_2), null);
    }
}