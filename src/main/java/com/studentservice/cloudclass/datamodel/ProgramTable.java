package com.studentservice.cloudclass.datamodel;

import java.util.*;

import com.studentservice.cloudclass.model.Course;
import com.studentservice.cloudclass.model.Professor;
import com.studentservice.cloudclass.model.Program;

public class ProgramTable {

	private static Map<String, Program> programMap= new HashMap<>();

	public ProgramTable() {
		
	}

	public void createRecord(Program p) throws MyException {
		if(programMap.containsKey(p.getName())) {			
			throw new MyException("Program already exists!!");
		}
		programMap.put(p.getName(), p);
	}

	public void deleteRecord(Program p) throws MyException {
		if(!programMap.containsKey(p.getName())) {
			throw new MyException("Name is invalid!! Give a different name!");
		}
		programMap.remove(p.getName());
	}

	public void updateRecord(Program p) {
		programMap.put(p.getName(), p);
	}

	public Program getRecord(String key) throws MyException {
		if(!programMap.containsKey(key)) {
			throw new MyException("Name is invalid!! Name doesn't exist!");
		}
		return programMap.get(key);
	}
	
	public void removeCourseFromProgram(String key, Course c) throws MyException{
		int index = -1; 
		for (int i = 0; i < this.getRecord(key).getCourseList().size(); i++) {
			if (this.getRecord(key).getCourseList().get(i).getName().equals(c.getName())) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			this.getRecord(key).getCourseList().remove(index);
		}
	}
	
	public List<Program> retriveAll()  throws MyException{
		final List<Program> returnList = new ArrayList<>();
		for (Program p : programMap.values()) {
			returnList.add(p);
		}
		if(returnList.isEmpty()) {
			throw new MyException("No Program exist!!");
		}
		return returnList;
	}

}
