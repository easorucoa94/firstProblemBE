package com.truextend.firstProblem.entities;

import javax.persistence.*;

@Entity
@Table(name = "te_classes")
public class ClassEntity {

	@Id
	private String sClass_code;
	private String sClass_title;
	private String sClass_description;
	
	protected ClassEntity() {}
	
	public ClassEntity(String sClass_code, String sClass_title, String sClass_description) {
		this.sClass_code = sClass_code;
		this.sClass_title = sClass_title;
		this.sClass_description = sClass_description;
	}
	
	public String getSClass_code() {
		return this.sClass_code;
	}
	
	public void setSClass_code(String sClass_code) {
		this.sClass_code = sClass_code;
	}
	
	public String getSClass_title() {
		return this.sClass_title;
	}
	
	public void setSClass_title(String sClass_title) {
		this.sClass_title = sClass_title;
	}
	
	public String getSClass_description() {
		return this.sClass_description;
	}
	
	public void setSClass_description(String sClass_description) {
		this.sClass_description = sClass_description;
	}
}
