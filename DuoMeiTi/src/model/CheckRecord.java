package model;

import javax.persistence.*;

@Entity
public class CheckRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
		
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn
	public Classroom classroom;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn
	public StudentProfile checkman;
	
	@Column(length=200)
	public String checkdetail;
	
	public java.sql.Date checkdate;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

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

	public StudentProfile getCheckman() {
		return checkman;
	}

	public void setCheckman(StudentProfile checkman) {
		this.checkman = checkman;
	}

	public String getCheckdetail() {
		return checkdetail;
	}

	public void setCheckdetail(String checkdetail) {
		this.checkdetail = checkdetail;
	}

	public java.sql.Date getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(java.sql.Date checkdate) {
		this.checkdate = checkdate;
	}
	
}
