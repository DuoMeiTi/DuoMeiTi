package model;




import javax.persistence.*;

@Entity
public class DutyChooseSwitch {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	@Column
	public boolean isOpen;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	

}

