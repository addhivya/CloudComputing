package com.studentservice.cloudclass.datamodel;

import java.util.*;

import com.studentservice.cloudclass.model.Course;
import com.studentservice.cloudclass.model.Professor;
import com.studentservice.cloudclass.model.Student;

public class CourseTable {

	private static Map<String, Course> courseMap=new HashMap<>();

	public CourseTable() {
		
	}

	public void createRecord(Course c) throws MyException{
		if(courseMap.containsKey(c.getid()) || c.getid()==null) {			
			throw new MyException("Issue with ID. Check ID!!");
		}
		courseMap.put(c.getid(),c);
	}

	public void deleteRecord(Course c) throws MyException {
		if(!courseMap.containsKey(c.getid())) {
			throw new MyException("ID is invalid!! Give a different ID!");
		}
		courseMap.remove(c.getid());
	}

	public void updateRecord(Course c) {
		courseMap.put(c.getid(), c);
	}

	public Course getRecord(String key) throws MyException {
		if(!courseMap.containsKey(key)) {
			throw new MyException("ID is invalid!! ID doesn't exist!");
		}
		return courseMap.get(key);
	}

	public List<Course> retriveAll() {
		List<Course> result = new ArrayList<>();
		for (Course s : courseMap.values()) {
			result.add(s);
		}
		return result;
	}
	
	public List<Course> getBySchedule(String schedule) throws MyException {
		final List<Course> returnList = new ArrayList<>();
		for (Course c : courseMap.values()) {
			if (c.getSchedule().equals(schedule)) {
				returnList.add(c);
			}
		}
		return returnList;
	}

	public void addStudentToCourse(Course c, Student s) {
		c.getEnrolledStudents().add(s.getStudentID());
	}

	public void removeStudentToCourse(Course c, Student s) {
		int index = -1;
		for (int i = 0; i < c.getEnrolledStudents().size(); i++) {
			if (c.getEnrolledStudents().get(i).equals(s.getStudentID())) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			c.getEnrolledStudents().remove(index);
		}
	}
	
	public void addProfessorToCourse(String courseName, Professor p) throws MyException {
		this.getRecord(courseName).setCourseProfessor(p);
	}
	
	public void removeProfessorToCourse(String courseName) throws MyException {
		this.getRecord(courseName).setCourseProfessor(null);
	}


	
	public void addTaToCourse(String courseName, Student ta) throws MyException {
		this.getRecord(courseName).setStudentTA(ta.getStudentID());
	}

	public void removeTaToCourse(String courseName) throws MyException {
		this.getRecord(courseName).setStudentTA(null);
	}

	/*public void removeLectureFromCourse(String lectureId, String courseId) {
		int index = -1;
		for (int i = 0; i < this.getRecord(courseId).getLectureList().size(); i++) {
			if (this.getRecord(courseId).getLectureList().get(i).getLectureId().equals(lectureId)) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			this.getRecord(courseId).getLectureList().remove(index);
		}
	}*/
}
