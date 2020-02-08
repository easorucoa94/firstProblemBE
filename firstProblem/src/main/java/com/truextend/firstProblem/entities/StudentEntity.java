package com.truextend.firstProblem.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@Table(name = "te_students")
public class StudentEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long lStudent_id;
	private String sStudent_firstName;
	private String sStudent_lastName;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "te_students_classes_relation", joinColumns = @JoinColumn(name = "lStudent_id"), inverseJoinColumns = @JoinColumn(name = "lClass_id"))
	@JsonIgnoreProperties("studentsInClass")
	private List<ClassEntity> studentClasses;

	protected StudentEntity() {
	}

	public StudentEntity(Long lStudent_id, String sStudent_firstName, String sStudent_lastName) {
		this.lStudent_id = lStudent_id;
		this.sStudent_firstName = sStudent_firstName;
		this.sStudent_lastName = sStudent_lastName;
	}

	public StudentEntity(Long lStudent_id, String sStudent_firstName, String sStudent_lastName,
			List<ClassEntity> studentClasses) {
		this.lStudent_id = lStudent_id;
		this.sStudent_firstName = sStudent_firstName;
		this.sStudent_lastName = sStudent_lastName;
		this.studentClasses = studentClasses;
	}

	public Long getLStudent_id() {
		return this.lStudent_id;
	}

	public void setLStudent_id(Long lStudent_id) {
		this.lStudent_id = lStudent_id;
	}

	public String getSStudent_firstName() {
		return this.sStudent_firstName;
	}

	public void setSStudent_firstName(String sStudent_firstName) {
		this.sStudent_firstName = sStudent_firstName;
	}

	public String getSStudent_lastName() {
		return this.sStudent_lastName;
	}

	public void setSStudent_lastName(String sStudent_lastName) {
		this.sStudent_lastName = sStudent_lastName;
	}

	public List<ClassEntity> getStudentClasses() {
		return this.studentClasses;
	}

	public void setStudentClasses(List<ClassEntity> studentClasses) {
		this.studentClasses = studentClasses;
	}
}
