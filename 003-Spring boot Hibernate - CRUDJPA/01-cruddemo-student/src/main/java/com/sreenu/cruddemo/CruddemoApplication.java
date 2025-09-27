package com.sreenu.cruddemo;

import com.sreenu.cruddemo.dao.StudentDAO;
import com.sreenu.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.*;

//ALTER TABLE student_tracker.student auto_increment = 3000 -> query to increment from 3000
//truncate student_tracker.student -> again come to normal

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args)  {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
//			createStudent(studentDAO);
			createMultipleStudents(studentDAO);
//			readStudent(studentDAO);
//			queryForStudents(studentDAO);
//			queryForStudentsByLastName(studentDAO);

//			updateStudent(studentDAO);

//			deleteStudent(studentDAO);

//			deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {

		System.out.println("Deleting all the students...");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted num of row: " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		// delete the student

		int studentId = 3;

		System.out.println("Deleting the student id: " + studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {

		// retrive student based on the id
		int studentId = 1;
		System.out.println("Getting the student with id: " + studentId);
		Student myStudent = studentDAO.findById(studentId);

		// change the first name to "scooby"
		System.out.println("Updating the student...");
		myStudent.setFirstName("Scooby");

		//update the student
		studentDAO.update(myStudent);

		//display the updated student
		System.out.println("Displaying the student: " + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findByLastName("kukka");


		// display the students

		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {

		// get a list of the students
		List<Student> theStudents = studentDAO.findAll();


		// display the list of the students
		for(Student tempStudents:theStudents) {
			System.out.println(tempStudents);
		}
	}

	private void readStudent(StudentDAO studentDAO) {

		// create a student object
		System.out.println("Creating the student object....");
		Student tempStudent = new Student("pandi","kukka","jooli@gmail.com");

		//save the element
		System.out.println("Saving the student object....");
		studentDAO.save(tempStudent);

		// display id of the saved object
		System.out.println("Saved student. Generated id: " + tempStudent.getId());

		//retrieve student based on the id : Primary key
		System.out.println("\nRetriving the student with id: " + tempStudent.getId());
		Student myStudent = studentDAO.findById(tempStudent.getId());

		//display student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		//create multiple students
		System.out.println("Creating 3 student objects...");
		Student tempStudent1 = new Student("John","Doe","john@gmail.com");
		Student tempStudent2 = new Student("krish","pre","krish@gmail.com");
		Student tempStudent3 = new Student("ram","du","ram@gmail.com");

		// save the student objects
		System.out.println("Saving the student objects...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);


	}

	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating the student object...");
		Student tempStudent = new Student("Paul","Doe","abc@gmail.com");

		//save the student object
		System.out.println("Saving the student....");
		studentDAO.save(tempStudent);

		// display id of the saved object
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}


}
