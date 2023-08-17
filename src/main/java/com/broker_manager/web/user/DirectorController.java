package com.broker_manager.web.user;

import com.broker_manager.model.User;
import com.broker_manager.model.enums.Role;
import com.broker_manager.service.DirectorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/director")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/users")
    public List<User> getAllUsersByRole(@RequestParam(name = "role") Role role) {
        return directorService.getAllUsersByRole(role);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return directorService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        return directorService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        directorService.deleteUser(id);
    }
}
