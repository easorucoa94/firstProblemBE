package com.truextend.firstProblem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "te_classes_students_relations")
public class ClassesStudentsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long lStudentClassesRelation_id;

	@ManyToOne
    @MapsId("lStudent_id")
    @JoinColumn(name = "lStudent_id")
    StudentEntity studentEntity;
 
    @ManyToOne
    @MapsId("lClass_id")
    @JoinColumn(name = "lClass_id")
    ClassEntity classEntity;

	protected ClassesStudentsEntity() {
	}

	public ClassesStudentsEntity(Long lStudentClassesRelation_id, StudentEntity studentEntity, ClassEntity classEntity) {
		this.lStudentClassesRelation_id = lStudentClassesRelation_id;
		this.studentEntity = studentEntity;
		this.classEntity = classEntity;
	}
	
	public Long getLStudentClassesRelation_id() {
		return this.lStudentClassesRelation_id;
	}
	
	public void setLStudentClassesRelation_id(Long lStudentClassesRelation_id) {
		this.lStudentClassesRelation_id = lStudentClassesRelation_id;
	}
	
	public StudentEntity getStudentEntity() {
		return this.studentEntity;
	}
	
	public void setStudentEntity(StudentEntity studentEntity) {
		this.studentEntity = studentEntity;
	}
	
	public ClassEntity getClassEntity() {
		return this.classEntity;
	}
	
	public void setClassEntity(ClassEntity classEntity) {
		this.classEntity = classEntity;
	}

}
