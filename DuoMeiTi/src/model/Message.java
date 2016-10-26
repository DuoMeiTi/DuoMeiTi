package model;
import javax.persistence.*;


//@Entity
//public class Message {
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	public int id;
//	
//	@Column
//	public String content;
//	
//	@ManyToOne(cascade=CascadeType.ALL)
//	@JoinColumn
//	public User from;
//	
//	@ManyToOne(cascade=CascadeType.ALL)
//	@JoinColumn
//	public User to;
//	
//	//1表示已读，0表示未读
//	@Column
//	public boolean readornot;
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public User getFrom() {
//		return from;
//	}
//
//	public void setFrom(User from) {
//		this.from = from;
//	}
//
//	public User getTo() {
//		return to;
//	}
//
//	public void setTo(User to) {
//		this.to = to;
//	}
//
//	public boolean isReadornot() {
//		return readornot;
//	}
//
//	public void setReadornot(boolean readornot) {
//		this.readornot = readornot;
//	}
//	
//}
