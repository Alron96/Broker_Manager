package com.broker_manager.util;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Role;
import com.broker_manager.to.UserTo;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class UserUtil {

    public static User createNewFromTo(UserTo userTo, Department department, Role role, BankAccount... bankAccounts) {
        return new User(null, userTo.getFullName(), userTo.getEmail().toLowerCase(),
                userTo.getPhoneNumber(), userTo.getPassword(), department, role, List.of(bankAccounts));
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setFullName(userTo.getFullName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPhoneNumber(userTo.getPhoneNumber());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getFullName(), user.getEmail(), user.getPhoneNumber(), user.getPassword());
    }
}