package com.sreenu.springboot.restcruddemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sreenu.springboot.restcruddemo.dao.EmployeeDAO;
import com.sreenu.springboot.restcruddemo.entity.Employee;
import com.sreenu.springboot.restcruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

//        private EmployeeDAO employeeDAO;

    private EmployeeService employeeService;

    private ObjectMapper objectMapper;

        // inject employee DAO(use constructor injection here)

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, ObjectMapper objectMapper)
    {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }

    // expose "/employees" and return a list of employees

    @GetMapping("/employees")
    public List<Employee> findAllEmployees() {
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    // add mapping for POST / employees - add new employeee

    @PostMapping("/employees")
    public Employee addEmplyee(@RequestBody Employee theEmployee) {

        // also just in case they pass an id in JSON....set id to 0
        // this is to force a save of new item... instead of update

        theEmployee.setId(0); // this is for like using int
//        theEmployee.setId(null); // this is for if we use Integer

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // add mapping for PUT /employees - update existing employees
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // add mapping for DELETE /employees/{emplyeeId} - delete employee

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee tempEmployee = employeeService.findById(employeeId);

        // throws an exception

        if(tempEmployee == null) {
            throw new RuntimeException("Employee id is not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return  "Deleted employee id - " + employeeId;
    }

    //add mapping for PATCH /employees/{employeeId} - patch employee.. partial update

    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId,@RequestBody Map<String,Object> patchPayLoad) {

        Employee theEmployee = employeeService.findById(employeeId);

        // throws exception if null

        if(theEmployee == null) {
            throw new RuntimeException("Emplyee id is not found - " + employeeId);
        }

        // throw Exception if request body contains "id" key

        if(patchPayLoad.containsKey("id")) {
            throw new RuntimeException("Employee id not allowed in request body - " + employeeId);
        }

        Employee patchedEmployee = apply(patchPayLoad, theEmployee);

        Employee dbEmployee = employeeService.save(patchedEmployee);

        return dbEmployee;
    }

    private Employee apply(Map<String,Object>patchPayLoad, Employee theEmployee) {

        // convert the employee object to json object node

        ObjectNode employeeNode = objectMapper.convertValue(theEmployee,ObjectNode.class);

        // Convert the patchpayload map to a JSON object node

        ObjectNode patchNode = objectMapper.convertValue(patchPayLoad,ObjectNode.class);

        // Merge the patch updated into the employee Node

        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode,Employee.class);
    }


}
