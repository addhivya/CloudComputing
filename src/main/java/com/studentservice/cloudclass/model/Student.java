package com.studentservice.cloudclass.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Student {
	String studentID;
	String firstName;
	String lastName;
	String image;
	List<Course> courseEnrolled;
	Program program;

	public Student() {
	}
	
	public Student(String firstName, String lastName, String image) {
		this.studentID = firstName + lastName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.image = image;
		this.courseEnrolled = new ArrayList<>();
	}

	public String getStudentID() {
		return getFirstName() + getLastName();
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Course> getCourseEnrolled() {
		if(this.courseEnrolled == null) {
			this.courseEnrolled = new ArrayList<>();
		}
		return courseEnrolled;
	}

	public void setCourseenrolled(List<Course> courseenrolled) {
		this.courseEnrolled = courseEnrolled;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}
}
