package com.broker_manager.service;

import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Role;
import com.broker_manager.repository.UserRepository;
import com.broker_manager.util.error.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiefBrokerService {

    private final UserRepository userRepository;

    @Autowired
    public ChiefBrokerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllBrokersByDepartment(Department department) {
        return userRepository.findByDepartmentAndRole(department, Role.BROKER);
    }

    public User getBrokerByDepartment(Department department, Integer id) {
        return userRepository.findByDepartmentAndId(department, id)
                .orElseThrow(() -> new NotFoundException("Broker not found"));
    }

    public User createUser(User user, Integer chiefId) {
        checkChiefBrokerByRoleAndDepartment(user, chiefId);
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Integer id, User updatedUser, Integer chiefId) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        checkChiefBrokerByRoleAndDepartment(existingUser, chiefId);

        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());

        return userRepository.save(existingUser);
    }

    @Transactional
    public void deleteUser(Integer id, Integer chiefId) {
        User firedUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The user you want to fire does not exist"));

        checkChiefBrokerByRoleAndDepartment(firedUser, chiefId);

        userRepository.delete(firedUser);
    }

    private void checkChiefBrokerByRoleAndDepartment(User user, Integer chiefId) {
        User chiefUser = userRepository.findById(chiefId)
                .orElseThrow(() -> new NotFoundException("ChiefBroker not found"));

        if (chiefUser.getRole() == Role.BROKER) {
            throw new IllegalArgumentException("Only director or chief can create, update or delete");
        }
        if (!chiefUser.getDepartment().equals(user.getDepartment())) {
            throw new IllegalArgumentException("User belongs to a different department and cannot be created, updated or deleted");
        }
    }
}