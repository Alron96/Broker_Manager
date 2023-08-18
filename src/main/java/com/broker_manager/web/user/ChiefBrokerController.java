package com.broker_manager.web.user;

import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import com.broker_manager.service.ChiefBrokerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ChiefBrokerController.REST_URL)
public class ChiefBrokerController {
    static final String REST_URL = "/chief_broker";

    private final ChiefBrokerService chiefBrokerService;

    public ChiefBrokerController(ChiefBrokerService chiefBrokerService) {
        this.chiefBrokerService = chiefBrokerService;
    }

    @GetMapping("/users/{department}")
    public List<User> getAllBrokersByDepartment(@PathVariable String department) {
        Department departmentUpperCase = Department.valueOf(department.toUpperCase());
        return chiefBrokerService.getAllBrokersByDepartment(departmentUpperCase);
    }

    @GetMapping("/users/{department}/{id}")
    public User getBrokerByDepartment(@PathVariable String department, @PathVariable Integer id) {
        Department departmentUpperCase = Department.valueOf(department.toUpperCase());
        return chiefBrokerService.getBrokerByDepartment(departmentUpperCase, id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user, @RequestParam Integer chiefId) {
        return chiefBrokerService.createUser(user, chiefId);
    }

    @PutMapping("/users/{department}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User updateUser(@PathVariable Integer id, @Valid @RequestBody User updatedUser, @RequestParam Integer chiefId) {
        return chiefBrokerService.updateUser(id, updatedUser, chiefId);
    }

    @DeleteMapping("/users/{department}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id, @RequestParam Integer chiefId) {
        chiefBrokerService.deleteUser(id, chiefId);
    }
}
