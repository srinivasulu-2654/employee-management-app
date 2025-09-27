package com.sreenu.cruddemo.dao;

import com.sreenu.cruddemo.entity.Student;

import java.util.*;

public interface StudentDAO {

    public void save(Student theStudent);

    Student findById(Integer Id);

    List<Student> findAll();

    List<Student> findByLastName(String theLastName);

    void  update(Student theStudent);

    void delete(Integer id);

    int deleteAll();
}
