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
    
    //isPassed=0 未处理 =1 不通过  =2 通过
    @Column(columnDefinition="INT default 0", nullable=false)
    public int isPassed;
    
    @Column(columnDefinition="INT default 0", nullable=false)
    public int isUpgradePrivilege;
    
    

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public java.sql.Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(java.sql.Date entryTime) {
		this.entryTime = entryTime;
	}

	public Set<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(Set<Classroom> classrooms) {
		this.classrooms = classrooms;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIsPassed() {
		return isPassed;
	}

	public void setIsPassed(int isPassed) {
		this.isPassed = isPassed;
	}

	public int getIsUpgradePrivilege() {
		return isUpgradePrivilege;
	}

	public void setIsUpgradePrivilege(int isUpgradePrivilege) {
		this.isUpgradePrivilege = isUpgradePrivilege;
	}

    
    
	public String toString()
	{
		return this.id + ", " + this.studentId;
	}
    



}
