package com.truextend.firstProblem.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.truextend.firstProblem.entities.StudentEntity;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, String> {
	public List<StudentEntity> findAll();
}
