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
import com.studentservice.cloudclass.model.Program;
import com.studentservice.cloudclass.service.ProgramService;


@Path("program")
public class ProgramResource {

	ProgramService programService = new ProgramService();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createProgram(Program p) throws MyException{
		programService.createProgram(p);
		return true;
	}

	@DELETE	
	@Path("/{programName}")
	public boolean deleteProgram(@PathParam("programName") String programName) throws MyException {
		programService.deleteProgram(programName);
		return true;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{programName}")
	public Program retrieveProgram(@PathParam("programName") String programName) throws MyException {
		return programService.retrieveOne(programName);
	}

	@PUT	
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean updateProgram(Program p) {
		programService.updateProgram(p);
		return true;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> retrieveAllProgram() throws MyException{
		return programService.retrieveAll();
	}

	@POST
	@Path("/addcourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean addCourseToProgram(@QueryParam("courseid") String courseid, 
			@QueryParam("programName") String program) throws MyException {
		return programService.addCourseToProgram(courseid, program);
	}
	
	@DELETE
	@Path("/remcourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean removeCourseToProgram(@QueryParam("courseid") String courseid, 
			@QueryParam("programName") String program) throws MyException {
		return programService.removeCourseToProgram(courseid, program);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/course/{programName}")
	public List<Course> getAllCourseByProgram(@PathParam("programName") String programName) throws MyException {
		return programService.getAllCourseByProgram(programName);
	}
}
