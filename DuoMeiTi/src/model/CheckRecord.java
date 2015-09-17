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
}
