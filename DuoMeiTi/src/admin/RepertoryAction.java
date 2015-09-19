package admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import model.Repertory;

public class RepertoryAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rtId;
	private String rtType;
	private String rtNumber;
	private String rtVersion;
	private String rtFactorynum;
	private List<Repertory> repertory_list;
	private String status;
	private String add_repertory_html;
	
	private String rtDevice;
	private String rtMainDevice;
	private List<Repertory> rtSearch_list;
	
	/*status 1: OK
			0:  save fail*/

	public int getRtId() {
		return rtId;
	}

	public void setRtId(int rtId) {
		this.rtId = rtId;
	}
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
	
	public String getRtVersion() {
		return rtVersion;
	}

	public void setRtVersion(String rtVersion) {
		this.rtVersion = rtVersion;
	}

	public String getRtFactorynum() {
		return rtFactorynum;
	}

	public void setRtFactorynum(String rtFactorynum) {
		this.rtFactorynum = rtFactorynum;
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

	public String getRtDevice() {
		return rtDevice;
	}
	public void setRtDevice(String rtDevice) {
		this.rtDevice = rtDevice;
	}
	public String getRtMainDevice() {
		return rtMainDevice;
	}
	public void setRtMainDevice(String rtMainDevice) {
		this.rtMainDevice = rtMainDevice;
	}
	public List<Repertory> getRtSearch_list() {
		return rtSearch_list;
	}
	public void setRtSearch_list(List<Repertory> rtSearch_list) {
		this.rtSearch_list = rtSearch_list;
	}
	public String toString() {
		return this.rtType + ", " + this.rtDevice + "," + this.rtMainDevice;
	}

	public String execute() throws Exception{
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Repertory.class); //hibernate session创建查询
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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
			rt.setRtVersion(rtVersion);
			rt.setRtFactorynum(rtFactorynum);
			
			try
			{
				Session session = model.Util.sessionFactory.openSession();
				session.beginTransaction();
				session.save(rt);
				session.getTransaction().commit();
				session.close();
				
				this.status = "1";
				this.rtId = rt.getRtId();
				this.add_repertory_html = util.Util.fileToString("/jsp/admin/widgets/add_repertory.html");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//JOptionPane.showMessageDialog(null, "提交成功");
		}
		return SUCCESS;
	}
	
	public String delete(){
		
		//List<Object> paramList = new ArrayList<Object>();
		Session session = model.Util.sessionFactory.openSession();
		//System.out.println(rtId);
		Criteria q = session.createCriteria(Repertory.class).add(Restrictions.eq("rtId", rtId));//eq("字段名","变量名")Integer.parseInt
		List paramList = q.list();
		System.out.println(paramList);
		if(paramList.isEmpty()){
			this.status = "0";//error;
		}else{
			session.beginTransaction();
			session.delete(paramList.get(0));
			session.getTransaction().commit();
			
			this.status = "1";//ok
		}
		session.close();

		return SUCCESS;
	}
	
	public String search() {
		/*status  0: empty select
				1: keyword select*/
		System.out.println("~~~"+rtDevice);
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Repertory.class);
		if(rtDevice.equals("main")) {
			//System.out.println(rtDevice);
			c.add(Restrictions.eq("rtType", this.rtMainDevice));
		}
		else if(rtDevice.equals("cost")) {
			
		}
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		rtSearch_list = c.list();
		System.out.println("list:: "+ rtSearch_list);
		if(rtSearch_list.isEmpty()) {
			this.status = "0";
		}else {
			Collections.reverse(rtSearch_list);
			this.status = "1";
			this.add_repertory_html = util.Util.fileToString("/jsp/admin/widgets/add_repertory.html");
		}
		session.close();
		
		return SUCCESS;
	}
	
	
}
