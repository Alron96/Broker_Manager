package com.broker_manager.web.user;

import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import com.broker_manager.service.user.ChiefBrokerUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.broker_manager.web.AuthorizedUser.authId;

@RestController
@RequestMapping(ChiefBrokerUserController.REST_URL)
public class ChiefBrokerUserController {
    static final String REST_URL = "/chief_broker/users/{department}";

    private final ChiefBrokerUserService chiefBrokerUserService;

    public ChiefBrokerUserController(ChiefBrokerUserService chiefBrokerUserService) {
        this.chiefBrokerUserService = chiefBrokerUserService;
    }

    @GetMapping
    public List<User> getAllBrokersByDepartment(@PathVariable String department) {
        Department departmentUpperCase = Department.valueOf(department.toUpperCase());
        return chiefBrokerUserService.getAllBrokersByDepartment(departmentUpperCase);
    }

    @GetMapping("/{id}")
    public User getBrokerByDepartment(@PathVariable String department, @PathVariable Integer id) {
        Department departmentUpperCase = Department.valueOf(department.toUpperCase());
        return chiefBrokerUserService.getBrokerByDepartment(departmentUpperCase, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        return chiefBrokerUserService.createUser(user, authId());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User updateUser(@PathVariable Integer id, @Valid @RequestBody User updatedUser) {
        return chiefBrokerUserService.updateUser(id, updatedUser, authId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {
        chiefBrokerUserService.deleteUser(id, authId());
    }
}
