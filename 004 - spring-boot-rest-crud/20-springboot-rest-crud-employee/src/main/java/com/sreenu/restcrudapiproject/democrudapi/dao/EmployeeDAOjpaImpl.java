package com.sreenu.restcrudapiproject.democrudapi.dao;

import com.sreenu.restcrudapiproject.democrudapi.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOjpaImpl implements EmployeeDAO {

    //define filed for entitymanager

    private EntityManager entityManager;

    // set up constructor injection

    @Autowired
    public EmployeeDAOjpaImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }



    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);


        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        //return the result list

        return employees;
    }
}
