package com.student.restapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.student.restapi.entity.Student;
import com.student.restapi.repository.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository repo;
	
	Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		List<Student> students = repo.findAll();
		logger.info("Get students data");
		return students;
	}
	
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();
		logger.info("Student Id: " +id);
		return student;
	}
	
	@PostMapping("/student/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createStudent(@RequestBody Student student) {
		repo.save(student);
	}
	
	@PutMapping("/student/update/{id}")
	public Student updateStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();
		student.setName("Mahesh");
		student.setPercentage(70);
		student.setBranch("IT");
		repo.save(student);
		return student;
	}
	
	@DeleteMapping("/student/delete/{id}")
	public void removeStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();
		repo.delete(student);
	}
}
