package admin;


import java.util.Collections;
import java.util.List;
import java.sql.Date;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import model.Notice;
import model.User;;
public class NoticeAction {
	public String title;
	public String content;
	
	public int id;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Notice notice2;
	public List<Notice> notice ;
	public String status;
	
	public String submit_type;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//	public 
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Notice> getNotice() {
		return notice;
	}
	public void setNotice(List<Notice> notice) {
		this.notice = notice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String execute() throws Exception{
		//System.out.println("NoticeAction.execute()");
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Notice.class);
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		notice = c.list();
		Collections.reverse(notice);
		session.close();
//		System.out.println("NoticeAction.execute()2222");
		return ActionSupport.SUCCESS;
	}
	//添加公告和更新公告
	public String add(){
		// 获得时间
		System.out.println("NoticeAction.add()");
//		Date date = new Date();
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		String time = format.format(date).toString();
		
		Session session = model.Util.sessionFactory.openSession();
		if(submit_type.equals("add")){
		System.out.println("type:add");
		Notice notice3 = new Notice();
		notice3.setTitle(title);
		notice3.setContent(content);
		notice3.setTime(new Date(new java.util.Date().getTime()));
		session.beginTransaction();
		session.save(notice3);
		session.getTransaction().commit();
		status ="ok";
		}
		else if(submit_type.equals("update")){
			System.out.println("type:update");
			Criteria q =
					 session.createCriteria(Notice.class).add(Restrictions.eq("id",
					 id));
			Notice notice4 = new Notice();
			notice4.setId(id);
			notice4 = (Notice)q.uniqueResult(); 
			notice4.setContent(content);
			notice4.setTitle(title);
			session.beginTransaction();
			session.save(notice4);
			session.getTransaction().commit();
			status ="ok";

			
		}
		return ActionSupport.SUCCESS;
	}
	public String delete(){
		System.out.println("NoticeAction.delete()");
		Session session = model.Util.sessionFactory.openSession();
		Criteria q =
				 session.createCriteria(Notice.class).add(Restrictions.eq("id",
				 id));
//		Notice notice4 = new Notice();
//		notice4.setId(id);
//		notice4 = (Notice)q.uniqueResult(); 
//		if (notice4 == null) {
//			status ="1";//删除的数据不存在，删除失败
//		}
		List paramList = q.list();
		if (paramList.isEmpty()) {
			status ="1";//删除的数据不存在，删除失败
		}
		else{
			session.beginTransaction();
			session.delete(paramList.get(0));
			session.getTransaction().commit();
			//session.close();
			status ="0";//删除成功
		}
		
		return ActionSupport.SUCCESS;
	}  
}
