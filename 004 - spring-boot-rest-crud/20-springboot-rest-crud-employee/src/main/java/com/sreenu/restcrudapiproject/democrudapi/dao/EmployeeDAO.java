package com.sreenu.restcrudapiproject.democrudapi.dao;

import com.sreenu.restcrudapiproject.democrudapi.entity.Employee;

import java.util.*;

public interface EmployeeDAO {

    List<Employee> findAll();
}
