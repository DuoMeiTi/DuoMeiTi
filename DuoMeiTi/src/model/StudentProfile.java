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
    
    @Column(length = 30, nullable = false, unique = true)
    public String studentId;
    
    @Column(length = 50)
    public String idCard;
    
    @Column(length = 50)
    public String bankCard;

    @Column(length = 50)
    public String college;    
    
    @Column
    public java.sql.Date entryTime;    


    
    @Column(length = 10)
    public String status;
    
    
    //用于用户注册请求部分： isPassed： 0 未处理； 1 不通过；2 通过， 
    public static final int   Unhandled = 0;
    public static final int   NotPassed = 1;
    public static final int   Passed = 2;
    @Column(columnDefinition="INT default 0", nullable=false)
    public int isPassed;
    //用于用户注册请求部分END
    
    
    //用于设置学生的权限：isUpgradePrivilege: 0 在职学生； 1 管理教师； 2 离职学生；    
    public static final int ServingStudent = 0;
    public static final int ManagedTeacher = 1;
    public static final int DepartureStudent = 2;
    @Column(columnDefinition="INT default 0", nullable=false)
    public int isUpgradePrivilege;
    //用于设置学生的权限END
    
    

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
//		return "StudentProfile(" + studentId +", " + +")";
		return this.id + ", " + this.studentId;
	}
    



}
