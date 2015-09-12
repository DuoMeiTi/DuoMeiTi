package model;


import org.hibernate.cfg.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;



@Entity
public class User 
{
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) 
	private int id;
	
	@Column(length=50)
    private String username;
	
	@Column(length=50)
    private String password;
    
	
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
	
	public String toString()
	{
		return this.username + ", " + this.password;
	}

    

}
