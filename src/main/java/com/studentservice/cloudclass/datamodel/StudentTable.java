package com.studentservice.cloudclass.datamodel;

import java.util.*;

import com.studentservice.cloudclass.model.Course;
import com.studentservice.cloudclass.model.Program;
import com.studentservice.cloudclass.model.Student;

public class StudentTable {

	private static Map<String, Student> studentMap=new HashMap<>();

	public StudentTable() {	
	}

	public void createRecord(Student s)throws MyException {
		if( s.getStudentID()==null || studentMap.containsKey(s.getStudentID())) {			
			throw new MyException("Issue with ID. Check ID!!");
		}
		studentMap.put(s.getStudentID(), s);
	}

	public void deleteRecord(Student s)  throws MyException{
		if(!studentMap.containsKey(s.getStudentID())) {
			throw new MyException("ID is invalid!! Give a different ID!");
		}
		studentMap.remove(s.getStudentID());
	}

	public void updateRecord(Student p) {
		studentMap.put(p.getStudentID(), p);
	}

	public Student getRecord(String key)throws MyException {
		if(!studentMap.containsKey(key)) {
			throw new MyException("ID is invalid!! ID doesn't exist!");
		}
		return studentMap.get(key);
	}

	public List<Student> retriveAll()throws MyException {
		List<Student> result = new ArrayList<>();
		for (Student s : studentMap.values()) {
			result.add(s);
		}		
		return result;
	}

	public void addStudentToCourse(String key, Course c) throws MyException {
		this.getRecord(key).getCourseEnrolled().add(c);
	}

	public void addStudentToProgram(String key, Program p) throws MyException {
		this.getRecord(key).setProgram(p);
	}

	public void removeStudentToCourse(String key, Course c) throws MyException {
		int index = -1;
		for (int i = 0; i < this.getRecord(key).getCourseEnrolled().size(); i++) {
			if (this.getRecord(key).getCourseEnrolled().get(i).getName().equals(c.getName())) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			this.getRecord(key).getCourseEnrolled().remove(index);
		}
	}

	public List<Student> getByProgram(String programName) {
		final List<Student> returnList = new ArrayList<>();
		for (Student p : studentMap.values()) {
			if (p.getProgram() != null && p.getProgram().equals(programName)) {
				returnList.add(p);
			}
		}
		return returnList;
	}

	public void removeStudentToProgram(String key, Program p) throws MyException{
		this.getRecord(key).setProgram(null);
	}
}
