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
	
	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}

	public boolean isReadornot() {
		return readornot;
	}

	public void setReadornot(boolean readornot) {
		this.readornot = readornot;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public User getFrom() {
		return from;
	}

	//1表示已读，0表示未读
	@Column
	public boolean readornot;
}
