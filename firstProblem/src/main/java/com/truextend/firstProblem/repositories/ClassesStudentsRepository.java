package com.truextend.firstProblem.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.truextend.firstProblem.entities.ClassesStudentsEntity;

@Repository
public interface ClassesStudentsRepository extends CrudRepository<ClassesStudentsEntity, Long> {
	public List<ClassesStudentsEntity> findAll();
}
