package com.studentservice.cloudclass.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.studentservice.cloudclass.datamodel.CourseTable;
import com.studentservice.cloudclass.datamodel.MyException;
import com.studentservice.cloudclass.datamodel.ProgramTable;
import com.studentservice.cloudclass.datamodel.StudentTable;
import com.studentservice.cloudclass.model.Course;
import com.studentservice.cloudclass.model.Program;
import com.studentservice.cloudclass.model.Student;


public class StudentService {

	StudentTable studentTable;
	CourseTable courseTable;
	ProgramTable programTable;

	public StudentService() {
		studentTable = new StudentTable();
		courseTable = new CourseTable();
		programTable = new ProgramTable();
	}

	public void createStudent(Student student) throws MyException{
		studentTable.createRecord(student);
	}

	public Student deleteStudent(String studentID) throws MyException {
		Student s = studentTable.getRecord(studentID);
		studentTable.deleteRecord(s);
		removeStudentFromCourse(s);
		return s;
	}
	
	private void removeStudentFromCourse(Student s) {
		courseTable.retriveAll().forEach(c -> {
			if(c.getEnrolledStudents().contains(s.getStudentID())) {
				courseTable.removeStudentToCourse(c, s);
			}
			
			if(c.getStudentTA().equals(s.getStudentID())) {
				try {
					courseTable.removeTaToCourse(c.getid());
				} catch (MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public Student updateStudent(Student s) {
		studentTable.updateRecord(s);
		return s;
	}

	public Student retrieveOne(String studentID) throws MyException {
		return studentTable.getRecord(studentID);
	}

	public List<Student> retrieveAll() throws MyException {
		return studentTable.retriveAll();
	}

	public boolean addStudentToCourse(String studentId, String courseId) throws MyException {
		Course c = courseTable.getRecord(courseId);
		System.out.println("Checking " + c);
		studentTable.addStudentToCourse(studentId, c);
		courseTable.addStudentToCourse(c, studentTable.getRecord(studentId));
		return true;
	}

	public boolean addStudentToProgram(String studentId, String programName) throws MyException{
		Program p = programTable.getRecord(programName);
		studentTable.addStudentToProgram(studentId, p);
		return true;
	}

	public boolean removeStudentToCourse(String studentId, String courseId) throws MyException {
		Course c = courseTable.getRecord(courseId);
		studentTable.removeStudentToCourse(studentId, c);
		courseTable.removeStudentToCourse(c, studentTable.getRecord(studentId));
		return true;
	}

	public boolean removeStudentToProgram(String studentId, String programName) throws MyException{
		Program p = programTable.getRecord(programName);
		studentTable.removeStudentToProgram(studentId, p);
		return true;
	}

	public List<String> retriveByCourse(String courseName) throws MyException {
		return courseTable.getRecord(courseName).getEnrolledStudents();
	}

	public List<Student> retriveByProgram(String programName) {
		return studentTable.getByProgram(programName);
	}
}
