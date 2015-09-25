package Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import model.AdminProfile;
import model.StudentProfile;
import model.User;
import ognl.IteratorElementsAccessor;
import Message.UserInfo;


public class ChatBoardAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private List<UserInfo> adminList;
	private List<UserInfo> studentList;
	
	public List<UserInfo> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<UserInfo> adminList) {
		this.adminList = adminList;
	}

	public List<UserInfo> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<UserInfo> studentList) {
		this.studentList = studentList;
	}

	public String getUserList(){
		Session session = model.Util.sessionFactory.openSession();
		String hql1="select u.username,u.id from AdminProfile a,User u where a.user=u";
		String hql2="select u.username,u.id from StudentProfile s,User u where s.user=u";
		Query q1=session.createQuery(hql1);
		Query q2=session.createQuery(hql2);
		adminList=new ArrayList<UserInfo>();
		studentList=new ArrayList<UserInfo>();
		Iterator iter=q1.list().iterator();
		traverseUserInfoList(iter, adminList);
		iter=q2.list().iterator();
		traverseUserInfoList(iter, studentList);
		System.out.println(adminList.size());
		System.out.println(studentList.size());
		session.close();
		return SUCCESS;
	}
	
	private void traverseUserInfoList(Iterator iter,List<UserInfo> list){
		while(iter.hasNext()){
			Object[] temp=(Object[])iter.next();
			String username=(String)temp[0];
			Integer id=(Integer)temp[1];
			list.add(new UserInfo(username,id));
		}
	}
}




