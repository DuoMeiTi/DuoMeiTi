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
import model.Message;
import ognl.IteratorElementsAccessor;
import Message.UserInfo;


public class ChatBoardAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private List<UserInfo> adminList;
	private List<UserInfo> studentList;
	
	private int from;
	private int to;
	private String content;
	private String log;
	

	

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

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
	
	
	public String receiveMes(){
		System.out.println("hell");
		try{
			Session session = model.Util.sessionFactory.openSession();
			Message newMes =new Message();
			newMes.content=content;
			String selectFrom="from User u where u.id="+from;
			String selectTo="select newMes.to from User u where u.id="+to;
			
			List<User> fromList = new ArrayList<User>();
			fromList=session.createQuery(selectFrom).list();
			List<User> toList= new ArrayList<User>();
			toList=session.createQuery(selectTo).list();
			newMes.from=fromList.get(0);
			newMes.to=toList.get(0);
			newMes.setReadornot(false);
			
			System.out.println(newMes.content);
			
			session.save(newMes);
			session.close();
			log="发送成功";
			return SUCCESS;
		}
		catch(Exception e)
		{
			log="发送失败";
			return ERROR;
		}
	}
}




