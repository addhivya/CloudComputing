package com.studentservice.cloudclass.Resource;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.studentservice.cloudclass.datamodel.MyException;
import com.studentservice.cloudclass.model.Student;
import com.studentservice.cloudclass.service.StudentService;

@Path("students")
public class StudentResource {

	StudentService studservice = new StudentService();

	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createStudent(Student s) throws MyException{
		studservice.createStudent(s);
		return true;
	}

	@DELETE	
	@Path("/{studentID}")
	public boolean deleteStudent(@PathParam("studentID") String studId) throws MyException {
		studservice.deleteStudent(studId);
		return true;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{studentID}")
	public Student retrieveStudent(@PathParam("studentID") String studId) throws MyException {
		return studservice.retrieveOne(studId);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> retrieveAllStudent() throws MyException {
		return studservice.retrieveAll();
	}

	@PUT	
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean updateStudent(Student s) {
		studservice.updateStudent(s);
		return true;
	}
	@POST
	@Path("/tocourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean addStudentToCourse(@QueryParam("studentId") String studentId,
			@QueryParam("courseId") String courseId) throws MyException {
		return studservice.addStudentToCourse(studentId, courseId);
	}
	
	@POST
	@Path("/fromcourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean removeStudentToCourse(@QueryParam("studentId") String studentId,
			@QueryParam("courseId") String courseId) throws MyException {
		return studservice.removeStudentToCourse(studentId, courseId);
	}
	
	@POST
	@Path("/toprogram")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean addStudentToProgram(@QueryParam("studentId") String studentId,
			@QueryParam("programName") String programName) throws MyException {
		return studservice.addStudentToProgram(studentId, programName);
	}	

	@POST
	@Path("/fromprogram")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean removeStudentToProgram(@QueryParam("studentId") String studentId,
			@QueryParam("programName") String programName) throws MyException {
		return studservice.removeStudentToProgram(studentId, programName);
	}

	/*@POST

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getByCourse(@QueryParam("course") String course) {
		return studservice.retriveByCourse(course);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getByProgram(@QueryParam("program") String program) {
		return studservice.retriveByCourse(program);
	}*/
}
