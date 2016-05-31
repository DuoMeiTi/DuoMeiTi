package student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

//import page.PageBean;
//import page.PageMessage;
import model.Classroom;
import model.Repertory;
import model.StudentProfile;
import model.TeachBuilding;
import model.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

//import dto.T_Classroom;



public class ClassroomManageAction extends ActionSupport {
//	public int build_id;
//	
//	public String build_name;
//	
	public List classrooms;
//	
//	//for classroom_manage.js ajax
//	public String searchselect;
//	
//	public String query_condition;
//	
//	public String status;
//	
//	public String classroominfo_html;
//	
//	public int currPage;
//	
////	public int pageSize;
//	
//	//for classroom_manage.js ajax
//	public String queryResult;
//	
//	public String stuId;
//	
//	public String add_classroom_num;
//	
//	public String add_status;
//	
//	public String submit_type;
//
//	@SuppressWarnings("unchecked")
//	private Map request;
//	
//	private String path;
//	
//	private TeachBuilding build;
	
	public String classroomList() throws Exception {
		
		Session s = model.Util.sessionFactory.openSession();
	
		int user_id=(int)ActionContext.getContext().getSession().get("user_id");
		int student_id =(int) ActionContext.getContext().getSession().get("student_id" );
//		int 
		
		classrooms = s.createCriteria(model.Classroom.class)
					.add(Restrictions.eq("principal.id", student_id))
					.list();
//		String hql = "SELECT c.id,c.classroom_num,u.id,u.fullName,t.id from Classroom c,User u,TeachBuilding t,StudentProfile s where c.teachbuilding=t and c.principal=s and s.user=u and u.id="+user_id;
//
//		Query query = session.createQuery(hql);	
//		List result= query.list();
//		
//		
//		classrooms = new ArrayList<T_Classroom>();
//		//教室ID，教室号，设备名，学生ID，学生名
//		for(int i=0;i<result.size();i++){
//			Object[] tuple = (Object[])result.get(i);
//			T_Classroom t_classroom = new T_Classroom();
//			t_classroom.id = (int)tuple[0];
//			t_classroom.teachbuilding_id=(int)tuple[4];
//			t_classroom.classroom_num = (String)tuple[1];
//			t_classroom.principal_name = (String)tuple[3];
//			t_classroom.principal_stuId = String.valueOf((int)tuple[2]);
//			t_classroom.repertorys =null;
//			classrooms.add(t_classroom);
//		}
		
		s.close();
		
		return SUCCESS;
	}

	public List getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(List classrooms) {
		this.classrooms = classrooms;
	}
	
	

}
