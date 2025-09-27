package com.sreenu.restcrudapiproject.democrudapi.rest;

import com.sreenu.restcrudapiproject.democrudapi.dao.EmployeeDAO;
import com.sreenu.restcrudapiproject.democrudapi.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeDAO employeeDAO;

    // quick and dirty: inject employee dao
    public EmployeeRestController(EmployeeDAO theEmployeeDAO)
    {
        employeeDAO = theEmployeeDAO;
    }

    //explose "/employees" and return list of employees

    @RequestMapping("/employees")
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }
}
