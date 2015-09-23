package model;

import java.util.Set;

import org.hibernate.cfg.*;

import javax.persistence.*;






@Entity
public class StudentProfile {
	
    @OneToOne
    @JoinColumn
    public User user;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
    
    @Column(length = 20)
    public String studentId;
    
    @Column(length = 50)
    public String idCard;
    
    @Column(length = 50)
    public String bankCard;

    @Column(length = 50)
    public String college;    
    
    @Column
    public java.sql.Date entryTime;    

    @OneToMany(mappedBy="principal", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    public Set<Classroom> classrooms;
    
    @Column(length = 10)
    public String status;
    
    @Column(columnDefinition="INT default 0", nullable=false)
    public int isPassed;
    
    @Column(columnDefinition="INT default 0", nullable=false)
    public int isUpgradePrivilege;

    
    
    
    



}
