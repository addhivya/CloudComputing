package com.studentservice.cloudclass.datamodel;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.studentservice.cloudclass.model.Professor;

public class ProfessorTable {

	private static Map<String, Professor> professorMap=new HashMap<>();
	
	public ProfessorTable() {	
	}
	
	public void createRecord(Professor p) throws MyException {
		if(professorMap.containsKey(p.getId())) {			
			throw new MyException("ID already exists!!");
		}
		professorMap.put(p.getId(), p);			
	}

	public void deleteRecord(Professor p) throws MyException {
		if(!professorMap.containsKey(p.getId())) {
			throw new MyException("ID is invalid!! Give a different ID!");
		}
		professorMap.remove(p.getId());		
	}

	public void updateRecord(Professor p) {		
		professorMap.put(p.getId(), p);
	}

	public Professor getRecord(String key) throws MyException {
		if(!professorMap.containsKey(key)) {
			throw new MyException("ID is invalid!! ID doesn't exist!");
		}
		return professorMap.get(key);
	}

	public List<Professor> retriveAll() throws MyException{
		final List<Professor> returnList = new ArrayList<>();
		if(professorMap.size()<1) {
			throw new MyException("No Professors exist!!");
		}
		for (Professor p : professorMap.values()) {
			returnList.add(p);
		}		
		return returnList;
	}

	public List<Professor> getByDept(String dept) throws MyException{
		final List<Professor> returnList = new ArrayList<>();
		for (Professor p : professorMap.values()) {
			if (p.getDepartment().equals(dept)) {
				returnList.add(p);
			}
		}
		if(returnList.isEmpty()) {
			throw new MyException("No Professors exist in that dept!!");
		}
		return returnList;
	}
	public List<Professor> getByYear(String year) {
		final List<Professor> returnList = new ArrayList<>();
		for (Professor p : professorMap.values()) {
			if (String.valueOf(p.getJoiningDate().getYear()).equals(year)) {
				returnList.add(p);
			}
		}
		return returnList;
	}
}
