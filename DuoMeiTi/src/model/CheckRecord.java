package model;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class CheckRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
		
	@ManyToOne
	@JoinColumn
	@Deprecated
	public Classroom classroom;
//	@Column public String classroomName;	
//	@Column public String teachingBuildingName;
	
	@ManyToOne
	@JoinColumn
	@Deprecated
	public User checkman;
//	@Column public String checkmanFullName;
//	@Column public String checkmanPhoneNumber;
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * 如果checkdetail == NoProblem，表示此CheckRecord 无问题
	 * 否则，有问题
	 */
	public static final String NoProblem = "无问题";
	
	@Column(length=200)
	public String checkdetail;
	
	
	
	
	
	
	@Column
	public java.sql.Timestamp checkdate;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public User getCheckman() {
		return checkman;
	}

	public void setCheckman(User checkman) {
		this.checkman = checkman;
	}

	public String getCheckdetail() {
		return checkdetail;
	}

	public void setCheckdetail(String checkdetail) {
		this.checkdetail = checkdetail;
	}

	public java.sql.Timestamp getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(java.sql.Timestamp checkdate) {
		this.checkdate = checkdate;
	}

//	public String getClassroomName() {
//		return classroomName;
//	}
//
//	public void setClassroomName(String classroomName) {
//		this.classroomName = classroomName;
//	}
//
//	public String getTeachingBuildingName() {
//		return teachingBuildingName;
//	}
//
//	public void setTeachingBuildingName(String teachingBuildingName) {
//		this.teachingBuildingName = teachingBuildingName;
//	}
//
//	public String getCheckmanFullName() {
//		return checkmanFullName;
//	}
//
//	public void setCheckmanFullName(String checkmanFullName) {
//		this.checkmanFullName = checkmanFullName;
//	}
//
//	public String getCheckmanPhoneNumber() {
//		return checkmanPhoneNumber;
//	}
//
//	public void setCheckmanPhoneNumber(String checkmanPhoneNumber) {
//		this.checkmanPhoneNumber = checkmanPhoneNumber;
//	}
	


	
}
