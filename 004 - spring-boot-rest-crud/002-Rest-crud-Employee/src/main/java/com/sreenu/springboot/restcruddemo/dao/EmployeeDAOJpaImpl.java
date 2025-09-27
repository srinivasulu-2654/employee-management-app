package com.sreenu.springboot.restcruddemo.dao;

import com.sreenu.springboot.restcruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    // define field for EntityManager

    private EntityManager entityManager;

    // set up constructor injection

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        // create q query
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);


        //create a query and get the result
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        // get employee
        Employee theemployee = entityManager.find(Employee.class,theId);

        // return the employee
        return theemployee;
    }

    @Override
    public Employee save(Employee theEmployee) {

        // save employee
        Employee dbEmployee = entityManager.merge(theEmployee);

        //return the dbEmployee

        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {

        Employee theemployee = entityManager.find(Employee.class,theId);

        entityManager.remove(theemployee);

    }
}
