package com.truextend.firstProblem.controllers;

import java.util.List;
import java.util.Optional;
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
import com.truextend.firstProblem.entities.ClassEntity;
import com.truextend.firstProblem.services.ClassService;
import com.truextend.firstProblem.services.impl.ClassServiceImpl;

@RestController
@RequestMapping(path = "/class")
public class ClassController {

	@Autowired
	ClassService classService = new ClassServiceImpl();

	@CrossOrigin
	@GetMapping
	public List<ClassEntity> findAll() {
		return classService.findAll();
	}

	@CrossOrigin
	@PostMapping
	public ResponseEntity<ClassEntity> save(@Valid @RequestBody ClassEntity classEntity) {
		return ResponseEntity.ok(classService.save(classEntity));
	}

	@SuppressWarnings("rawtypes")
	@CrossOrigin
	@PutMapping("/{sClassId}")
	public ResponseEntity update(@PathVariable String sClassId,
			@Valid @RequestBody ClassEntity classEntity) {
		Optional<String> classId = Optional.ofNullable(sClassId);
		if (classId.isEmpty()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			Long lClassId = Long.parseLong(sClassId);
			Optional<ClassEntity> foundClass = Optional.ofNullable(classService.findByLClassId(lClassId));
			if (foundClass.isPresent()) {
				classEntity.setLClassId(lClassId);
				return ResponseEntity.ok(classService.save(classEntity));
			} else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		}		
	}

	@SuppressWarnings("rawtypes")
	@CrossOrigin
	@DeleteMapping("/{sClassId}")
	public ResponseEntity delete(@PathVariable String sClassId) {
		Optional<String> classId = Optional.ofNullable(sClassId);
		if (classId.isEmpty()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			Long lClassId = Long.parseLong(sClassId);
			Optional<ClassEntity> foundClass = Optional.ofNullable(classService.findByLClassId(lClassId));
			if (foundClass.isPresent()) {
				classService.deleteById(lClassId);
				return new ResponseEntity(HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@CrossOrigin
	@PostMapping(path = "/search")
	public ResponseEntity<Set<ClassEntity>> filter(@Valid @RequestBody ClassEntity classEntity)
			throws JsonProcessingException {
		return ResponseEntity
				.ok(classService.findByObjectFilter(classEntity.getSClassCode(), classEntity.getSClassTitle(),
						classEntity.getSClassDescription(), classEntity.getClassFilteredStudents()));
	}

}
