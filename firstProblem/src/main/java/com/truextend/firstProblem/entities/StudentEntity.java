package com.truextend.firstProblem.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@Table(name = "te_students")
public class StudentEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lStudent_id")
	private Long lStudentId;
	@Column(name = "sStudent_firstName")
	private String sStudentFirstName;
	@Column(name = "sStudent_lastName")
	private String sStudentLastName;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "te_students_classes_relation", joinColumns = @JoinColumn(name = "lStudent_id"), inverseJoinColumns = @JoinColumn(name = "lClass_id"))
	@JsonIgnoreProperties("studentsInClass")
	private Set<ClassEntity> studentClasses;

	@Transient
	private List<Long> studentFilteredClasses;

	protected StudentEntity() {
	}

	public StudentEntity(Long lStudentId, String sStudentFirstName, String sStudentLastName) {
		this.lStudentId = lStudentId;
		this.sStudentFirstName = sStudentFirstName;
		this.sStudentLastName = sStudentLastName;
	}

	public StudentEntity(Long lStudentId, String sStudentFirstName, String sStudentLastName,
			Set<ClassEntity> studentClasses) {
		this.lStudentId = lStudentId;
		this.sStudentFirstName = sStudentFirstName;
		this.sStudentLastName = sStudentLastName;
		this.studentClasses = studentClasses;
	}

	public Long getLStudentId() {
		return this.lStudentId;
	}

	public void setLStudentId(Long lStudentId) {
		this.lStudentId = lStudentId;
	}

	public String getSStudentFirstName() {
		return this.sStudentFirstName;
	}

	public void setSStudentFirstName(String sStudentFirstName) {
		this.sStudentFirstName = sStudentFirstName;
	}

	public String getSStudentLastName() {
		return this.sStudentLastName;
	}

	public void setSStudentLastName(String sStudentLastName) {
		this.sStudentLastName = sStudentLastName;
	}

	public Set<ClassEntity> getStudentClasses() {
		return this.studentClasses;
	}

	public void setStudentClasses(Set<ClassEntity> studentClasses) {
		this.studentClasses = studentClasses;
	}

	public List<Long> getStudentFilteredClasses() {
		return this.studentFilteredClasses;
	}

	public void setStudentFilteredClasses(List<Long> studentFilteredClasses) {
		this.studentFilteredClasses = studentFilteredClasses;
	}
}
