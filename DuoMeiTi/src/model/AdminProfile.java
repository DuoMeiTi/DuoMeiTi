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
    
    

    
    
    
    
    
    
    
    

    


}
