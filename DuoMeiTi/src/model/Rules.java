package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rules {
@Id
@GeneratedValue
public int id;
@Column(length=5000)
public String text;
public Date time;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}

}
