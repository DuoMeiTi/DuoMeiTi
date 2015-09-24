package admin;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.Classroom;
import model.TeachBuilding;

import com.opensymphony.xwork2.ActionSupport;

public class TeachBuildingAction extends ActionSupport {
	
	private List<TeachBuilding> builds;
	
	public String add_status;
	
	public String build_name;


	public String execute() {
		Session session = model.Util.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(TeachBuilding.class).setFetchMode("classrooms", FetchMode.SELECT);
		builds = criteria.list();
		for(TeachBuilding b : builds) System.out.println(b.getBuild_name());
		session.close();
		return SUCCESS;
	}
	
	public String addTeachBuilding() {
		Session session = model.Util.sessionFactory.openSession();
		Criteria build_criteria = session.createCriteria(TeachBuilding.class);
		build_criteria.add(Restrictions.eq("build_name", build_name));
		List<TeachBuilding> buildings = build_criteria.list();
		if(buildings.size() > 0) {
			add_status = "exist";
		}
		else {
			TeachBuilding build = new TeachBuilding();
			build.setBuild_name(build_name);
			
			session.beginTransaction();
			session.save(build);
			session.getTransaction().commit();
			
			add_status = "ok";
		}
		session.close();
		return SUCCESS;
	}
	
	
	
	
	

	
	public List<TeachBuilding> getBuilds() {
		return builds;
	}


	public void setBuilds(List<TeachBuilding> builds) {
		this.builds = builds;
	}

	public String getAdd_status() {
		return add_status;
	}

	public void setAdd_status(String add_status) {
		this.add_status = add_status;
	}

	public String getBuild_name() {
		return build_name;
	}

	public void setBuild_name(String build_name) {
		this.build_name = build_name;
	}
	
}
