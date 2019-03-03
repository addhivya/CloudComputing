package com.studentservice.cloudclass.service;

import java.util.List;

import com.studentservice.cloudclass.datamodel.AnnouncementTable;
import com.studentservice.cloudclass.datamodel.CourseTable;
import com.studentservice.cloudclass.datamodel.MyException;
import com.studentservice.cloudclass.datamodel.ProfessorTable;
import com.studentservice.cloudclass.datamodel.ProgramTable;
import com.studentservice.cloudclass.datamodel.StudentTable;
import com.studentservice.cloudclass.model.Course;
import com.studentservice.cloudclass.model.Professor;
import com.studentservice.cloudclass.model.Student;

public class CourseService {
	StudentTable studentTable;
	CourseTable courseTable;
	ProgramTable programTable;
	ProfessorTable professorTable;
	AnnouncementTable announcementTable;
	//LectureTable lectureTable;

	public CourseService() {
		studentTable = new StudentTable();
		courseTable = new CourseTable();
		programTable = new ProgramTable();
		professorTable = new ProfessorTable();
		announcementTable = new AnnouncementTable();
	//	lectureTable = new LectureTable();
	}

	public void createCourse(Course course) throws MyException {
		courseTable.createRecord(course);
	}

	public Course deleteCourse(String courseID) throws MyException {
		Course c = courseTable.getRecord(courseID);
		courseTable.deleteRecord(c);
		removeCourseFromAllAnnouncement(c);
		removeCourseFromAllStudent(c);
		removeCourseFromAllProgram(c);
		return c;
	}
	
	private void removeCourseFromAllAnnouncement(Course c) {
		announcementTable.retriveAll().forEach(announcement -> {
			  if(announcement.getCourse() == c) {
				  try {
					announcementTable.remmoveCourseToAnnouncement(announcement.getid());
				} catch (MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		});
	}
	
	private void removeCourseFromAllStudent(Course c) throws MyException {
		 studentTable.retriveAll().forEach(s -> {
			 if(s.getCourseEnrolled().contains(c)) {
				 try {
					studentTable.removeStudentToCourse(s.getStudentID(), c);
				} catch (MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		 });
	}
	
	private void removeCourseFromAllProgram(Course c) throws MyException {
		programTable.retriveAll().forEach(p -> {
			if(p.getCourseList().contains(c)) {
				try {
					programTable.removeCourseFromProgram(p.getName(), c);
				} catch (MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public Course updateCourse(Course c) {
		courseTable.updateRecord(c);
		return c;
	}

	public Course retrieveOne(String courseID) throws MyException {
		return courseTable.getRecord(courseID);
	}

	public List<String> retriveByCourse(String courseID) throws MyException {
		return courseTable.getRecord(courseID).getEnrolledStudents();
	}
	
	public List<Course> retriveBySchedule(String schedule)  throws MyException{
		if (schedule == null) {
			return null;
		}
		return courseTable.getBySchedule(schedule);
	}
	
	public List<Course> retrieveAll() throws MyException  {
		return courseTable.retriveAll();
	}
	
	public boolean addProfessorToCourse(String courseID, String professorId) throws MyException {
		Professor p = professorTable.getRecord(professorId);
		courseTable.addProfessorToCourse(courseID, p);
		return true;
	}

	public boolean removeProfessorToCourse(String courseID) throws MyException {
		courseTable.removeProfessorToCourse(courseID);
		return true;
	}	

	public boolean addTaToCourse(String courseID, String studentId) throws MyException {
		Student p = studentTable.getRecord(studentId);
		courseTable.addTaToCourse(courseID, p);
		return true;
	}

	public boolean removeTaToCourse(String courseID) throws MyException {
		courseTable.removeTaToCourse(courseID);
		return true;
	}

	/*public boolean addLectureToCourse(String lectureId, String courseId) {
		courseTable.getRecord(courseId).getLectureList().add(lectureTable.getRecord(lectureId));
		return true;
	}

	public boolean removeLectureToCourse(String lectureId, String courseId) {
		courseTable.removeLectureFromCourse(lectureId, courseId);
		return true;
	}*/
}
