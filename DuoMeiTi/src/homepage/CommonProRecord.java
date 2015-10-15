package homepage;
import java.util.Collections;
import java.util.List;
import java.sql.Date;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import model.Commonproblem;
import model.Notice;
import model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

public class CommonProRecord extends ActionSupport {
	public String title;
	public String content;
	public int id;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Commonproblem notice2;
	public List<Commonproblem> notice ;
	public String status;
	
	public String submit_type;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Commonproblem> getNotice() {
		return notice;
	}
	public void setNotice(List<Commonproblem> notice) {
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
		Criteria c = session.createCriteria(Commonproblem.class);
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		notice = c.list();
		Collections.reverse(notice);
		session.close();
//		System.out.println("NoticeAction.execute()2222");
		return ActionSupport.SUCCESS;
	}
	public String showRecord() throws Exception 
	{
		execute();
		return ActionSupport.SUCCESS;
	}
	
	

}
