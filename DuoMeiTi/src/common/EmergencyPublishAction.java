package common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//import Repair.RepairDAO;
//import RepairImpl.RepairDAOImpl;
import model.CheckRecord;
import model.Classroom;
import model.EmergencyInfo;
import model.EmergencyInfoRead;
import model.Repertory;
import model.RoomPicture;
import model.StudentProfile;
import model.TeachBuilding;
import model.User;



/*
 * 在紧急消息提醒中，分为两部分，一部分称为紧急消息(EmergencyInfo)，一部分称为紧急消息评论(EmergencyComment)
 * 但是在数据库中存储都叫EmergencyInfo
 * 
 * 
 * EmergencyInfoRead表记录了当前对应人员user 对 紧急消息info已经阅读到了哪个位置，
 * 对于一个紧急消息，无论其有多少条评论，在EmergencyInfoRead表中有且仅有一个记录，
 * 对于EmergencyInfoRead的一个记录（user, info）表示
 * info所对应的紧急消息+紧急消息评论的整个列表已经阅读到了所有id小于等于info.id的所有紧急消息或者评论
 * 针对EmergencyInfoRead表的操作应该仅仅通过obtainLastEmergencyInfoRead， modifyEmergencyInfoRead
 * 两个函数来操作，不应该直接操作EmergencyInfoRead表，因为很容易搞乱！！！
 * 
 */


public class EmergencyPublishAction extends ActionSupport {

	

	
	ArrayList<Boolean> notReadList;
	
	String emergencyCommentTable;
	ArrayList<EmergencyInfo> emergencyCommentList;
	int emergencyInfoId;
	

	
	// 获取人员user_id， 针对紧急消息info_id（一定不可以是评论，要保证info_id必须是紧急消息，而不是评论）
	// 所代表的列表中所阅读到的位置的记录，若没有则返回null	
	synchronized  public static EmergencyInfoRead obtainLastEmergencyInfoRead(Session s, int user_id, int info_id)
	{
		return (EmergencyInfoRead)
		s.createCriteria(model.EmergencyInfoRead.class)
		.add(Restrictions.eq("user.id", user_id))
		.createAlias("info", "info")
		.add(Restrictions.or(Restrictions.eq("info.id", info_id), 
							 Restrictions.eq("info.info.id", info_id)))
		.uniqueResult();
	}
	
	//修改人员user，对于紧急消息info的阅读到的位置到new_info
	synchronized public static void  modifyEmergencyInfoRead(Session s, int user_id, int info_id , int new_info_id)  
	{
		EmergencyInfo new_info = new EmergencyInfo();
		new_info.id = new_info_id;
		
		EmergencyInfoRead lastRead = obtainLastEmergencyInfoRead(s, user_id, info_id);
		if(lastRead == null)
		{
			lastRead = new EmergencyInfoRead();
			User user = new User();
			user.id = user_id;
			
			lastRead.info = new_info;
			lastRead.user = user;			
		}
		else
		{
			lastRead.info = new_info;
		}
		s.beginTransaction();
		s.saveOrUpdate(lastRead);
		
		s.getTransaction().commit();
	}

	
	// 刷新评论的Table
	private void refreshEmergencyCommentTable(Session  s )
	{
		System.out.println("enter !!!done");
		int user_id = (int) ActionContext.getContext().getSession().get("user_id");
		emergencyCommentList = (ArrayList<EmergencyInfo>)				
				s.createCriteria(model.EmergencyInfo.class)
				.add(Restrictions.eq("info.id", emergencyInfoId))
				.addOrder(Order.asc("id"))
				.list();
		System.out.println("enter !!!done2222222");
		
		EmergencyInfoRead lastRead = obtainLastEmergencyInfoRead(s, user_id, emergencyInfoId);
		
		notReadList = new ArrayList<Boolean>();
		for(EmergencyInfo i: emergencyCommentList)
		{
			if(lastRead == null)
			{
				notReadList.add(true);
				continue;
			}
			if(i.id > lastRead.info.id)
				notReadList.add(true);
			else
				notReadList.add(false);
		}
		
		System.out.println("done");


		emergencyCommentTable = util.Util.getJspOutput("/jsp/base/widgets/emergencyCommentTable.jsp");
	}
	
	
	String emergencyInfoTable;
	ArrayList<EmergencyInfo> emergencyInfoList;
	// 刷新紧急消息的Table
	private void refreshEmergencyInfoTable( Session  s)
	{
		int user_id = (int) ActionContext.getContext().getSession().get("user_id");
		System.out.println("enter111111111");
		// 获取所有的紧急消息
		emergencyInfoList = (ArrayList<EmergencyInfo>)
				s.createCriteria(model.EmergencyInfo.class)
				.add(Restrictions.isNull("info"))
				.addOrder(Order.desc("id"))
				.list();
		System.out.println("enter2222");
		notReadList = new ArrayList<Boolean>();
		for(EmergencyInfo i: emergencyInfoList)
		{
			System.out.println("enter4444");
			System.out.println(user_id);
			System.out.println(i.id);
			EmergencyInfoRead tmp_read = null;
			try
			{
				 tmp_read = obtainLastEmergencyInfoRead(s, user_id, i.id);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			

			System.out.println("enter55555");
			if(tmp_read == null)
			{
				notReadList.add(true);
			}
			else 
				notReadList.add(false);
 		}
 		emergencyInfoTable = util.Util.getJspOutput("/jsp/base/widgets/emergencyInfoTable.jsp");

	}
	public String obtainEmergencyCommentTable() throws Exception
	{
		Session s = model.Util.sessionFactory.openSession();
 		
		refreshEmergencyCommentTable(s);
		clearNotReadListForEmergencyComment(s);
		
		
		s.close();
		return this.SUCCESS;
	}
	
	
	private void clearNotReadListForEmergencyInfo(Session s)
	{
		int user_id = (int) ActionContext.getContext().getSession().get("user_id");
//		emergencyInfoList
//		notReadList;

		for(int i = 0; i < emergencyInfoList.size(); ++ i)
		{
			if(notReadList.get(i))
			{
				EmergencyInfo em = emergencyInfoList.get(i);
				modifyEmergencyInfoRead(s, user_id, em.id, em.id);
			}
		}
				
	}
	private void clearNotReadListForEmergencyComment(Session s)
	{
		int user_id = (int) ActionContext.getContext().getSession().get("user_id");
//		emergencyInfoList
//		notReadList;
		for(int i = 0; i < emergencyCommentList.size(); ++ i)
		{
			if(notReadList.get(i))
			{
				EmergencyInfo em = emergencyCommentList.get(i);
				modifyEmergencyInfoRead(s, user_id, em.info.id, em.id);
			}
		}

	}
	
	
	
	
	public String obtainEmergencyInfoTable() throws Exception
	{
		Session s = model.Util.sessionFactory.openSession();
		
		System.out.println("GGG");
		
		refreshEmergencyInfoTable(s);
		System.out.println("GGG22222");
		clearNotReadListForEmergencyInfo(s);
		System.out.println("GGG3333333333");
		s.close();
		return this.SUCCESS;
	}
	
	/*计算是否有新的紧急消息, 新的紧急消息列表放入emergencyInfoList,
	 *这里的未读紧急消息包含三种情况：
	 *1, 未读的紧急消息
	 *2, 由当前用户发布的已读紧急消息，但是含有当前用户未读的评论
	 *3, 不是由当前用户发布的 已读的紧急消息，但是当前用户曾经评论过次紧急消息，并且现在有了新的当前用户未读的评论
	 */
	 
	private void refreshNotReadEmergencyInfoTable(Session s) throws Exception
	{
		int user_id = (int) ActionContext.getContext().getSession().get("user_id");
		User user =(User) 
				s.createCriteria(model.User.class)
					.add(Restrictions.eq("id", user_id))
					.uniqueResult();
		
		ArrayList<EmergencyInfo> all_info = (ArrayList<EmergencyInfo >)
											s.createCriteria(model.EmergencyInfo.class)
											.add(Restrictions.isNull("info"))
											.addOrder(Order.desc("id"))
											.list();
		
		emergencyInfoList  =  new ArrayList<EmergencyInfo>();
		notReadList = new ArrayList<Boolean>();
		
		for(EmergencyInfo i : all_info)
		{
			
			EmergencyInfoRead lastRead = obtainLastEmergencyInfoRead(s, user_id, i.id);

			// 这是一个当前用户未读的info
			if(lastRead == null)
			{
				emergencyInfoList.add(i);
				notReadList.add(true);
				continue;
			}
			System.out.println("HAHAHAH");
			// 当前用户发布了这个info  或者   当前用户评论了当前info
			if(i.user.id == user_id || s.createCriteria(model.EmergencyInfo.class)
										.add(Restrictions.eq("user.id", user_id))										
										.add(Restrictions.eq("info.id", i.id))										
										.list()
										.size() > 0  )
			{
				EmergencyInfo lastComment = 
							(EmergencyInfo)
							s.createCriteria(model.EmergencyInfo.class)							
							.add(Restrictions.eq("info.id", i.id))
							.addOrder(Order.desc("id"))
							.setMaxResults(1)							
							.uniqueResult() ;
				
				EmergencyInfoRead lastCommentRead =	
						(EmergencyInfoRead)
						s.createCriteria(model.EmergencyInfoRead.class)
						.add(Restrictions.eq("user.id", user_id))
						.createAlias("info", "main_info")
						.add(Restrictions.eq("main_info.info.id", i.id))
						.uniqueResult();
				
				if(lastComment != null && 
		     		 (lastCommentRead == null || lastComment.id != lastCommentRead.info.id))
				{
					System.out.println("ENTER");
					emergencyInfoList.add(i);
					notReadList.add(false);
					continue;
				}
			}
		}
		emergencyInfoTable = util.Util.getJspOutput("/jsp/base/widgets/emergencyInfoTable.jsp");

		
	}
	
	public String obtainNotReadEmergencyInfoTable() throws Exception
	{
		
		
		Session s = model.Util.sessionFactory.openSession();
		
		refreshNotReadEmergencyInfoTable(s);
		clearNotReadListForEmergencyInfo(s);

		s.close();
		return this.SUCCESS;
	}
	
	
	
	
	String emergencyInfoContent;
	public String addEmergencyInfo() throws Exception
	{
		
		int user_id = (int) ActionContext.getContext().getSession().get("user_id");
		
		
		Session s = model.Util.sessionFactory.openSession();
		
		User user = (User) 
				s.createCriteria(model.User.class)
					.add(Restrictions.eq("id", user_id))
					.uniqueResult();

		
		
		
		EmergencyInfo em = new EmergencyInfo();
		em.content = emergencyInfoContent;
		em.date = new Timestamp(new java.util.Date().getTime());		 
		em.user = user;
		
		if(emergencyInfoId > 0)	
		{
			EmergencyInfo main_em = new EmergencyInfo();
			main_em.id = emergencyInfoId;
			em.info = main_em;
		}
		else 
			em.info = null;
			
		s.beginTransaction();
		s.save(em);
		s.getTransaction().commit();
		System.out.println("EM ID:::::::::::;");
		System.out.println(em.id);
		
		// 添加评论
		if(emergencyInfoId > 0)	
		{
			modifyEmergencyInfoRead(s, user_id, emergencyInfoId, em.id);
			refreshEmergencyCommentTable(s);
			
			clearNotReadListForEmergencyComment(s);
		}
		else // 添加是紧急消息
		{
			modifyEmergencyInfoRead(s, user_id, em.id, em.id);
			
			refreshEmergencyInfoTable(s);			
			clearNotReadListForEmergencyInfo(s);
		}
		
		
		s.close();
		return this.SUCCESS;
	}
	
	
	
	
	
	public String queryNewInfo() throws Exception
	{
		Session s = model.Util.sessionFactory.openSession();
		refreshNotReadEmergencyInfoTable(s);
		s.close();
		
//		emergencyInfoList
		
		return this.SUCCESS;
	}
	public String getEmergencyCommentTable() {
		return emergencyCommentTable;
	}
	public void setEmergencyCommentTable(String emergencyCommentTable) {
		this.emergencyCommentTable = emergencyCommentTable;
	}
	public ArrayList<EmergencyInfo> getEmergencyCommentList() {
		return emergencyCommentList;
	}
	public void setEmergencyCommentList(ArrayList<EmergencyInfo> emergencyCommentList) {
		this.emergencyCommentList = emergencyCommentList;
	}
	public int getEmergencyInfoId() {
		return emergencyInfoId;
	}
	public void setEmergencyInfoId(int emergencyInfoId) {
		this.emergencyInfoId = emergencyInfoId;
	}
	public String getEmergencyInfoTable() {
		return emergencyInfoTable;
	}
	public void setEmergencyInfoTable(String emergencyInfoTable) {
		this.emergencyInfoTable = emergencyInfoTable;
	}
	public ArrayList<EmergencyInfo> getEmergencyInfoList() {
		return emergencyInfoList;
	}
	public void setEmergencyInfoList(ArrayList<EmergencyInfo> emergencyInfoList) {
		this.emergencyInfoList = emergencyInfoList;
	}
	public String getEmergencyInfoContent() {
		return emergencyInfoContent;
	}
	public void setEmergencyInfoContent(String emergencyInfoContent) {
		this.emergencyInfoContent = emergencyInfoContent;
	}
//	public int getEmergencyInfoInfoId() {
//		return emergencyInfoInfoId;
//	}
//	public void setEmergencyInfoInfoId(int emergencyInfoInfoId) {
//		this.emergencyInfoInfoId = emergencyInfoInfoId;
//	}
	public ArrayList<Boolean> getNotReadList() {
		return notReadList;
	}
	public void setNotReadList(ArrayList<Boolean> notReadList) {
		this.notReadList = notReadList;
	}

	

	


}
