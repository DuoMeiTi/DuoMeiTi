package repairTest;

import java.util.List;

import org.junit.Test;
import model.*;
import Repair.RepairDAO;
import RepairImpl.RepairDAOImpl;
import action.SuperAction;

public class RepairDAOTest extends SuperAction{

	@Test
	public void queryTest() {
		RepairDAO rdao = new RepairDAOImpl();
		List<Repair> list = rdao.queryRepair(request.getParameter("type"), request.getParameter("val"));
		System.out.println("Lise size： "+list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		/*Date date = new Date(new java.util.Date().getTime());
		testRecord t = new testRecord("d", date);
		System.out.println(t);*/
	}
}
