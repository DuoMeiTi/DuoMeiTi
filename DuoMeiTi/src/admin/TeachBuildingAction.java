package admin;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import model.TeachBuilding;

import com.opensymphony.xwork2.ActionSupport;

public class TeachBuildingAction extends ActionSupport {
	
	private List<TeachBuilding> builds;
	
	
	public List<TeachBuilding> getBuilds() {
		return builds;
	}


	public void setBuilds(List<TeachBuilding> builds) {
		this.builds = builds;
	}


	public String execute() {
		Session session = model.Util.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(TeachBuilding.class);
		builds = criteria.list();
//		for(TeachBuilding b : builds) System.out.println(b.getBuild_name());
		session.close();
		return SUCCESS;
	}
}
