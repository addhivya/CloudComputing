package com.studentservice.cloudclass.Resource;

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
import com.studentservice.cloudclass.model.Course;
import com.studentservice.cloudclass.model.Professor;
import com.studentservice.cloudclass.service.CourseService;

@Path("course")
public class CourseResource {

	CourseService courseservice = new CourseService();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createCourse(Course c) throws MyException {
		courseservice.createCourse(c);
		return true;
	}

	@DELETE	
	@Path("/{id}")
	public boolean deleteCourse(@PathParam("id") String cid) throws MyException {
		courseservice.deleteCourse(cid);
		return true;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Course retrieveCourse(@PathParam("id") String cid) throws MyException {
		return courseservice.retrieveOne(cid);
	}

	@PUT	
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean updateCourse(Course c) {
		courseservice.updateCourse(c);
		return true;
	}

	@GET
	@Path("/schedule")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getByDept(@QueryParam("schedule") String schedule)  throws MyException{
		return courseservice.retriveBySchedule(schedule);
	} 
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> retrieveAllCourses() throws MyException  {
		return courseservice.retrieveAll();
	}
	
	
	@POST
	@Path("/addprof")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean addProfessorToCourse(@QueryParam("professorId") String professorId,
			@QueryParam("courseID") String courseID) throws MyException {
		return courseservice.addProfessorToCourse(courseID, professorId);
	}
	
	@DELETE
	@Path("/remprof")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean removeProfessorToCourse(@QueryParam("courseID") String courseID) throws MyException {
		return courseservice.removeProfessorToCourse(courseID);
	}
	

	@POST
	@Path("/addta")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean addTaToCourse(@QueryParam("studentId") String studentId,
			@QueryParam("courseID") String courseID) throws MyException {
		return courseservice.addTaToCourse(courseID, studentId);
	}

	@POST
	@Path("/remta")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean removeTaToCourse(@QueryParam("courseID") String courseID) throws MyException {
		return courseservice.removeTaToCourse(courseID);
	}

	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean addLectureCourse(@QueryParam("lectureId") String lectureId,
			@QueryParam("courseName") String courseName) {
		return courseservice.addLectureToCourse(lectureId, courseName);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean removeLectureCourse(@QueryParam("lectureId") String lectureId,
			@QueryParam("courseName") String courseName) {
		return courseservice.removeLectureToCourse(lectureId, courseName);
	}*/
}
