package repairTest;

import java.util.List;

import org.junit.Test;
import model.*;
import Repair.RepairDAO;
import RepairImpl.RepairDAOImpl;

public class RepairDAOTest {

	@Test
	public void queryTest() {
		RepairDAO rdao = new RepairDAOImpl();
		List<Repair> list = rdao.queryRepair();
		System.out.println("Lise size： "+list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		/*Date date = new Date(new java.util.Date().getTime());
		testRecord t = new testRecord("d", date);
		System.out.println(t);*/
	}
}
