package com.truextend.firstProblem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<ClassEntity> save(@Valid @RequestBody ClassEntity classEntity) {
		return ResponseEntity.ok(classRepository.save(classEntity));
	}
	
}
