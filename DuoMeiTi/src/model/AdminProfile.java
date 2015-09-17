package model;


import java.util.Set;

import org.hibernate.cfg.*;

import javax.persistence.*;






@Entity
public class AdminProfile {
	
    @OneToOne
    @JoinColumn
    public User user;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int id;

 
    @Column(length=10)
    public int sex;    

    @Column(length = 100)
    public String profilePhotoPath;
    
    @Column(length = 50)
    public String unitInfo;
    
    @Column(length=20)
    public String phoneNumber;
    

    
    @Column(length=1000)
    public String remark;



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



	public int getSex() {
		return sex;
	}



	public void setSex(int sex) {
		this.sex = sex;
	}



	public String getProfilePhotoPath() {
		return profilePhotoPath;
	}



	public void setProfilePhotoPath(String profilePhotoPath) {
		this.profilePhotoPath = profilePhotoPath;
	}



	public String getUnitInfo() {
		return unitInfo;
	}



	public void setUnitInfo(String unitInfo) {
		this.unitInfo = unitInfo;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    

    
    
    
    
    
    
    
    

    


}
