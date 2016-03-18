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
	@Fetch(FetchMode.SELECT)
	public Classroom classroom;
	
	@ManyToOne
	@JoinColumn
	@Fetch(FetchMode.SELECT)
	public User checkman;
	
	@Column(length=200)
	public String checkdetail;
	
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


	
}
