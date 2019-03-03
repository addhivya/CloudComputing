package com.studentservice.cloudclass.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.studentservice.cloudclass.datamodel.MyException;


public class Announcement {
	String id;
	LocalDateTime date;
	String description;
	Course course;
	Program program;
	
	public Announcement() {
	 
	}
	 
	public Announcement(String id, String d, String description) throws MyException {
		this.id=id;
		this.description = description;
		try {
			Date date=new SimpleDateFormat("dd/MM/yyyy").parse(d);
			Instant i = date.toInstant();
			this.date= LocalDateTime.ofInstant(i, ZoneId.systemDefault());
			} catch(ParseException p) {
				throw new MyException("Date is not in valid format");
			}
	}
	

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}
	
	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(String d) throws MyException {
		try {
			Date date=new SimpleDateFormat("dd/MM/yyyy").parse(d);
			Instant i = date.toInstant();
			this.date= LocalDateTime.ofInstant(i, ZoneId.systemDefault());
			} catch(ParseException p) {
				throw new MyException("Date is not in valid format");
			}
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
