package com.truextend.firstProblem.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.truextend.firstProblem.entities.StudentEntity;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
	public List<StudentEntity> findAll();
	
	@Query(" FROM StudentEntity studentEntity WHERE studentEntity.lStudentId = ?1")
	public StudentEntity findByLStudentId(Long lStudentId);

	@Query(" FROM StudentEntity studentEntity LEFT JOIN studentEntity.studentClasses studentClasses WHERE (?3 IS NULL OR studentClasses.lClassId IN ?3) AND studentEntity.sStudentFirstName LIKE %?1% AND studentEntity.sStudentLastName LIKE %?2%")
	public Set<StudentEntity> findByObjectFilter(String sStudentFirstName, String sStudentLastName,
			List<Long> studentFilteredClasses);
}
