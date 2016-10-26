package model;


import org.hibernate.cfg.*;

import javax.persistence.*;



@Entity
public class User 
{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	public int id;
	
	@Column(length=50, unique=true)
	public String username;
	
	@Column(length=50)
	public String password;
    
    @Column(length=50)
    public String fullName;

	@Column(length=10)
    public String sex;

    @Column(length = 100)
    public String profilePhotoPath;
    
    @Column(length=20)
    public String phoneNumber;
    
    @Column(length=1000)
    public String remark;
    
	public String toString()
	{
		return this.username + ", " + this.password + "," + this.id;
	}
	
	
	
	
	
	
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProfilePhotoPath() {
		return profilePhotoPath;
	}

	public void setProfilePhotoPath(String profilePhotoPath) {
		this.profilePhotoPath = profilePhotoPath;
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
