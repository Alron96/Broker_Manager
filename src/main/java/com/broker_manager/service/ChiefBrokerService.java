package com.broker_manager.service;

import com.broker_manager.util.error.NotFoundException;
import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Role;
import com.broker_manager.repository.UserRepository;
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

    public List<User> getBrokersByDepartment(Department department) {
        return userRepository.findByDepartmentAndRole(department, Role.BROKER);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Integer id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setDepartment(updatedUser.getDepartment());
        existingUser.setRole(updatedUser.getRole());

        return userRepository.save(existingUser);
    }

    @Transactional
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ChiefBroker not found"));

        if (user.getRole() != Role.BROKER) {
            throw new IllegalArgumentException("Only brokers can be fired");
        }

        // Проверить отдел пользователя
        if (!user.getDepartment().equals(user.getDepartment())) {
            throw new IllegalArgumentException("User belongs to a different department and cannot be fired");
        }
        userRepository.delete(user);
    }
}