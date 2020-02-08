package com.truextend.firstProblem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.truextend.firstProblem.entities.StudentEntity;
import com.truextend.firstProblem.services.StudentService;
import com.truextend.firstProblem.services.impl.StudentServiceImpl;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

	@Autowired
	StudentService studentService = new StudentServiceImpl();

	@GetMapping
	public List<StudentEntity> findAll() {
		return studentService.findAll();
	}

	@PostMapping
	public ResponseEntity<StudentEntity> save(@Valid @RequestBody StudentEntity studentEntity) {
		return ResponseEntity.ok(studentService.save(studentEntity));
	}

	@PutMapping("/{sStudentId}")
	public ResponseEntity<StudentEntity> update(@PathVariable String sStudentId,
			@Valid @RequestBody StudentEntity studentEntity) {
		Long lStudentId = Long.parseLong(sStudentId);
		studentEntity.setLStudentId(lStudentId);
		return ResponseEntity.ok(studentService.save(studentEntity));
	}

	@DeleteMapping("/{sStudentId}")
	public void delete(@PathVariable String sStudentId) {
		Long lStudentId = Long.parseLong(sStudentId);
		studentService.deleteById(lStudentId);
	}

	@PostMapping(path = "/search")
	public ResponseEntity<List<StudentEntity>> filter(@Valid @RequestBody StudentEntity studentEntity)
			throws JsonProcessingException {
		return ResponseEntity.ok(studentService.findByObjectFilter(studentEntity.getSStudentFirstName(),
				studentEntity.getSStudentLastName(), studentEntity.getStudentFilteredClasses()));
	}
}
