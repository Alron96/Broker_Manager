package com.broker_manager.web.user;

import com.broker_manager.model.User;
import com.broker_manager.service.DirectorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = DirectorController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DirectorController {
    static final String REST_URL = "/director";

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return directorService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id) {
        return directorService.getUser(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        return directorService.createUser(user);
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User updateUser(@PathVariable Integer id, @Valid @RequestBody User updatedUser) {
        return directorService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {
        directorService.deleteUser(id);
    }
}
