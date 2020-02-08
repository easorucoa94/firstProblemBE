package com.truextend.firstProblem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "te_students")
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long lStudent_id;
	private String sStudent_firstName;
	private String sStudent_lastName;

	protected StudentEntity() {
	}

	public StudentEntity(Long lStudent_id, String sStudent_firstName, String sStudent_lastName) {
		this.lStudent_id = lStudent_id;
		this.sStudent_firstName = sStudent_firstName;
		this.sStudent_lastName = sStudent_lastName;
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
}
