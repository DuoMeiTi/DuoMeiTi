//package homepage;
//
//import java.util.List;
//
//import org.hibernate.Session;
//
//import com.opensymphony.xwork2.ActionSupport;
//
//import model.TeachBuilding;
//import org.hibernate.Criteria;
//public class ClassRoomInfoAction extends ActionSupport {
//	private List<TeachBuilding> builds;
//
//	public List<TeachBuilding> getBuilds() {
//		return builds;
//	}
//
//	public void setBuilds(List<TeachBuilding> builds) {
//		this.builds = builds;
//	}
//	/**
//	 * 显示所有的教学楼
//	 * @return
//	 */
//	public String showBuildings(){
//		Session session = model.Util.sessionFactory.openSession();
//		Criteria criteria = session.createCriteria(TeachBuilding.class);
//		builds = criteria.list();
//		session.close();
//		return SUCCESS;
//	}
//}
