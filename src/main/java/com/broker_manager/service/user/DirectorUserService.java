package com.broker_manager.service.user;

import com.broker_manager.model.User;
import com.broker_manager.repository.UserRepository;
import com.broker_manager.util.error.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.broker_manager.util.UserUtil.checkNew;

@Service
public class DirectorUserService extends AbstractUserService {

    public DirectorUserService(UserRepository userRepository) {
        super(userRepository);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User createUser(User user) {
        checkNew(user);
        return prepareAndSave(user);
    }

    @Transactional
    public User updateUser(Integer id, User updatedUser) {
        updatedUser.setId(id);
        return prepareAndSave(updatedUser);
    }

    @Transactional
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(user);
    }
}