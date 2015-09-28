package Repair;
import java.util.List;

import model.Repair;
import model.RepairRecord;
import model.Repertory;
import model.testRecord;

public interface RepairDAO {
	public List<Repair> queryRepair();

	boolean update_rtstate(Repertory rt);
}
