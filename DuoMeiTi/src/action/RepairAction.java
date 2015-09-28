package action;

import java.text.SimpleDateFormat;
import java.util.List;
import model.*;
import Repair.RepairDAO;
import RepairImpl.RepairDAOImpl;

public class RepairAction extends SuperAction {
	public String ret;
	public String id;
	public String bh;
	
	
	
	public RepairAction() {
	}

	public RepairAction(String ret, String id, String bh) {
		this.ret = ret;
		this.id = id;
		this.bh = bh;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@Override
	public String toString() {
		return "RepairAction [ret=" + ret + ", id=" + id + ", bh=" + bh + "]";
	}


	private static final long serialVersionUID = 1L;
	public String query() {
		 RepairDAO rdao = new RepairDAOImpl();
		 List<Repair> list = rdao.queryRepair(request.getParameter("Type"), request.getParameter("Value"));
		 //放进session中
		 System.out.println(list);
			if (list != null && list.size() > 0) {
				session.setAttribute("maintainRecords_list",  list);
			}
		return "query_repair";
	}
	
	public String add() {
		RepairDAO rdao = new RepairDAOImpl();
		ret = Integer.toString(rdao.add_rt(id, bh));
		System.out.println("顶层"+ret);
		return "adddevice";
	}
}
