package com.studentservice.cloudclass.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.studentservice.cloudclass.datamodel.AnnouncementTable;
import com.studentservice.cloudclass.datamodel.CourseTable;
import com.studentservice.cloudclass.datamodel.MyException;
import com.studentservice.cloudclass.datamodel.ProfessorTable;
import com.studentservice.cloudclass.model.Professor;

public class ProfessorService {

	ProfessorTable table;
	AnnouncementTable announcementTable;
	CourseTable courseTable;

	public ProfessorService() {
		table = new ProfessorTable();
		announcementTable = new AnnouncementTable();
		courseTable = new CourseTable();
	}

	public void createProfessor(Professor p) throws MyException {
		p.setId(p.getFirstName()+p.getLastName());
		table.createRecord(p);
	}

	public Professor deleteProfessor(String professorID) throws MyException {
		Professor p = table.getRecord(professorID);
		table.deleteRecord(p);
		deleteProgramFromCourse(p);
		return p;
	}
	
	private void deleteProgramFromCourse(Professor professor) {
		courseTable.retriveAll().forEach(c -> {
			if(c.getCourseProfessor() == professor) {
				try {
					courseTable.removeProfessorToCourse(c.getid());
				} catch (MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public void updateProfessor(Professor p) {
		p.setId(p.getFirstName()+p.getLastName());
		table.updateRecord(p);		
	}

	public Professor retrieveOne(String professorID) throws MyException {
		return table.getRecord(professorID);
	}

	public List<Professor> retrieveAll() throws MyException  {
		return table.retriveAll();
	}

	public List<Professor> retriveByDept(String dept) throws MyException  {
		if (dept == null) {
			return retrieveAll();
		}
		return table.getByDept(dept);
	}

	public List<Professor> retriveByYear(String year, Integer size) throws MyException {
		List<Professor> result;
		if (year == null) {
			result = table.retriveAll();
		}
		result = table.getByYear(year);

		result.sort(new Comparator<Professor>() {
			public int compare(Professor p1, Professor p2) {
				return p1.getJoiningDate().compareTo(p2.getJoiningDate());
			}
		});

		if (size == null) {
			return result;
		} else {
			if(result.size()<size) {
				return result;	
			}
			else
			return result.subList(0, size);
		}
	}
}
