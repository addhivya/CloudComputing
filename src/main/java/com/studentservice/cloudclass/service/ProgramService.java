package com.studentservice.cloudclass.service;

import java.util.List;

import com.studentservice.cloudclass.datamodel.AnnouncementTable;
import com.studentservice.cloudclass.datamodel.CourseTable;
import com.studentservice.cloudclass.datamodel.MyException;
import com.studentservice.cloudclass.datamodel.ProgramTable;
import com.studentservice.cloudclass.datamodel.StudentTable;
import com.studentservice.cloudclass.model.Course;
import com.studentservice.cloudclass.model.Professor;
import com.studentservice.cloudclass.model.Program;


public class ProgramService {
	
	ProgramTable programTable;
	CourseTable courseTable;
	AnnouncementTable announcementTable;
	StudentTable studentTable;

	public ProgramService() {
		programTable = new ProgramTable();
		courseTable = new CourseTable();
		announcementTable = new AnnouncementTable();
		studentTable = new StudentTable();
	}

	public void createProgram(Program p)throws MyException {
		programTable.createRecord(p);
	}

	public Program deleteProgram(String programName) throws MyException{
		Program c = programTable.getRecord(programName);
		programTable.deleteRecord(c);
		removeProgramFromAllAnnouncement(programName);
		removeProgramFromStudent(programName);
		return c;
	}
	
	private void removeProgramFromAllAnnouncement(String programName) {
		announcementTable.retriveAll().forEach(announcement -> {
			  if(announcement.getProgram().getName().equals(programName)) {
				  try {
					announcementTable.remmoveProgramToAnnouncement(announcement.getid());
				} catch (MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		});
	}
	
	private void removeProgramFromStudent(String programName) throws MyException {
		studentTable.retriveAll().forEach(student -> {
			if(student.getProgram().getName().equals(programName)) {
				try {
					studentTable.removeStudentToProgram(student.getStudentID(), null);
				} catch (MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	

	public Program updateProgram(Program c) {
		programTable.updateRecord(c);
		return c;
	}

	public Program retrieveOne(String pName)  throws MyException{
		return programTable.getRecord(pName);
	}
	
	public List<Program> retrieveAll() throws MyException {
		return programTable.retriveAll();
	}
	
	public Boolean addCourseToProgram(String courseId, String programName) throws MyException {
		programTable.getRecord(programName).getCourseList().add(courseTable.getRecord(courseId));
		return true;
	}
	
	public Boolean removeCourseToProgram(String courseId, String programName)  throws MyException{
		programTable.removeCourseFromProgram(programName, courseTable.getRecord(courseId));
		return true;
	}
	
	public List<Course> getAllCourseByProgram(String programName) throws MyException {
		return programTable.getRecord(programName).getCourseList();
	}
}
