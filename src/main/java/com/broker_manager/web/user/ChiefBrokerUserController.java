package com.broker_manager.web.user;

import com.broker_manager.model.User;
import com.broker_manager.service.user.ChiefBrokerUserService;
import com.broker_manager.web.AuthorizedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ChiefBrokerUserController.REST_URL)
public class ChiefBrokerUserController {
    static final String REST_URL = "/chief_broker/users/{department}";

    private final ChiefBrokerUserService chiefBrokerUserService;

    public ChiefBrokerUserController(ChiefBrokerUserService chiefBrokerUserService) {
        this.chiefBrokerUserService = chiefBrokerUserService;
    }

    @GetMapping
    public List<User> getAllBrokersByDepartment(@PathVariable String department, @AuthenticationPrincipal AuthorizedUser authUser) {
        return chiefBrokerUserService.getAllBrokersByDepartment(department, authUser.getUser());
    }

    @GetMapping("/{id}")
    public User getBrokerByDepartment(@PathVariable String department, @PathVariable Integer id, @AuthenticationPrincipal AuthorizedUser authUser) {
        return chiefBrokerUserService.getBrokerByDepartment(department, id, authUser.getUser());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user, @AuthenticationPrincipal AuthorizedUser authUser) {
        return chiefBrokerUserService.createUser(user, authUser.getUser());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User updateUser(@PathVariable Integer id, @Valid @RequestBody User updatedUser, @AuthenticationPrincipal AuthorizedUser authUser) {
        return chiefBrokerUserService.updateUser(id, updatedUser, authUser.getUser());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id, @AuthenticationPrincipal AuthorizedUser authUser) {
        chiefBrokerUserService.deleteUser(id, authUser.getUser());
    }
}
