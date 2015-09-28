package Repair;
import java.util.List;

import model.Repair;
import model.RepairRecord;
import model.Repertory;
import model.testRecord;

public interface RepairDAO {
	public List<Repair> queryRepair(String type, String val);

	public int add_rt(String id, String bh);
}
