package com.sreenu.springboot.restcruddemo.dao;

import com.sreenu.springboot.restcruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it no need to write to any code


}
