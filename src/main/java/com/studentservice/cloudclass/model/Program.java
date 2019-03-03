package com.studentservice.cloudclass.model;

import java.util.ArrayList;
import java.util.List;

public class Program {
	String name;
	List<Course> courseList;
	public Program() {
	}
	public Program(String name) {
		this.name = name;
		this.courseList = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCourseList() {
		if(courseList==null) {
			courseList=new ArrayList<>();
		}
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	
}
