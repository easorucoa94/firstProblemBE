package com.truextend.firstProblem.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@CrossOrigin
	@GetMapping
	public List<StudentEntity> findAll() {
		return studentService.findAll();
	}

	@CrossOrigin
	@PostMapping
	public ResponseEntity<StudentEntity> save(@Valid @RequestBody StudentEntity studentEntity) {
		return ResponseEntity.ok(studentService.save(studentEntity));
	}

	@CrossOrigin
	@PutMapping("/{sStudentId}")
	public ResponseEntity<StudentEntity> update(@PathVariable String sStudentId,
			@Valid @RequestBody StudentEntity studentEntity) {
		Long lStudentId = Long.parseLong(sStudentId);
		studentEntity.setLStudentId(lStudentId);
		return ResponseEntity.ok(studentService.save(studentEntity));
	}

	@SuppressWarnings("rawtypes")
	@CrossOrigin
	@DeleteMapping("/{sStudentId}")
	public ResponseEntity delete(@PathVariable String sStudentId) {
		try {
			Long lStudentId = Long.parseLong(sStudentId);
			studentService.deleteById(lStudentId);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin
	@PostMapping(path = "/search")
	public ResponseEntity<Set<StudentEntity>> filter(@Valid @RequestBody StudentEntity studentEntity)
			throws JsonProcessingException {
		return ResponseEntity.ok(studentService.findByObjectFilter(studentEntity.getSStudentFirstName(),
				studentEntity.getSStudentLastName(), studentEntity.getStudentFilteredClasses()));
	}
}
