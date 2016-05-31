package model;


import org.hibernate.cfg.*;
import javax.persistence.*;



@Entity
public class SemesterFirstWeek {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
    

    @Column
    public java.sql.Date date;

    
    
    
    
    

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
}


