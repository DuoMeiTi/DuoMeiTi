package model;
import org.hibernate.*;
import org.hibernate.annotations.Table;
import org.hibernate.cfg.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;



@Entity
public class UserModel 
{
	private int id;
    private String username;
    private String password;
    
	@Id @GeneratedValue(strategy=GenerationType.AUTO) 
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    @Column(length=50)
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(length=50)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    

}
