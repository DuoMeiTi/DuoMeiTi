package model;

import javax.persistence.*;

public class CheckRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
		
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="classroom")
	public Classroom classroom;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="checkman")
	public StudentProfile checkman;
	
	@Column(length=200)
	public String checkdetail;
	
	public java.sql.Date checkdate;
}
