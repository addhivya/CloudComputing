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
import com.studentservice.cloudclass.model.Announcement;
import com.studentservice.cloudclass.model.Course;
import com.studentservice.cloudclass.model.Professor;
import com.studentservice.cloudclass.model.Program;
import com.studentservice.cloudclass.service.AnnouncementService;
import com.studentservice.cloudclass.service.ProgramService;


@Path("announcement")
public class AnnouncementResource {

	AnnouncementService announcementService = new AnnouncementService();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createAnnouncement(Announcement p) throws MyException{
		announcementService.createAnnouncement(p);
		return true;
	}

	@DELETE	
	@Path("/{id}")
	public boolean deleteAnnouncement(@PathParam("id") String id) throws MyException {
		announcementService.deleteAnnouncement(id);
		return true;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Announcement retrieveAnnouncement(@PathParam("id") String id) throws MyException {
		return announcementService.retrieveOne(id);
	}

	@PUT	
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean updateAnnouncement(Announcement p) {
		announcementService.updateAnnouncement(p);
		return true;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> retrieveAllAnnouncement() throws MyException{
		return announcementService.retrieveAll();
	}

	@POST
	@Path("/addcourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean addCourseToAnnouncement(@QueryParam("courseid") String courseid, 
			@QueryParam("id") String id) throws MyException {
		return announcementService.addCourseToAnnouncement(id, courseid);
	}
	
	@DELETE
	@Path("/remcourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean removeCourseToAnnouncement(@QueryParam("id") String id) throws MyException {
		return announcementService.removeCourseToAnnouncement(id);
	}
	
	@POST
	@Path("/addProgram")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean addProgramToAnnouncement(@QueryParam("programName") String programName, 
			@QueryParam("id") String id) throws MyException {
		return announcementService.addProgramToAnnouncement(id, programName);
	}
	
	@DELETE
	@Path("/remProgram")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean removeProgramToAnnouncement(@QueryParam("id") String id) throws MyException {
		return announcementService.removeProgramToAnnouncement(id);
	}
}
