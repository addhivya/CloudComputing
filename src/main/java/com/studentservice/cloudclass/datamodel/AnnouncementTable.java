package com.studentservice.cloudclass.datamodel;

import java.util.*;

import com.studentservice.cloudclass.model.Announcement;
import com.studentservice.cloudclass.model.Course;
import com.studentservice.cloudclass.model.Program;

public class AnnouncementTable {

	private static Map<String, Announcement> announcementMap=new HashMap<>();
	
	

	public void createRecord(Announcement p) throws MyException {
		if(announcementMap.containsKey(p.getid()) || p.getid()==null) {			
			throw new MyException("Issue with ID. Check ID!!");
		}
		announcementMap.put(p.getid(), p);
	}

	public void deleteRecord(Announcement p) throws MyException {
		if(!announcementMap.containsKey(p.getid())) {
			throw new MyException("ID is invalid!! Give a different ID!");
		}
		announcementMap.remove(p.getid());
	}

	public void updateRecord(Announcement p) {
		announcementMap.put(p.getid(), p);
	}

	public Announcement getRecord(String key) throws MyException {
		if(!announcementMap.containsKey(key)) {
			throw new MyException("ID is invalid!! ID doesn't exist!");
		}
		return announcementMap.get(key);
	}
	
	public void addCourseToAnnouncement(Course course, String announcementId) throws MyException {
		getRecord(announcementId).setCourse(course);
	}
	
	public void remmoveCourseToAnnouncement(String announcementId) throws MyException {
		getRecord(announcementId).setCourse(null);
	}
	
	public void addProgramToAnnouncement(Program program, String announcementId) throws MyException {
		getRecord(announcementId).setProgram(program);
	}
	
	public void remmoveProgramToAnnouncement(String announcementId) throws MyException {
		getRecord(announcementId).setProgram(null);
	}
	
	public List<Announcement> retriveAll() {
		List<Announcement> result = new ArrayList<>();
		for (Announcement s : announcementMap.values()) {
			result.add(s);
		}
		return result;
	}
}
