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
	
	@PutMapping("/{sClass_id}")
	public ResponseEntity<ClassEntity> update(@PathVariable String sClass_id, @Valid @RequestBody ClassEntity classEntity) {
		Long lClass_id = Long.parseLong(sClass_id);
		classEntity.setLClass_id(lClass_id);
		return ResponseEntity.ok(classRepository.save(classEntity));
	}

	@DeleteMapping("/{sClass_id}")
	public void delete(@PathVariable String sClass_id) {
		Long lClass_id = Long.parseLong(sClass_id);
		classRepository.deleteById(lClass_id);
	}

}
