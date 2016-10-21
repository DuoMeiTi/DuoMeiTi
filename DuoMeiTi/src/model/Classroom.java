package model;


import java.util.Set;

import javax.persistence.*;
//import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Classroom {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	@ManyToOne
	@JoinColumn
	@Fetch(FetchMode.SELECT)
	public TeachBuilding teachbuilding;
	
	@Column
	public String classroom_num;
	
	@ManyToOne
	@JoinColumn
	@Fetch(FetchMode.SELECT)
	public StudentProfile principal;
	
	@Column
	public String class_schedule_path;
	
	
	public String getClass_schedule_path() {
		return class_schedule_path;
	}

	public void setClass_schedule_path(String class_schedule_path) {
		this.class_schedule_path = class_schedule_path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TeachBuilding getTeachbuilding() {
		return teachbuilding;
	}

	public void setTeachbuilding(TeachBuilding teachbuilding) {
		this.teachbuilding = teachbuilding;
	}

	public String getClassroom_num() {
		return classroom_num;
	}

	public void setClassroom_num(String classroom_num) {
		this.classroom_num = classroom_num;
	}

	public StudentProfile getPrincipal() {
		return principal;
	}

	public void setPrincipal(StudentProfile principal) {
		this.principal = principal;
	}

	
	
}
