package com.broker_manager.service.user;

import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Role;
import com.broker_manager.repository.UserRepository;
import com.broker_manager.util.error.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.broker_manager.util.UserUtil.checkNew;

@Service
public class ChiefBrokerUserService extends AbstractUserService {

    public ChiefBrokerUserService(UserRepository userRepository) {
        super(userRepository);
    }

    public List<User> getAllBrokersByDepartment(String department, User chiefUser) {
        Department checkingDepartment = checkDepartment(department);
        checkChiefBrokerByDepartment(checkingDepartment, chiefUser);
        return userRepository.findByDepartmentAndRole(checkingDepartment, Role.BROKER);
    }

    public User getBrokerByDepartment(String department, Integer id, User chiefUser) {
        Department checkingDepartment = checkDepartment(department);
        checkChiefBrokerByDepartment(checkingDepartment, chiefUser);
        return userRepository.findByDepartmentAndId(checkingDepartment, id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User createUser(User user, User chiefUser) {
        checkNew(user);
        checkChiefBrokerByDepartmentAndUserByRole(user, chiefUser);
        return prepareAndSave(user);
    }

    @Transactional
    public User updateUser(Integer id, User updatedUser, User chiefUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));


        checkChiefBrokerByDepartmentAndUserByRole(existingUser, chiefUser);
        return prepareAndSave(updatedUser);
    }

    @Transactional
    public void deleteUser(Integer id, User chiefUser) {
        User firedUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The user you want to fire does not exist"));

        checkChiefBrokerByDepartmentAndUserByRole(firedUser, chiefUser);
        userRepository.delete(firedUser);
    }

    public static Department checkDepartment(String department) {
        try {
            return Department.valueOf(department.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Department does not exist");
        }
    }

    public static void checkChiefBrokerByDepartment(Department userDepartment, User chiefUser) {
        if (!chiefUser.getDepartment().equals(userDepartment)) {
            throw new IllegalArgumentException("User belong to a different department and cannot be created, updated or deleted");
        }
    }

    public static void checkChiefBrokerByDepartmentAndUserByRole(User user, User chiefUser) {
        checkChiefBrokerByDepartment(user.getDepartment(), chiefUser);
        if (user.getRole() != Role.BROKER) {
            throw new IllegalArgumentException("Chief broker can create only brokers");
        }
    }
}