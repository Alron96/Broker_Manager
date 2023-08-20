package com.broker_manager.web.user;

import com.broker_manager.model.User;
import com.broker_manager.service.user.DirectorUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = DirectorUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DirectorUserController {
    static final String REST_URL = "/director/users";

    private final DirectorUserService directorUserService;

    public DirectorUserController(DirectorUserService directorUserService) {
        this.directorUserService = directorUserService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return directorUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return directorUserService.getUser(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        return directorUserService.createUser(user);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User updateUser(@PathVariable Integer id, @Valid @RequestBody User updatedUser) {
        return directorUserService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {
        directorUserService.deleteUser(id);
    }
}
