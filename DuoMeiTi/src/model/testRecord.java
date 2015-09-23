package model;

import java.sql.Date;

public class testRecord {
	public String repairdetail;
	
	public testRecord() {
		
	}

	public testRecord(String repairdetail) {
		super();
		this.repairdetail = repairdetail;
	}

	@Override
	public String toString() {
		return "testRecord [repairdetail=" + repairdetail + "]";
	}
	
}
