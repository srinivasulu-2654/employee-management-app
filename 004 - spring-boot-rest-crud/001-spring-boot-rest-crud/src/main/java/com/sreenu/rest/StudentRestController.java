package com.sreenu.rest;

import com.sreenu.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define @PostConstruct to load the student data ... only once

    @PostConstruct
    public  void loadData() {
        theStudents = new ArrayList<>();
        theStudents.add(new Student("ram","Sri"));
        theStudents.add(new Student("kri","shna"));
        theStudents.add(new Student("sita","devi"));

    }

    // define endpoint for "/students" - return arraylist

    @GetMapping("/students")
    public List<Student> gettheStudents() {

        return theStudents;
    }

    // define endpoint for "/student/{studentId}" -> return student at index

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // just index into the list... keep it simple for now
        
        //  Check the studentId again list size

        if(studentId >= theStudents.size() || studentId < 0)
        {
            throw  new StudentNotFoundException("Student id is not found - " + studentId);
        }

        return theStudents.get(studentId);
    }

    // Add an exception handle using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc)
    {
       // create a studentErrorresponse

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

       return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }


    // add another exception handler .. .to catch any exception that means like (asdsdn,sfsklfi like this) -> Catch all

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc)
    {
        // create a studentErrorresponse

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

}


// Here please explore about the @PostConstruct
