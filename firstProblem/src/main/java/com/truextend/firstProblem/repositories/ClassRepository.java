package com.truextend.firstProblem.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.truextend.firstProblem.entities.ClassEntity;

@Repository
public interface ClassRepository extends CrudRepository<ClassEntity, String> {
	List<ClassEntity> findAll();
}
