package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Classroom;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

public class ClassroomSearchAction extends ActionSupport{
	//private String classroom_num;  //教室号
	//private String principle;  //负责人
	private String search;
	private List<Classroom> classroom_list;//教室信息
	private String searchselect;
	private String status;
	//private HttpServletRequest request;
	private String classroominfo_html;
	/*
	 * status 0: OK
	 * 		  1: 查询关键字为空
	 */
	
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
	
	public String getSearchselect() {
		return searchselect;
	}
	public void setSearchselect(String searchselect) {
		this.searchselect = searchselect;
	}
	public List<Classroom> getClassroom_list() {
		return classroom_list;
	}
	public void setClassroom_list(List<Classroom> classroom_list) {
		this.classroom_list = classroom_list;
	}
	public String getClassroominfo_html() {
		return classroominfo_html;
	}
	public void setClassroominfo_html(String classroominfo_html) {
		this.classroominfo_html = classroominfo_html;
	}
	public String classroom_search() throws Exception
	{
		//request=ServletActionContext.getRequest();
//		Classroom clr = new Classroom();
//		System.out.println("action");
		if(search==null)
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
			Session session = model.Util.sessionFactory.openSession();
			Criteria q = session.createCriteria(Classroom.class);
			if(searchselect == "1"){
				q.add(Restrictions.eq("classroom_num", search));
			}
			else{
				q.add(Restrictions.eq("principal", search));
			}
			//System.out.println(searchselect + " " + search);
			classroom_list= q.list();
			System.out.println(classroom_list.size());
//			Classroom clr = new Classroom();
			for(int i=0;i<classroom_list.size();i++){
//				clr.setClassroom_num(classroom_list.get(i).classroom_num);
//				clr.setRepertorys(classroom_list.get(i).repertorys);
//				clr.setCapacity(classroom_list.get(i).capacity);
//				clr.setPrincipal(classroom_list.get(i).principal);
//				session.beginTransaction();
//				session.save(clr);
//				session.getTransaction().commit();
				this.classroominfo_html = util.Util.fileToString("/jsp/homepage/widgets/classroominfo.html");
//				session.close();
			}
			session.close();
			
			this.status = "0";
			//this.classroom_id = clr.getId();
			
			//request.getSession().setAttribute("classroom_list",classroom_list);
		}
		return ActionSupport.SUCCESS;
		
	}
  
}
