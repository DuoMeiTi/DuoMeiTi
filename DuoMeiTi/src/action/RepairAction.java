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
	
	public String classroom_id;
	
	
	
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
			else
			{
				session.setAttribute("maintainRecords_list", null);
			}
		return "query_repair";
	}
	
	public String add() {
		RepairDAO rdao = new RepairDAOImpl();
		System.out.println(id + "============" + bh);
		ret = Integer.toString(rdao.add_rt(id, bh));
		System.out.println("顶层"+ret);
		return "adddevice";
	}
	
	public String alter() {
		List<Repertory> list;
		RepairDAO rdao = new RepairDAOImpl();
		list = rdao.alterDevice(request.getParameter("classroomid"));
		for (int i = 0; i < list.size(); i++) {
			System.out.println("输出++++++++++++++++++++++++++++++++");
			System.out.println(list.get(i));
		}
		if (list != null && list.size() > 0) {
			session.setAttribute("alter_list",  list);
		}
		else
		{
			session.setAttribute("alter_list", null);
		}
		return "alternative";
	}
	
	public String move2alter() {
		RepairDAO rdao = new RepairDAOImpl();
		System.out.println(id + "============");
		ret = Integer.toString(rdao.m2alter(request.getParameter("m2alter")));
		System.out.println("顶层"+ret);
		return "move2alter";
	}
	public String addalter() {
		RepairDAO rdao = new RepairDAOImpl();
		System.out.println(id + "============");
		ret = Integer.toString(rdao.addalterIm(request.getParameter("rtid")));
		System.out.println("顶层"+ret);
		return "addalter";
	}
	public String delalter() {
		RepairDAO rdao = new RepairDAOImpl();
		System.out.println(id + "============");
		ret = Integer.toString(rdao.delalterIm(request.getParameter("rtid")));
		System.out.println("顶层"+ret);
		return "delalter";
	}
}
