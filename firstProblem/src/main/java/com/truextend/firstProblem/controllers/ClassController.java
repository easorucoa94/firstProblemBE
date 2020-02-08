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
import com.truextend.firstProblem.entities.ClassEntity;
import com.truextend.firstProblem.repositories.ClassRepository;

@RestController
@RequestMapping(path = "/class")
public class ClassController {

	@Autowired
	ClassRepository classRepository;

	@GetMapping
	public List<ClassEntity> findAll() {
		return classRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<ClassEntity> save(@Valid @RequestBody ClassEntity classEntity) {
		return ResponseEntity.ok(classRepository.save(classEntity));
	}

	@PutMapping("/{sClassId}")
	public ResponseEntity<ClassEntity> update(@PathVariable String sClassId,
			@Valid @RequestBody ClassEntity classEntity) {
		Long lClassId = Long.parseLong(sClassId);
		classEntity.setLClassId(lClassId);
		return ResponseEntity.ok(classRepository.save(classEntity));
	}

	@DeleteMapping("/{sClassId}")
	public void delete(@PathVariable String sClassId) {
		Long lClassId = Long.parseLong(sClassId);
		classRepository.deleteById(lClassId);
	}
	@PostMapping(path = "/search")
	public ResponseEntity<List<ClassEntity>> filter(@Valid @RequestBody ClassEntity classEntity)
			throws JsonProcessingException {
		if (classEntity.getClassFilteredStudents().size() == 0) {
			classEntity.setClassFilteredStudents(null);
		}
		return ResponseEntity
				.ok(classRepository.findByObjectFilter(classEntity.getSClassCode(), classEntity.getSClassTitle(),
						classEntity.getSClassDescription(), classEntity.getClassFilteredStudents()));
	}

}
