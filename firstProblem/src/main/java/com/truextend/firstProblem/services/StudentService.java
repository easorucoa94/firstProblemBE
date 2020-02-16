package com.truextend.firstProblem.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.truextend.firstProblem.entities.StudentEntity;

@Service
public interface StudentService {
	public List<StudentEntity> findAll();
	
	public StudentEntity findByLStudentId(Long lStudentId);

	public StudentEntity save(StudentEntity studentEntity);

	public void deleteById(Long lStudentId);

	public Set<StudentEntity> findByObjectFilter(String sStudentFirstName, String sStudentLastName,
			List<Long> studentFilteredClasses);
}
