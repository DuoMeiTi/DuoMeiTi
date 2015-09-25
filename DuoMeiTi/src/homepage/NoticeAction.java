package homepage;


import java.util.Collections;
import java.util.List;
import java.sql.Date;


import org.hibernate.Criteria;
import org.hibernate.Session;


import com.opensymphony.xwork2.ActionSupport;

import model.Notice;;
public class NoticeAction {
	public String title;
	public String content;
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
	public String execute(){
//		System.out.println("NoticeAction.execute()");
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Notice.class);
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		notice = c.list();
		Collections.reverse(notice);
		session.close();
//		System.out.println("NoticeAction.execute()2222");
		return ActionSupport.SUCCESS;
	}
	public String add(){
		// 获得时间
		System.out.println("NoticeAction.add()");
//		Date date = new Date();
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		String time = format.format(date).toString();
		
		Session session = model.Util.sessionFactory.openSession();
		Notice notice3 = new Notice();
		notice3.setTitle(title);
		notice3.setContent(content);
		notice3.setTime(new Date(new java.util.Date().getTime()));
		session.beginTransaction();
		session.save(notice3);
		session.getTransaction().commit();
		status ="ok";
		return ActionSupport.SUCCESS;
	}
//	public  
}
