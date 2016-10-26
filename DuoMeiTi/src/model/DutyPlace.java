package model;




import javax.persistence.*;
import model.DutyPiece;
import model.StudentProfile;

@Entity
public class DutyPlace {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	
	//地点名称
	@Column(unique = true)
	public String placeName;

	

	
	
	
	
	
	
	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
}
