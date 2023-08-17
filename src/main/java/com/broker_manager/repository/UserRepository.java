package com.broker_manager.repository;

import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByDepartmentAndRole(Department department, Role broker);

    List<User> findByRole(Role role);
}
