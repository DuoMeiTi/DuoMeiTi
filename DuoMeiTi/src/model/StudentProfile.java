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
    
    @Column(length=10)
    public String fullName;
    
    public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

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
    



    @Column
    public int college;
    public static final String[]collegeSelect = { 
    		"数学科学学院",
    		"物理与光电工程学院",
    		"化工与环境生命学部",
    		"机械工程与材料能源学部",
    		"建设工程学部",
    		"建筑与艺术学院",
    		"运载工程与力学学部",    		
    		"电子信息与电气工程学部",
    		"外国语学院",
    		"人文与社会科学学部",
    		"管理与经济学部",
    		"创新实验学院",
    };


    @Column(length = 50)
    public String college;

    
    @Column(length=20)
    public String phoneNumber;
    
    @Column
    public java.sql.Date entryTime;
    

    @OneToMany(mappedBy="principal", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    public Set<Classroom> classrooms;
    
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




}
