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
 
    @Column(length = 10)
    public String sex;    
    
    @Column(length = 20)
    public String studentId;
    
    @Column(length = 100)
    public String profilePhotoPath;
    
    
    @Column(length = 50)
    public String idCard;
    
    @Column(length = 50)
    public String bankCard;
    


    @Column(length = 50)
    public String college;

    
    @Column(length=20)
    public String phoneNumber;
    
    @Column
    public java.sql.Date entryTime;
    
<<<<<<< HEAD
//<<<<<<< HEAD
////    //负责教室
////    @OneToOne
////    @JoinColumn
////    public Classroom classroom;
//=======
//    //负责教室
    @OneToMany(mappedBy="principal", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    public Set<Classroom> classrooms;
//>>>>>>> origin/master
    
=======
    @OneToMany(mappedBy="principal", cascade=CascadeType.ALL)
    public Set<Classroom> classrooms;  
>>>>>>> origin/master

    
    @Column(length = 10)
    public String status;    

    
    
    @Column(length=1000)
    public String remark;



    
    
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getProfilePhotoPath() {
		return profilePhotoPath;
	}

	public void setProfilePhotoPath(String profilePhotoPath) {
		this.profilePhotoPath = profilePhotoPath;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
    
    
    
    
    
    
    
    
    
    
    
    
    
 



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getSex() {
		return sex;
	}



	public void setSex(int sex) {
		this.sex = sex;
	}



	public String getStudentId() {
		return studentId;
	}



	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}



	public String getProfilePhotoPath() {
		return profilePhotoPath;
	}



	public void setProfilePhotoPath(String profilePhotoPath) {
		this.profilePhotoPath = profilePhotoPath;
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



	public int getCollege() {
		return college;
	}



	public void setCollege(int college) {
		this.college = college;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public java.sql.Date getEntryTime() {
		return entryTime;
	}



	public void setEntryTime(java.sql.Date entryTime) {
		this.entryTime = entryTime;
	}

	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}


	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}

}
