package model;

public class Repair {
	public String Id;
	
	public String Name;
	
	public String Type;
	
	public String Detail;
	
	public String Room;
	
	public String Building;
	
	public java.sql.Date Date;
	
	public Repair() {
		
	}

	public Repair(String id, String name, String type, String detail, String room, String building,
			java.sql.Date date) {
		//super();
		Id = id;
		Name = name;
		Type = type;
		Detail = detail;
		Room = room;
		Building = building;
		Date = date;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}

	public String getRoom() {
		return Room;
	}

	public void setRoom(String room) {
		Room = room;
	}

	public String getBuilding() {
		return Building;
	}

	public void setBuilding(String building) {
		Building = building;
	}

	public java.sql.Date getDate() {
		return Date;
	}

	public void setDate(java.sql.Date date) {
		Date = date;
	}
	
	
}
