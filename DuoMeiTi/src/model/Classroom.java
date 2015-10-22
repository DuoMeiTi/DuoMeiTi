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
	
	@Column(length = 10)
	public String classroom_num;
	
	@ManyToOne
	@JoinColumn
	@Fetch(FetchMode.SELECT)
	public StudentProfile principal;

	
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
