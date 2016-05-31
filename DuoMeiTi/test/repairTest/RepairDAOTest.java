//package repairTest;
//
//import java.util.List;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.junit.Test;
//import model.*;
//import Repair.RepairDAO;
////import RepairImpl.RepairDAOImpl;
//import action.SuperAction;
//import db.MyHibernateSessionFactory;

//public class RepairDAOTest extends SuperAction{
//
//	@Test
//	public void queryTest() {
//		RepairDAO rdao = new RepairDAOImpl();
//		List<Repair> list = rdao.queryRepair(request.getParameter("type"), request.getParameter("val"));
//		System.out.println("Lise size： "+list.size());
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//		/*Date date = new Date(new java.util.Date().getTime());
//		testRecord t = new testRecord("d", date);
//		System.out.println(t);*/
//	}
//	
//	@Test
//	public void classroomTest() {
//		// TODO Auto-generated method stub
//				Transaction tx = null;
//				String hql ="";
//				try {
//					Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
//					tx = session.beginTransaction();
//					hql = "SELECT rt FROM Repertory rt WHERE rt.classroom = 2";
//					System.out.println(hql);
//					Query query = session.createQuery(hql);
//					List<Repertory> rtClass = query.list();
//					for (int i = 0; i < rtClass.size(); i++) {
//						System.out.println("输出++++++++++++++++++++++++++++++++");
//						System.out.println(rtClass.get(i));
//					}
//					tx.commit();
//				}
//				catch (Exception ex) {
//					ex.printStackTrace();
//					tx.commit();
//				}
//				finally {
//					if (tx != null) {
//						if (tx != null) {
//							tx = null;
//						}
//					}
//				}
//	}
//}
