package action;

import java.util.List;
import model.Repair;
import model.RepairRecord;
import model.testRecord;
import Repair.RepairDAO;
import RepairImpl.RepairDAOImpl;

public class RepairAction extends SuperAction {
	private static final long serialVersionUID = 1L;
	public String query() {
		 RepairDAO rdao = new RepairDAOImpl();
		 List list = rdao.queryRepair();
		 //放进session中
			if (list != null && list.size() > 0) {
				session.setAttribute("repair_list",  list);
			}
		return "query_repair";
	}
}
