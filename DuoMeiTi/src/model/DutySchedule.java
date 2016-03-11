package model;

import javax.persistence.*;
import model.DutyPiece;
import model.StudentProfile;
import model.DutyPlace;

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
	public DutyPiece dutyPiece;
	
	
//	//学生选的值班时间地点
//	@ManyToOne
//	@JoinColumn
//	public model.DutyPlace dutyPlace;
	
	
	

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

	public DutyPiece getDutyPiece() {
		return dutyPiece;
	}

	public void setDutyPiece(DutyPiece dutyPiece) {
		this.dutyPiece = dutyPiece;
	}

	
	

}
