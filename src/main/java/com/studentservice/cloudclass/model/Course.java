package com.studentservice.cloudclass.model;

import java.util.*;


public class Course {
	String id;
	String name;
//	List<Lecture> lectureList;
	String schedule;
//	List<Announcement> announceList;
	List<String> enrolledStudents;
	Professor courseProfessor;
	String studentTA;
	
     public Course() {
	 }
     
     public Course(String id, String name, String schedule) {
    	 this.id=id;
 		this.name = name;
 		this.schedule = schedule;
 		//lectureList = new ArrayList<>();
 		//announceList = new ArrayList<>();
 		enrolledStudents = new ArrayList<>();
 	}
     
     public String getid() {
 		return id;
 	}

 	public void setid(String id) {
 		this.id = id;
 	}
     
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public List<Lecture> getLectureList() {
		return lectureList;
	}

	public void setLectureList(List<Lecture> lectureList) {
		this.lectureList = lectureList;
	}*/

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	/*public List<Announcement> getAnnounceList() {
		return announceList;
	}

	public void setAnnounceList(List<Announcement> announceList) {
		this.announceList = announceList;
	}*/

	public List<String> getEnrolledStudents() {
		if(this.enrolledStudents == null) {
			this.enrolledStudents = new ArrayList<>();
		}
		return enrolledStudents;
	}

	public void setEnrolledStudents(List<String> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	public Professor getCourseProfessor() {
		return courseProfessor;
	}

	public void setCourseProfessor(Professor courseProfessor) {
		this.courseProfessor = courseProfessor;
	}

	public String getStudentTA() {
		return studentTA;
	}

	public void setStudentTA(String studentTA) {
		this.studentTA = studentTA;
	}

	
}
