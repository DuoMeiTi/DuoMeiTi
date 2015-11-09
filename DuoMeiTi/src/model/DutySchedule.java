package model;

import java.util.Date;

import javax.persistence.*;
import model.DutyTime;
import model.StudentProfile;

@Entity
public class DutySchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	
	//学生
	@ManyToOne
	@JoinColumn
	public StudentProfile student;
	
	//学生选的值班时间地点
	@ManyToOne
	@JoinColumn
	public DutyTime dutyTime;
	
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
    public Date entryTime;
	
	
	

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

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

	public DutyTime getDutyTime() {
		return dutyTime;
	}

	public void setDutyTime(DutyTime dutyTime) {
		this.dutyTime = dutyTime;
	}

}
