package homepage;

import java.util.Collections;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;
import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

public class HomepageInformation extends util.PageGetBaseAction {
	
	
	public List notice_list;
	public List check_list;
	public List repair_list;
	public List deviceReplaceList;
	public String notice_list_html;
	public String check_list_html;
	public String repair_list_html;
	public String device_list_html;
		
	/*public String execute() throws Exception{
		
		Session session = model.Util.sessionFactory.openSession();
		notice_list = session.createCriteria(model.Notice.class).list();
		check_list = session.createCriteria(model.CheckRecord.class).list();
		repair_list = session.createCriteria(model.RepairRecord.class).list();
		
		java.util.Date now = new java.util.Date();
		java.sql.Date sql_now = new java.sql.Date(now.getTime());
		
		deviceReplaceList = session.createCriteria(model.Repertory.class)
							.add(Restrictions.le("rtDeadlineData", sql_now))
							.add(Restrictions.eq("rtDeviceStatus", "教室"))
							.list();
		Collections.reverse(notice_list);
		Collections.reverse(check_list);
		Collections.reverse(repair_list);
		session.close();
		return ActionSupport.SUCCESS;
	}*/
	public String  MoreAnnouncement() throws Exception{
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(model.Notice.class).addOrder(Order.desc("id"));
		notice_list = this.makeCurrentPageList(q, 10);
		
		session.close();
		if(this.getIsAjaxTransmission()) // 这是ajax 传输
		{
			notice_list_html = util.Util.getJspOutput("/jsp/homepage/widgets/more_announcementTable.jsp");				
			return "getPage";
		}
		return ActionSupport.SUCCESS;
	}
	
	public String MoreCheckClassroom() throws Exception{
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(model.CheckRecord.class).addOrder(Order.desc("id"));
		check_list = this.makeCurrentPageList(q, 10);
		
		session.close();
		if(this.getIsAjaxTransmission())
		{
			check_list_html =util.Util.getJspOutput("/jsp/homepage/widgets/more_classroomTable.jsp");
			return "getPage";
		}
		return ActionSupport.SUCCESS;
	}
	
	public String MoreEquipmentMaintenance() throws Exception{
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(model.RepairRecord.class).addOrder(Order.desc("id"));
		repair_list = this.makeCurrentPageList(q, 10);
		
		session.close();
		if(this.getIsAjaxTransmission())
		{
			repair_list_html = util.Util.getJspOutput("/jsp/homepage/widgets/more_equipment_maintenanceTable.jsp");
			return "getPage";
		}
		return ActionSupport.SUCCESS;
	}
	
	public String MoreEquipmentReplacement() throws Exception{
		
		Session session = model.Util.sessionFactory.openSession();
		
		java.util.Date now = new java.util.Date();
		java.sql.Date sql_now = new java.sql.Date(now.getTime());
		
		Criteria q = session.createCriteria(model.Repertory.class)
					 .add(Restrictions.le("rtDeadlineData", sql_now))
					 .add(Restrictions.eq("rtDeviceStatus", "教室"))
					 .addOrder(Order.desc("id"));
		deviceReplaceList = this.makeCurrentPageList(q, 10);
		
		session.close();
		
		if(this.getIsAjaxTransmission())
		{
			device_list_html = util.Util.getJspOutput("/jsp/homepage/widgets/more_equipment_replacementTable.jsp");
			return "getPage";
		}
		
		return ActionSupport.SUCCESS;
	}
	
	
	

	public String getDevice_list_html() {
		return device_list_html;
	}

	public void setDevice_list_html(String device_list_html) {
		this.device_list_html = device_list_html;
	}

	public String getRepair_list_html() {
		return repair_list_html;
	}

	public void setRepair_list_html(String repair_list_html) {
		this.repair_list_html = repair_list_html;
	}

	public String getCheck_list_html() {
		return check_list_html;
	}

	public void setCheck_list_html(String check_list_html) {
		this.check_list_html = check_list_html;
	}

	public String getNotice_list_html() {
		return notice_list_html;
	}
	public void setNotice_list_html(String notice_list_html) {
		this.notice_list_html = notice_list_html;
	}
	
	public List getNotice_list() {
		return notice_list;
	}

	public void setNotice_list(List notice_list) {
		this.notice_list = notice_list;
	}

	public List getCheck_list() {
		return check_list;
	}

	public void setCheck_list(List check_list) {
		this.check_list = check_list;
	}

	public List getRepair_list() {
		return repair_list;
	}

	public void setRepair_list(List repair_list) {
		this.repair_list = repair_list;
	}


	public List getDeviceReplaceList() {
		return deviceReplaceList;
	}


	public void setDeviceReplaceList(List deviceReplaceList) {
		this.deviceReplaceList = deviceReplaceList;
	}
	
	
	
	
}
