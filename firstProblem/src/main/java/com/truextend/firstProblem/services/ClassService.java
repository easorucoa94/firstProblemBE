package com.truextend.firstProblem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.truextend.firstProblem.entities.ClassEntity;

@Service
public interface ClassService {
	public List<ClassEntity> findAll();

	public ClassEntity save(ClassEntity classEntity);

	public void deleteById(Long lClassId);

	public List<ClassEntity> findByObjectFilter(String sClassCode, String sClassTitle, String sClassDescription,
			List<Long> classFilteredStudents);
}
