package com.truextend.firstProblem.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@Table(name = "te_classes")
public class ClassEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lClass_id")
	private Long lClassId;
	@Column(name = "sClass_code")
	private String sClassCode;
	@Column(name = "sClass_title")
	private String sClassTitle;
	@Column(name = "sClass_description")
	private String sClassDescription;

	@ManyToMany(mappedBy = "studentClasses", targetEntity = StudentEntity.class)
	@JsonIgnoreProperties("studentClasses")
	private Set<StudentEntity> studentsInClass;

	@Transient
	private List<Long> classFilteredStudents;

	protected ClassEntity() {
	}

	public ClassEntity(Long lClassId, String sClassCode, String sClassTitle, String sClassDescription) {
		this.lClassId = lClassId;
		this.sClassCode = sClassCode;
		this.sClassTitle = sClassTitle;
		this.sClassDescription = sClassDescription;
	}

	public ClassEntity(Long lClassId, String sClassCode, String sClassTitle, String sClassDescription,
			Set<StudentEntity> studentsInClass) {
		this.lClassId = lClassId;
		this.sClassCode = sClassCode;
		this.sClassTitle = sClassTitle;
		this.sClassDescription = sClassDescription;
		this.studentsInClass = studentsInClass;
	}

	public Long getLClassId() {
		return this.lClassId;
	}

	public void setLClassId(Long lClassId) {
		this.lClassId = lClassId;
	}

	public String getSClassCode() {
		return this.sClassCode;
	}

	public void setSClassCode(String sClassCode) {
		this.sClassCode = sClassCode;
	}

	public String getSClassTitle() {
		return this.sClassTitle;
	}

	public void setSClassTitle(String sClassTitle) {
		this.sClassTitle = sClassTitle;
	}

	public String getSClassDescription() {
		return this.sClassDescription;
	}

	public void setSClassDescription(String sClassDescription) {
		this.sClassDescription = sClassDescription;
	}

	public Set<StudentEntity> getStudentsInClass() {
		return this.studentsInClass;
	}

	public void setStudentsInClass(Set<StudentEntity> studentsInClass) {
		this.studentsInClass = studentsInClass;
	}

	public List<Long> getClassFilteredStudents() {
		return this.classFilteredStudents;
	}

	public void setClassFilteredStudents(List<Long> classFilteredStudents) {
		this.classFilteredStudents = classFilteredStudents;
	}
}
