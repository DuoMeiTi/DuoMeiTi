package homepage;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import model.Classroom;
import model.TeachBuilding;

public class BuildingInformationAction extends ActionSupport {
	private List<TeachBuilding> builds;

	public String build_name;
	public List<Classroom> classroom_list;
	public int buildId;
	public int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getBuildId() {
		return buildId;
	}

	public void setBuildId(int buildId) {
		this.buildId = buildId;
	}

	public List<TeachBuilding> getBuilds() {
		return builds;
	}

	public void setBuilds(List<TeachBuilding> builds) {
		this.builds = builds;
	}

	public String getBuild_name() {
		return build_name;
	}

	public void setBuild_name(String build_name) {
		this.build_name = build_name;
	}

	public List<Classroom> getClassroom_list() {
		return classroom_list;
	}

	public void setClassroom_list(List<Classroom> classroom_list) {
		this.classroom_list = classroom_list;
	}

	public String execute() {
		System.out.println("建立查询");
		Session session = model.Util.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(TeachBuilding.class).setFetchMode("classroom", FetchMode.SELECT);
		builds = criteria.list();
		for (TeachBuilding b : builds) {
			System.out.println(b.getBuild_name());
		}
		session.close();
		return SUCCESS;
	}

	/*
	 * public String BuildingDelete() throws Exception{ Session session =
	 * model.Util.sessionFactory.openSession(); Transaction trans =
	 * session.beginTransaction(); Criteria q =
	 * session.createCriteria(Classroom.class); q =
	 * q.createCriteria("teachbuilding"); q.add(Restrictions.eq("build_id",
	 * buildId));
	 * 
	 * classroom_list = q.list(); if (classroom_list.isEmpty()) { status = 0; }
	 * else { status = 1; } System.out.println(status); trans.commit(); return
	 * ActionSupport.SUCCESS;
	 * 
	 * 
	 * }
	 */

}
