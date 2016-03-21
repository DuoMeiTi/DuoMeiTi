package admin;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;
import javax.print.attribute.standard.PresentationDirection;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import model.Classroom;
import model.StudentProfile;
import model.TeachBuilding;

import util.Const;

import com.opensymphony.xwork2.ActionSupport;

public class TeachBuildingAction extends ActionSupport {
	
	private List<TeachBuilding> builds;
	
	private String device[];
	private String mainDevice[];
	private String costDevice[];
	public List<Classroom> classroom_list;
	public List<TeachBuilding> building_list;
	
	public String add_status;
	
	public String build_name;
	private String strValue;
	public int status;
	public int buildId;
	
	
	
	
	public List<TeachBuilding> getBuilding_list() {
		return building_list;
	}

	public void setBuilding_list(List<TeachBuilding> building_list) {
		this.building_list = building_list;
	}

	public int getBuildId() {
		return buildId;
	}

	public void setBuildId(int buildId) {
		this.buildId = buildId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Classroom> getClassroom_list() {
		return classroom_list;
	}

	public void setClassroom_list(List<Classroom> classroom_list) {
		this.classroom_list = classroom_list;
	}

	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public String execute() {
		Session session = model.Util.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(TeachBuilding.class)
				.setFetchMode("classrooms", FetchMode.SELECT)
				;
		builds = criteria.list();
		for(TeachBuilding b : builds) System.out.println(b.getBuild_name());
		
		
		//******************修改库存设备类型为外键
/*		repo_types=new ArrayList<String>();
		String hql="select r.rtType from Repertory r";
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<String> tmp_repo_types= query.list();
		for(String s:tmp_repo_types)
		{
			//System.out.println(s);
			if(!repo_types.contains(s))
				repo_types.add(s);
		}*/
		device = Const.device;
		mainDevice = Const.mainDevice;
		costDevice = Const.costDevice;
		//******************修改库存设备类型为外键

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
	
	
	
	public String BuildingDelete() throws Exception{
		
		Session session = model.Util.sessionFactory.openSession();
		System.out.println("BuildingDelete:");
//		Transaction trans = 
		session.beginTransaction();
		Criteria q = session.createCriteria(Classroom.class);
		q = q.createCriteria("teachbuilding");
		q.add(Restrictions.eq("build_id",buildId));
		
		classroom_list = q.list();
		System.out.println(classroom_list);
		
		if (classroom_list.isEmpty()) {
			status = 0;
		}
		else {
			status = 1;
			return ActionSupport.SUCCESS;
		}
		try{
			Criteria b = session.createCriteria(TeachBuilding.class);
			b.add(Restrictions.eq("build_id", buildId));
			List L = b.list();
			System.out.println(buildId);
			System.out.println(L.get(0));
			session.delete(L.get(0));
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
            session.getTransaction().rollback();
            System.out.println("删除失败");
		}finally{
			session.close();
		}
		System.out.println(status);
		
		return ActionSupport.SUCCESS;
		
		
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

/*	public List<String> getRepo_types() {
		return repo_types;
	}

	public void setRepo_types(List<String> repo_types) {
		this.repo_types = repo_types;
	}*/
	public String[] getDevice() {
		return device;
	}

	public void setDevice(String[] device) {
		this.device = device;
	}

	public String[] getMainDevice() {
		return mainDevice;
	}

	public void setMainDevice(String[] mainDevice) {
		this.mainDevice = mainDevice;
	}

	public String[] getCostDevice() {
		return costDevice;
	}

	public void setCostDevice(String[] costDevice) {
		this.costDevice = costDevice;
	}

	
	
}
