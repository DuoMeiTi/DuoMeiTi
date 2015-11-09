package model;

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
