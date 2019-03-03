package com.studentservice.cloudclass.service;

import java.util.List;

import com.studentservice.cloudclass.datamodel.AnnouncementTable;
import com.studentservice.cloudclass.datamodel.CourseTable;
import com.studentservice.cloudclass.datamodel.MyException;
import com.studentservice.cloudclass.datamodel.ProgramTable;
import com.studentservice.cloudclass.model.Announcement;

public class AnnouncementService {
	AnnouncementTable announcementTable;
	ProgramTable programTable;
	CourseTable courseTable;

	public AnnouncementService() {
		announcementTable = new AnnouncementTable();
		programTable = new ProgramTable();
		courseTable = new CourseTable();
	}

	public void createAnnouncement(Announcement lecture) throws MyException {
		announcementTable.createRecord(lecture);
	}

	public Announcement deleteAnnouncement(String lectureId) throws MyException {
		Announcement c = announcementTable.getRecord(lectureId);
		announcementTable.deleteRecord(c);
		return c;
	}

	public Announcement updateAnnouncement(Announcement c) {
		announcementTable.updateRecord(c);
		return c;
	}

	public Announcement retrieveOne(String announcementId) throws MyException {
		return announcementTable.getRecord(announcementId);
	}
	
	public List<Announcement> retrieveAll() throws MyException {
		return announcementTable.retriveAll();
	}
	
	public boolean addCourseToAnnouncement(String announcementId, String courseid) throws MyException {
		announcementTable.addCourseToAnnouncement(courseTable.getRecord(courseid), announcementId);
		return true;
	}
	
public boolean addProgramToAnnouncement(String announcementId, String programid) throws MyException {
	announcementTable.addProgramToAnnouncement(programTable.getRecord(programid), announcementId);
		return true;
	}

public boolean removeCourseToAnnouncement(String announcementId) throws MyException {
	announcementTable.remmoveCourseToAnnouncement(announcementId);
	return true;
}

public boolean removeProgramToAnnouncement(String announcementId) throws MyException {
	announcementTable.remmoveProgramToAnnouncement(announcementId);
	return true;
}
}
