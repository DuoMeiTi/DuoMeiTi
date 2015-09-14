package admin;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xpath.internal.operations.Bool;

import model.Repertory;

public class RepertoryAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rtType;
	private String rtNumber;
	private List<Repertory> repertory_list;
	private String status;
	private String add_repertory_html;
	
	/*status 1: OK
			0:  save fail*/

	public String getRtType() {
		return rtType;
	}

	public void setRtType(String rtType) {
		this.rtType = rtType;
	}

	public String getRtNumber() {
		return rtNumber;
	}

	public void setRtNumber(String rtNumber) {
		this.rtNumber = rtNumber;
	}
	
	public List<Repertory> getRepertory_list() {
		return repertory_list;
	}

	public void setRepertory_list(List<Repertory> repertory_list) {
		this.repertory_list = repertory_list;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAdd_repertory_html() {
		return add_repertory_html;
	}

	public void setAdd_repertory_html(String add_repertory_html) {
		this.add_repertory_html = add_repertory_html;
	}

	public String execute() throws Exception{
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Repertory.class); //hibernate session创建查询
		repertory_list = c.list();
		Collections.reverse(repertory_list);//工具类collections用于操作集合类，如List,Set
		session.close();
		//System.out.println(repertory_list);
		return SUCCESS;
	}
	
	public String insert(){
		
		
		if(rtNumber.equals("")||rtNumber.equals("")){
			 this.status = "0";
		}
		else{
			
			Repertory rt = new Repertory();
			rt.setRtType(rtType);
			rt.setRtNumber(rtNumber);
			
			try
			{
				Session session = model.Util.sessionFactory.openSession();
				session.beginTransaction();
				session.save(rt);
				session.getTransaction().commit();
				session.close();
				this.status = "1";
				this.add_repertory_html = util.Util.fileToString("/jsp/admin/widgets/add_repertory.html");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			
			//JOptionPane.showMessageDialog(null, "提交成功");
		}
		
//		System.out.println(rtNumber);
//		System.out.println(rtType);
		
		return SUCCESS;
	}
	
	
}
