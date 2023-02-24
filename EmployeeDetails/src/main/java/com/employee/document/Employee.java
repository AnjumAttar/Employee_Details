package com.employee.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employee")
public class Employee {
	
	@Id
	private int id;
	private String first_name; 
	private String last_name;
	private int age;
	private double ctc;
	private String organisation;
	
	
	public Employee() {
		
	}


	public Employee(int id, String first_name, String last_name, int age, double ctc, String organisation) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.ctc = ctc;
		this.organisation = organisation;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public double getCtc() {
		return ctc;
	}


	public void setCtc(double ctc) {
		this.ctc = ctc;
	}


	public String getOrganisation() {
		return organisation;
	}


	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}


	@Override
	public String toString() {
		return "EmployeeDetails [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", age=" + age
				+ ", ctc=" + ctc + ", organisation=" + organisation + "]";
	}
	
	
	
	
	

}
