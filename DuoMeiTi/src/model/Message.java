package model;
import javax.persistence.*;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	@Column
	public String content;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn
	public User from;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn
	public User to;
	
	//1表示已读，0表示未读
	@Column
	public boolean readornot;
}
