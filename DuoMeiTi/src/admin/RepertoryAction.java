package admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dto.T_Repertory;
import jxl.Sheet;
import jxl.Workbook;
import model.Repertory;
import util.Const;

public class RepertoryAction extends util.FileUploadBaseAction{
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
	private java.sql.Date rtProdDate;
	private String rtProdDateString = "";
	private java.sql.Date rtApprDate;
	private String rtApprDateString = "";
	private String rtDeviceStatus;
	private int rtReplacePeriod;
	private int rtFilterCleanPeriod;
	private String rtFreqPoint;
	
	//search tag's name
	private String sDevice;
	private String sMainDevice;
	private String sCostDevice;
	private String sDeviceStatus;
	private List<T_Repertory> rtSearch_list = new ArrayList<T_Repertory>();
	
	private String repertory_table;
	
	//tag select func
	private String device[];
	private String mainDevice[];
	private String costDevice[];
	private String deviceStatus[];

	public static java.sql.Date addDays(java.util.Date d, int day)
	{
		return new java.sql.Date(d.getTime() + day * 24*60 *60*1000);
	}
	
	public String execute() throws Exception{
		
		device = Const.device;
		mainDevice = Const.mainDevice;
		/*for(int i = 0; i < mainDevice.length; i++)
			System.out.println(mainDevice[i]);*/
		costDevice = Const.costDevice;
//		deviceStatus = Const.deviceStatus;
		deviceStatus = new String[4];
		deviceStatus[0] = Const.deviceStatus[0];
		deviceStatus[1] = Const.deviceStatus[1];
		deviceStatus[2] = Const.deviceStatus[3];
		deviceStatus[3] = Const.deviceStatus[4];
		
		
		Session session = 
				model.Util.sessionFactory.openSession();
//		model.Util.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Repertory.class);
		repertory_list = c.list();
		//System.out.println(repertory_list);
		Collections.reverse(repertory_list);
//		session.close();
		return SUCCESS;
	}
	
	private String judgeDevice(String rtType)
	{
		boolean markde = false;
		for(int i = 1; i < Const.mainDevice.length; i++)
		{
			if(rtType.equals(Const.mainDevice[i]))
			{
				rtDevice = Const.device[1];
				markde = true;
				break;
			}
		}
		if(markde == false)
		{
			for(int j = 1; j < Const.costDevice.length; j++)
			{
				if(rtType.equals(Const.costDevice[j]))
				{
					rtDevice = Const.device[2];
					markde = true;
					break;
				}
			}
		}
		if(markde == false) return null;
		return rtDevice;
	}
	public String importExcel() throws Exception 
	{
		if(this.file == null)
		{
			this.status = "0";
			return this.SUCCESS;
		}
		
		Workbook rwb= Workbook.getWorkbook(this.file);
		Sheet rs=rwb.getSheet(0);
		int clos=rs.getColumns();
        int rows=rs.getRows();
        
        Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
        for (int i = 1; i < rows; i++) 
        {
        	rtType = rs.getCell(0, i).getContents();
        	rtDevice = this.judgeDevice(rtType);
        	rtNumber = rs.getCell(1, i).getContents();
        	rtVersion = rs.getCell(2, i).getContents();
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	String col3 = rs.getCell(3, i).getContents();
        	if(col3 != "") rtProdDate = new java.sql.Date(sdf.parse(col3).getTime());
        	else rtProdDate = null;
        	String col4 = rs.getCell(4, i).getContents();
        	if(col4 != "") rtApprDate = new java.sql.Date(sdf.parse(col4).getTime());
        	else rtApprDate = null;
        	
        	rtFactorynum = rs.getCell(5, i).getContents();
        	rtDeviceStatus = rs.getCell(6, i).getContents();
        	
        	String col9 = rs.getCell(9, i).getContents();
        	if(col9.equals(""))  rtReplacePeriod = 0;
        	else rtReplacePeriod = Integer.parseInt(col9);
        	
        	if(rtType == "" && rtNumber == "" && rtNumber == "" && rtVersion == "" 
        			&& rtProdDate == null && rtApprDate == null && rtFactorynum == "" && rtDeviceStatus == "")
        	{
        		break;
        	}
        	
        	Repertory rt = new Repertory();
    		rt.setRtDevice(rtDevice);
    		rt.setRtType(rtType);
    		rt.setRtNumber(rtNumber);
    		rt.setRtVersion(rtVersion);
//    		rtProdDateString = rtProdDate.toString();
    		rt.setRtProdDate(rtProdDate);
//    		rtApprDateString = rtApprDate.toString();
    		rt.setRtApprDate(rtApprDate);
    		rt.setRtFactorynum(rtFactorynum);
    		rt.setRtDeviceStatus(rtDeviceStatus);
    		rt.setRtReplacePeriod(rtReplacePeriod);
    		rt.setRtDeadlineData(addDays(new java.util.Date(), rtReplacePeriod));
    		if(rtDeviceStatus.equals("教室"))
        	{
    			String build_name = rs.getCell(7, i).getContents();
    			
    			List build_list = session.createCriteria(model.TeachBuilding.class)
    					.add(Restrictions.eq("build_name",   build_name )).list();
    			
    			if(build_list.isEmpty())
    			{
    				this.status = "2";//数据有误
        			return this.SUCCESS;
    			}
    			
    			
    			model.TeachBuilding build = (model.TeachBuilding)build_list.get(0);
        		String rtClassroom = rs.getCell(8, i).getContents();
        		List q = session.createCriteria(model.Classroom.class)
        						.add(Restrictions.eq("teachbuilding.id", build.getBuild_id()))
        						.add(Restrictions.eq("classroom_num", rtClassroom))        						
        						.list();
        		if(q.isEmpty())
        		{
        			this.status = "2";//数据有误
        			return this.SUCCESS;
        		}
        		else
        		{
        			model.Classroom classroom = (model.Classroom)q.get(0);
        			rt.setClassroom(classroom);
        		}
        	}
    		if(rtType.equals("麦克"))
    		{
    			rtFreqPoint = rs.getCell(10, i).getContents();
    			rt.setRtFreqPoint(rtFreqPoint);
    		}
    		else if(rtType.equals("投影机"))
    		{
    			String col11 = rs.getCell(11, i).getContents();
    			if(col11.equals("")) rtFilterCleanPeriod = 0;
    			else rtFilterCleanPeriod = Integer.parseInt(col11);
    			rt.setRtFilterCleanPeriod(rtFilterCleanPeriod);
    		}
    		session.save(rt);
        }
        session.getTransaction().commit();
		session.close();
		
		this.status = "1";		
		return SUCCESS;
	}
	
	
	public String search() throws Exception{
		/*status  0: empty select
				1: keyword select*/
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Repertory.class);
		//System.out.println(sDevice + "," + sMainDevice + "," + sCostDevice);
		if(sDevice.equals("")) {
			
		}
		else {
			c.add(Restrictions.eq("rtDevice", this.sDevice));
			if(sDevice.equals("主要设备")) {
				if(sMainDevice.equals("")) {
					
				}
				else {
					c.add(Restrictions.eq("rtType", this.sMainDevice));
				}
			}
			else if(sDevice.equals("耗材设备")) {
				if(sCostDevice.equals("")) {
					
				}
				else {
					c.add(Restrictions.eq("rtType", this.sCostDevice));
				}
			}
		}
		if(sDeviceStatus.equals("")) {
			
		}else {
			c.add(Restrictions.eq("rtDeviceStatus", this.sDeviceStatus));
		}
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		repertory_list = c.list();
		if(repertory_list.isEmpty())
		{
			this.status = "0";
		}
		else 
		{
			Collections.reverse(repertory_list);
			this.status = "1";
	        repertory_table = util.Util.getJspOutput("/jsp/admin/widgets/repertoryTable.jsp");
	        //System.out.println(repertory_table);
		}
		session.close();
		
		return SUCCESS;
	}
	
	public String update(){
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(Repertory.class).setFetchMode("classroom", FetchMode.SELECT).add(Restrictions.eq("rtId", rtId));//eq("字段名","变量名")Integer.parseInt
//		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		Repertory rt = (Repertory) c.uniqueResult();
		rt.setRtDevice(rtDevice);
		rt.setRtType(rtType);
		rt.setRtNumber(rtNumber);
		rt.setRtVersion(rtVersion);
		if(rtProdDate != null) rtProdDateString = rtProdDate.toString();
		rt.setRtProdDate(rtProdDate);
		if(rtApprDate != null) rtApprDateString = rtApprDate.toString();
		rt.setRtApprDate(rtApprDate);
		rt.setRtFactorynum(rtFactorynum);
		rt.setRtDeviceStatus(rtDeviceStatus);
		rt.setRtReplacePeriod(rtReplacePeriod);
		rt.setRtFilterCleanPeriod(rtFilterCleanPeriod);
		rt.setRtFreqPoint(rtFreqPoint);
		rt.setRtDeadlineData(addDays(new java.util.Date(), rtReplacePeriod));
		
		session.beginTransaction();
		session.update(rt);
		session.getTransaction().commit();
		session.close();
		this.status = "1";
		return SUCCESS;
	}
	
	public String fetch(){
		Session session = model.Util.sessionFactory.openSession();
		System.out.println(rtId);
		Criteria c = session.createCriteria(Repertory.class).add(Restrictions.eq("rtId", rtId));//eq("字段名","变量名")Integer.parseInt
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		repertory_list = c.list();
//		System.out.println(repertory_list);
//
//		if(repertory_list.isEmpty())
//		{
//			this.status = "0";
//		}
//		else
//		{
//			Collections.reverse(repertory_list);
//			this.status = "1";
//		}
		List tmp_rtSearch_list = c.list();
		rtSearch_list = new ArrayList<T_Repertory>();
		for(int i = 0; i < tmp_rtSearch_list.size(); ++ i)
		{
			Repertory r = (Repertory)tmp_rtSearch_list.get(i);
			
			rtSearch_list.add(new T_Repertory(r));
		}
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
	
	public String insert()
	{
		Repertory rt = new Repertory();
		rt.setRtDevice(rtDevice);
		rt.setRtType(rtType);
		rt.setRtNumber(rtNumber);
		rt.setRtVersion(rtVersion);
		if(rtProdDate != null) rtProdDateString = rtProdDate.toString();
		rt.setRtProdDate(rtProdDate);
		if(rtApprDate != null) rtApprDateString = rtApprDate.toString();
		rt.setRtApprDate(rtApprDate);
		rt.setRtFactorynum(rtFactorynum);
		rt.setRtDeviceStatus(rtDeviceStatus);
		rt.setRtReplacePeriod(rtReplacePeriod);
		rt.setRtFilterCleanPeriod(rtFilterCleanPeriod);
		rt.setRtFreqPoint(rtFreqPoint);
//		rt.setRtFreqPoint(rtFreqPoint);

		rt.setRtDeadlineData(addDays(new java.util.Date(), rtReplacePeriod));
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

	public List getRtSearch_list() {
		return rtSearch_list;
	}

	public void setRtSearch_list(List rtSearch_list) {
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

	public java.sql.Date getRtProdDate() {
		return rtProdDate;
	}

	public void setRtProdDate(java.sql.Date rtProdDate) {
		this.rtProdDate = rtProdDate;
	}

	public java.sql.Date getRtApprDate() {
		return rtApprDate;
	}

	public void setRtApprDate(java.sql.Date rtApprDate) {
		this.rtApprDate = rtApprDate;
	}

	public String getRtDeviceStatus() {
		return rtDeviceStatus;
	}

	public void setRtDeviceStatus(String rtDeviceStatus) {
		this.rtDeviceStatus = rtDeviceStatus;
	}

	public String getSDeviceStatus() {
		return sDeviceStatus;
	}

	public void setSDeviceStatus(String sDeviceStatus) {
		this.sDeviceStatus = sDeviceStatus;
	}

	public String getRtProdDateString() {
		return rtProdDateString;
	}

	public void setRtProdDateString(String rtProdDateString) {
		this.rtProdDateString = rtProdDateString;
	}

	public String getRtApprDateString() {
		return rtApprDateString;
	}

	public void setRtApprDateString(String rtApprDateString) {
		this.rtApprDateString = rtApprDateString;
	}

	public String getRepertory_table() {
		return repertory_table;
	}

	public void setRepertory_table(String repertory_table) {
		this.repertory_table = repertory_table;
	}

	public int getRtReplacePeriod() {
		return rtReplacePeriod;
	}

	public void setRtReplacePeriod(int rtReplacePeriod) {
		this.rtReplacePeriod = rtReplacePeriod;
	}

	public int getRtFilterCleanPeriod() {
		return rtFilterCleanPeriod;
	}

	public void setRtFilterCleanPeriod(int rtFilterCleanPeriod) {
		this.rtFilterCleanPeriod = rtFilterCleanPeriod;
	}

	public String getRtFreqPoint() {
		return rtFreqPoint;
	}

	public void setRtFreqPoint(String rtFreqPoint) {
		this.rtFreqPoint = rtFreqPoint;
	}
	
	
	
	
	
	
	
	

}
