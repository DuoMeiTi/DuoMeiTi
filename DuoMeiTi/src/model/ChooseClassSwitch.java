package model;

import javax.persistence.*;

@Entity
public class ChooseClassSwitch {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	@Column
	public boolean open;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}

