package com.truextend.firstProblem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truextend.firstProblem.entities.StudentEntity;
import com.truextend.firstProblem.repositories.StudentRepository;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping
	public List<StudentEntity> findAll() {
		return studentRepository.findAll();
	}
	
	@DeleteMapping("/delete/{sStudent_id}")
    public void delete(@PathVariable String sStudent_id) {
		Long lStudent_id = Long.parseLong(sStudent_id);
		studentRepository.deleteById(lStudent_id);
    }
}
