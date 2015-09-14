package admin;

import java.util.List;

import model.Classroom;
import model.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

public class ClassroomSearchAction {
	//private String classroom_num;  //教室号
	//private String principle;  //负责人
	private String search;
	private List<Classroom> classroom_list;//教室信息
	private String status;
	/*
	 * status 0: OK
	 * 		  1: 查询关键字为空
	 */
	
	/*public String getClassroom_num() {
		return classroom_num;
	}
	public void setClassroom_num(String classroom_num) {
		this.classroom_num = classroom_num;
	}
	public String getPrinciple() {
		return principle;
	}
	public void setPrinciple(String principle) {
		this.principle = principle;
	}*/
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Classroom> getClassroom_list() {
		return classroom_list;
	}
	public void setClassroom_list(List<Classroom> classroom_list) {
		this.classroom_list = classroom_list;
	}
	
	public String classroom_search() throws Exception
	{
		Session session = model.Util.sessionFactory.openSession();
		
		Criteria q = session.createCriteria(User.class).add(Restrictions.eq("classroom_num", search));
		classroom_list= q.list();
		if(search==null||search=="")
		{
			this.status = "error: search key is null";
			return ActionSupport.SUCCESS;
		}
		if(search.equals(""))
		{
			this.status = "1";
			return ActionSupport.SUCCESS;
		}	
		else
		{
			Classroom cr = new Classroom();
			//cr.setClassroom_num(classroom_num);
			//cr.setPassword(password);
			session.beginTransaction();
			session.save(cr);
			session.getTransaction().commit();
			this.status = "0";
		}
        //		System.out.println("SKLJFLJDF");
		
		session.close();
		return ActionSupport.SUCCESS;
	}
  
}
