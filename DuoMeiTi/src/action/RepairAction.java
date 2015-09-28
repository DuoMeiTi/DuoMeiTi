package action;

import java.text.SimpleDateFormat;
import java.util.List;
import model.*;
import Repair.RepairDAO;
import RepairImpl.RepairDAOImpl;

public class RepairAction extends SuperAction {
	private static final long serialVersionUID = 1L;
	public String query() {
		 RepairDAO rdao = new RepairDAOImpl();
		 List<Repair> list = rdao.queryRepair();
		 //放进session中
		 System.out.println(list);
			if (list != null && list.size() > 0) {
				session.setAttribute("maintainRecords_list",  list);
			}
		return "query_repair";
	}

}
