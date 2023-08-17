package com.broker_manager.service;

import com.broker_manager.exception.NotFoundException;
import com.broker_manager.model.User;
import com.broker_manager.model.enums.Role;
import com.broker_manager.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {

    private final UserRepository userRepository;

    public DirectorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsersByRole(Role role) {
        return userRepository.findByRole(role);
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
                .orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(user);
    }
}