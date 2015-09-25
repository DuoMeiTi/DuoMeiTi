package admin;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import model.Repertory;
import util.Const;

public class RepertoryAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//insert tag's name
	private int rtId;
	private String rtType;
	private String rtNumber;
	private String rtVersion;
	private String rtFactorynum;
	private List<Repertory> repertory_list;
	private String status;
	private String add_repertory_html;
	private String rtDevice;
	
	//search tag's name
	private String sDevice;
	private String sMainDevice;
	private String sCostDevice;
	private List<Repertory> rtSearch_list;
	
	//tag select func
	private String device[];
	private String mainDevice[];
	private String costDevice[];
	private String deviceStatus[];

	
	public String execute() throws Exception{
		
		device = Const.device;
		mainDevice = Const.mainDevice;
		/*for(int i = 0; i < mainDevice.length; i++)
			System.out.println(mainDevice[i]);*/
		costDevice = Const.costDevice;
		deviceStatus = Const.deviceStatus;
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Repertory.class); //hibernate session创建查询
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		repertory_list = c.list();
		Collections.reverse(repertory_list);//工具类collections用于操作集合类，如List,Set
		session.close();
		//System.out.println(repertory_list);
		return SUCCESS;
	}
	
	public String search() {
		/*status  0: empty select
				1: keyword select*/
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Repertory.class);
		//System.out.println(sDevice + "," + sMainDevice + "," + sCostDevice);
		if(sDevice.equals("")) {
			
		}else {
			c.add(Restrictions.eq("rtDevice", this.sDevice));
			if(sDevice.equals("主要设备")) {
				if(sMainDevice.equals("")) {
					
				}else {
					c.add(Restrictions.eq("rtType", this.sMainDevice));
				}
			}else if(sDevice.equals("耗材设备")) {
				if(sCostDevice.equals("")) {
					
				}else {
					c.add(Restrictions.eq("rtType", this.sCostDevice));
				}
			}
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
	
	public String update(){
		System.out.println(rtId + "," + rtDevice + "," + rtType);
		
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Repertory.class).setFetchMode("classroom", FetchMode.SELECT).add(Restrictions.eq("rtId", rtId));//eq("字段名","变量名")Integer.parseInt
//		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		Repertory rt = (Repertory) c.uniqueResult();
//System.out.println(rtDevice + " " + rtType + " " + rtNumber + " " + rtVersion + " " + rtFactorynum);
System.out.println("id:" + rtId);
		rt.setRtDevice(rtDevice);
System.out.println("===============1");
		rt.setRtType(rtType);
System.out.println("===============2");
		rt.setRtNumber(rtNumber);
System.out.println("===============3");
		rt.setRtVersion(rtVersion);
System.out.println("===============4");
		rt.setRtFactorynum(rtFactorynum);
System.out.println("begin update");
		
		session.beginTransaction();
		session.update(rt);
		session.getTransaction().commit();
		session.close();
		System.out.println(rtDevice + ",+++" + rtType);
		this.status = "1";
		return SUCCESS;
	}
	
	public String fetch(){
		Session session = model.Util.sessionFactory.openSession();
		System.out.println(rtId);
		Criteria c = session.createCriteria(Repertory.class).add(Restrictions.eq("rtId", rtId));//eq("字段名","变量名")Integer.parseInt
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		rtSearch_list = c.list();
		System.out.println(rtSearch_list);
		if(rtSearch_list.isEmpty()){
			this.status = "0";//error;
		}else{
			Collections.reverse(rtSearch_list);
			this.status = "1";//ok
		}
		session.close();
		return SUCCESS;
	}
	
	public String insert(){
			
		Repertory rt = new Repertory();
		rt.setRtDevice(rtDevice);
		rt.setRtType(rtType);
		rt.setRtNumber(rtNumber);
		rt.setRtVersion(rtVersion);
		rt.setRtFactorynum(rtFactorynum);
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		session.save(rt);
		session.getTransaction().commit();
		session.close();
		
		this.status = "1";
		this.rtId = rt.getRtId();
		this.add_repertory_html = util.Util.fileToString("/jsp/admin/widgets/add_repertory.html");
		
			//JOptionPane.showMessageDialog(null, "提交成功");
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

	public String getSDevice() {
		return sDevice;
	}

	public void setSDevice(String sDevice) {
		this.sDevice = sDevice;
	}

	public String getSMainDevice() {
		return sMainDevice;
	}

	public void setSMainDevice(String sMainDevice) {
		this.sMainDevice = sMainDevice;
	}

	public String getSCostDevice() {
		return sCostDevice;
	}

	public void setSCostDevice(String sCostDevice) {
		this.sCostDevice = sCostDevice;
	}

	public List<Repertory> getRtSearch_list() {
		return rtSearch_list;
	}

	public void setRtSearch_list(List<Repertory> rtSearch_list) {
		this.rtSearch_list = rtSearch_list;
	}

	public String[] getDevice() {
		return device;
	}

	public void setDevice(String[] device) {
		this.device = device;
	}

	public String[] getMainDevice() {
		return mainDevice;
	}

	public void setMainDevice(String[] mainDevice) {
		this.mainDevice = mainDevice;
	}

	public String[] getCostDevice() {
		return costDevice;
	}

	public void setCostDevice(String[] costDevice) {
		this.costDevice = costDevice;
	}

	public String[] getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String[] deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	
	

	
	
	
	
	

}
