package com.sreenu.demo.rest;

import com.sreenu.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    @PostConstruct
    public void loadData() {

        theStudents = new ArrayList<>();
        theStudents.add(new Student("poornima","patel"));
        theStudents.add(new Student("sreenu","polukanti"));
        theStudents.add(new Student("abhey","pandey"));
    }

    @GetMapping("/students")
    List<Student> getStudents() {
//        List<Student> theStudents = new ArrayList<>();
//        theStudents.add(new Student("poornima","patel"));
//        theStudents.add(new Student("sreenu","polukanti"));
//        theStudents.add(new Student("abhey","pandey"));

        return theStudents;
    }

    //define endpoint or "/students/{studentId}" - return student at index

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        //just index into the list... keep it simple for now

        // check the studentId again list size

        if ( (studentId >= theStudents.size()) || (studentId < 0) ) {
            throw new StudentNotFoundException("Student id is not found: " + studentId);
        }

        return theStudents.get(studentId);
    }

    // Add an exception handler using @ExceptionHandler

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exe) {

        // create a StudentErrorReponse

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exe.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return responseEntity

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }


}
