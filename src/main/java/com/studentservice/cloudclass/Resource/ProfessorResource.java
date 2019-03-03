package com.studentservice.cloudclass.Resource;

import java.text.ParseException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.studentservice.cloudclass.datamodel.MyException;
import com.studentservice.cloudclass.model.Professor;
import com.studentservice.cloudclass.service.ProfessorService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("professors")
public class ProfessorResource {

	ProfessorService profservice = new ProfessorService();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createProfessor(Professor p) throws MyException, ParseException {		
		profservice.createProfessor(p);		
		return true;
	}
	
	@GET	
	@Path("/{professorID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor retrieveProfessor(@PathParam("professorID") String profId) throws MyException {
		return profservice.retrieveOne(profId);
	}

	@DELETE
	@Path("/{professorID}")
	public boolean deleteProfessor(@PathParam("professorID") String profId) throws MyException {
		profservice.deleteProfessor(profId);
		return true;
	}
	
	@PUT	
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean updateProfessor(Professor p) {
		 profservice.updateProfessor(p);
		 return true;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> retrieveAllProfessor() throws MyException  {
		return profservice.retrieveAll();
	}
	
	@GET
	@Path("/dept")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getByDept(@QueryParam("department") String department) throws MyException  {
		return profservice.retriveByDept(department);
	}

	@GET
	@Path("/year")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getByYear(@QueryParam("year") String year, @QueryParam("size") Integer size) throws MyException {
		return profservice.retriveByYear(year, size);
	}
	
}
