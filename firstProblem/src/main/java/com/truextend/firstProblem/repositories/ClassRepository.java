package com.truextend.firstProblem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.truextend.firstProblem.entities.ClassEntity;

@Repository
public interface ClassRepository extends CrudRepository<ClassEntity, Long> {
	public List<ClassEntity> findAll();

	@Query(" FROM ClassEntity classEntity LEFT JOIN classEntity.studentsInClass classStudents WHERE (?4 IS NULL OR classStudents.lStudentId IN ?4) AND classEntity.sClassCode LIKE %?1% AND classEntity.sClassTitle LIKE %?2% AND classEntity.sClassDescription LIKE %?3%")
	public List<ClassEntity> findByObjectFilter(String sClassCode, String sClassTitle, String sClassDescription,
			List<Long> classFilteredStudents);
}
