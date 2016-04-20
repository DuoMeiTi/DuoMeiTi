package model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class CheckInRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	

	@ManyToOne
	@JoinColumn
	public StudentProfile student;
	
	@Column
	public Timestamp recordtime;
	
	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StudentProfile getStudent() {
		return student;
	}

	public void setStudent(StudentProfile student) {
		this.student = student;
	}

	public Timestamp getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(Timestamp recordtime) {
		this.recordtime = recordtime;
	}

	@Override
	public String toString()
	{
		return "check IN::" + student.user.getFullName() + "| " + recordtime;
		
	}
}
