package com.truextend.firstProblem.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truextend.firstProblem.entities.StudentEntity;
import com.truextend.firstProblem.repositories.StudentRepository;
import com.truextend.firstProblem.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<StudentEntity> findAll() {
		return studentRepository.findAll();
	}
	
	public StudentEntity findByLStudentId(Long lStudentId) {
		return studentRepository.findByLStudentId(lStudentId);
	}

	@Override
	public StudentEntity save(StudentEntity studentEntity) {
		return studentRepository.save(studentEntity);
	}

	@Override
	public void deleteById(Long lStudentId) {
		studentRepository.deleteById(lStudentId);
	}

	@Override
	public Set<StudentEntity> findByObjectFilter(String sStudentFirstName, String sStudentLastName,
			List<Long> studentFilteredClasses) {
		if (studentFilteredClasses.size() == 0) {
			studentFilteredClasses = null;
		}
		return studentRepository.findByObjectFilter(sStudentFirstName, sStudentLastName, studentFilteredClasses);
	}

}
