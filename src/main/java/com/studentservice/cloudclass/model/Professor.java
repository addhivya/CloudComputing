package com.studentservice.cloudclass.model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.studentservice.cloudclass.datamodel.MyException;

public class Professor {

	private String id;

	private String firstName;

	private String lastName;

	private String department;

    private LocalDateTime joiningDate;

	public Professor() {
	}

	//@JsonCreator
	/*public Professor(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
			@JsonProperty("department") String department, @JsonProperty("professorId") String professorId) {*/
	public Professor(String firstName, String lastName,String department, String joiningDate) throws MyException {
		this.id = firstName+lastName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;		
		try {
		Date date=new SimpleDateFormat("dd/MM/yyyy").parse(joiningDate);
		Instant i = date.toInstant();
		this.joiningDate= LocalDateTime.ofInstant(i, ZoneId.systemDefault());
		} catch(ParseException p) {
			throw new MyException("Date is not in valid format");
		}
		
		//this.joiningDate = joiningDate.toInstant().toEpochMilli(); @JsonProperty("joiningDate") Date joiningDate
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}	

	public LocalDateTime getJoiningDate() {
		return this.joiningDate;
	}
	
	public void setJoiningDate(String joiningDate) throws MyException {
		try {
			Date date=new SimpleDateFormat("dd/MM/yyyy").parse(joiningDate);
			Instant i = date.toInstant();
			this.joiningDate= LocalDateTime.ofInstant(i, ZoneId.systemDefault());
			} catch(ParseException p) {
				throw new MyException("Date is not in valid format");
			}
	}
}
