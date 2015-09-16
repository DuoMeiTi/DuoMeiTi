package admin;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.TeachBuilding;
import model.User;

import com.opensymphony.xwork2.ActionSupport;

public class TeachBuildingAction extends ActionSupport {
	
	private List<TeachBuilding> builds;
	private String build_name;
	private String status;
	private String build_id;
	public List<TeachBuilding> getBuilds() {
		return builds;
	}
	public void setBuild_id(String build_id){
		this.build_id=build_id;
	}
	public String getBuild_id(){
		return this.build_id;
	}

	public void setBuilds(List<TeachBuilding> builds) {
		this.builds = builds;
	}
	
	public void setBuild_name(String build_name){
		this.build_name=build_name;
	}
	public String getBuild_name(){
		return this.build_name;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return this.status;
	}


	public String execute() {
		Session session = model.Util.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(TeachBuilding.class);
		builds = criteria.list();
//		for(TeachBuilding b : builds) System.out.println(b.getBuild_name());
		session.close();
		return SUCCESS;
	}
	public String deleteBuilding() throws Exception
	{
		if(build_id == null)
		{
			this.status = "1";
			return ActionSupport.SUCCESS;
		}
//		System.out.println("SKLJFLJDF");
		if(build_id.equals(""))
		{
			this.status = "1";
			return ActionSupport.SUCCESS;
		}	
		int BUILD_ID=Integer.valueOf(build_id).intValue();
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(TeachBuilding.class).add(Restrictions.eq("build_id", BUILD_ID));
		List tl = q.list();
		if(tl.isEmpty())
		{
			this.status = "2";
		}
		else
		{
			session.beginTransaction();
			session.delete(tl.get(0));
			session.getTransaction().commit();
			this.status = "0";
		}
		session.close();
		return ActionSupport.SUCCESS;
	}
	public String createBuildingSave() throws Exception
	{
		if(build_name == null)
		{
			this.status = "1";
			return ActionSupport.SUCCESS;
		}
//		System.out.println("SKLJFLJDF");
		if(build_name.equals(""))
		{
			this.status = "1";
			return ActionSupport.SUCCESS;
		}		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(TeachBuilding.class).add(Restrictions.eq("build_name", build_name));
		List tl = q.list();
		if(!tl.isEmpty())
		{
			this.status = "2";
		}
		else
		{
			TeachBuilding tb = new TeachBuilding();
			tb.setBuild_name(build_name);
			
			
			session.beginTransaction();
			session.save(tb);
			session.getTransaction().commit();
			this.status = "0";
		}
		session.close();
		return ActionSupport.SUCCESS;
	}
}
