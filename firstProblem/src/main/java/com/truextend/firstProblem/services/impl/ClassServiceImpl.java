package com.truextend.firstProblem.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truextend.firstProblem.entities.ClassEntity;
import com.truextend.firstProblem.repositories.ClassRepository;
import com.truextend.firstProblem.services.ClassService;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	ClassRepository classRepository;

	@Override
	public List<ClassEntity> findAll() {
		return classRepository.findAll();
	}

	@Override
	public ClassEntity save(ClassEntity classEntity) {
		return classRepository.save(classEntity);
	}

	@Override
	public void deleteById(Long lClassId) {
		classRepository.deleteById(lClassId);
	}

	@Override
	public List<ClassEntity> findByObjectFilter(String sClassCode, String sClassTitle, String sClassDescription,
			List<Long> classFilteredStudents) {
		if (classFilteredStudents.size() == 0) {
			classFilteredStudents = null;
		}
		return classRepository.findByObjectFilter(sClassCode, sClassTitle, sClassDescription, classFilteredStudents);
	}
}
