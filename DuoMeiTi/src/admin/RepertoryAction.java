package admin;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
//import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;    
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import dto.T_Repertory;
import model.Classroom;
//import jxl.Sheet;
//import jxl.Workbook;
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
	private String add_repertory_html;
	private String rtDevice;
	private java.util.Date rtProdDate;
//	private String rtProdDateString = "";
	private java.util.Date rtApprDate;
//	private String rtApprDateString = "";
	private String rtDeviceStatus;
	private int rtReplacePeriod;
	private int rtFilterCleanPeriod;
	private String rtFreqPoint;
	
	
	String repertoryTable;
	
	private String status;
	private List<Repertory> repertory_list;
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

	
	
	
	List deviceStatusHistoryList;
	
	String deviceStatusHistoryTable;
	
	public static java.sql.Timestamp addDays(java.util.Date d, int day)
	{
		return new java.sql.Timestamp(d.getTime() + day * 24*60 *60*1000);
	}

	Repertory makeRepertory()
	{
		Repertory r = new Repertory();
		r.rtId = rtId;
		r.rtType = rtType;
		r.rtNumber = rtNumber;
		r.rtVersion = rtVersion;
		r.rtFactorynum = rtFactorynum;
		r.rtDevice = rtDevice;
		
		if(rtProdDate != null)
		r.rtProdDate = new java.sql.Timestamp(rtProdDate.getTime());
		
		if(rtApprDate != null)
		r.rtApprDate = new java.sql.Timestamp(rtApprDate.getTime());
		
		
		r.rtReplacePeriod = rtReplacePeriod;		
		r.rtDeadlineDate = addDays(new java.util.Date() , rtReplacePeriod);
		
		r.rtFilterCleanPeriod = rtFilterCleanPeriod;		
		r.rtFreqPoint = rtFreqPoint;


		
		r.rtDeviceStatus = rtDeviceStatus;
		r.rtClassroom = null;
		
		
		
		
		return r;
	}
	
	public String watchDeviceStatusHistory() throws Exception
	{
		Session session = model.Util.sessionFactory.openSession();

		deviceStatusHistoryList = 
				session.createCriteria(model.DeviceStatusHistory.class)
				.add(Restrictions.eq("device.id", rtId))
				.addOrder(Order.asc("id"))
				.list();
		deviceStatusHistoryTable = util.Util.getJspOutput("/jsp/admin/widgets/deviceStatusHistoryTable.jsp");
		session.close();
		return SUCCESS;
	}
	
	
	
	
	public String execute() throws Exception{
		
		device = Const.device;
		mainDevice = Const.mainDevice;
		/*for(int i = 0; i < mainDevice.length; i++)
			System.out.println(mainDevice[i]);*/
		costDevice = Const.costDevice;
//		deviceStatus = Const.deviceStatus;
		deviceStatus = new String[5];
		deviceStatus[0] = Const.deviceStatus[0];
		deviceStatus[1] = Const.deviceStatus[1];
		deviceStatus[2] = Const.deviceStatus[3];
		deviceStatus[3] = Const.deviceStatus[4];
		deviceStatus[4] = Const.deviceStatus[2];
		
		
		Session session = model.Util.sessionFactory.openSession();
//		model.Util.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Repertory.class);
		repertory_list = c.list();
		//System.out.println(repertory_list);
//		Collections.reverse(repertory_list);
		session.close();
		return SUCCESS;
	}
	
	static String getCellStringValue(Row row, int i)
	{
		 Cell res = row.getCell(i);
		 
		 if(res == null) return null;
		 else return row.getCell(i).toString();
		 
	}
	
	static int getCellIntValue(Row row, int i)
	{
		String res = getCellStringValue(row, i);
		if(res == null) return 0;
		else return Integer.parseInt(res);
	}

	static java.util.Date getCellDateValue(Row row, int i)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String cnt = getCellStringValue(row, i);
		if(cnt == null) return null;
		
		java.util.Date res;		
		try
    	{
    		res = dateFormat.parse(cnt);
    	}
    	catch(ParseException e)
    	{
    		res = null;
    	}
		return res;
	}
	static java.sql.Timestamp getCellTimestampValue(Row row, int i)
	{
		java.util.Date res = getCellDateValue(row, i);
		if(res == null) return null;
		else return new java.sql.Timestamp(res.getTime());
	}
	
	
//	麦克:有频点
//	投影机: 有过滤网时间段
	static boolean isValidRepertory(Repertory r)
	{		
		if(r.rtType == null || r.rtDeviceStatus == null) return false;
		System.out.println("SBSB*****");
		if(util.Util.judgeDeviceType(r.rtType) == null) return false;
		System.out.println("SBSB*****---");
		if(!util.Util.isValidDeviceStatus(r.rtDeviceStatus)) return false;
		System.out.println("SBSB*****||||s");
		
		
		return true;
	}
	
	
	public String importExcel() throws Exception 
	{
		System.out.println("SBSBSB****************");
		if(this.file == null)
		{
			this.status = "0";
			return this.SUCCESS;
		}
		


		InputStream stream = new FileInputStream (this.file);
		
		String fileType = fileFileName.substring(fileFileName.lastIndexOf(".") + 1, fileFileName.length());

		Workbook rwb;
		if (fileType.equals("xls")) 
		{
			rwb = new HSSFWorkbook(stream);
        }    
        else if (fileType.equals("xlsx")) 
        {    
        	rwb = new XSSFWorkbook(stream);    
        }    
        else 
        {    
            System.out.println("您输入的excel格式不正确");   
            stream.close();
            return ActionSupport.SUCCESS;
        }    
		
		Sheet rs= rwb.getSheetAt(0);
		int user_id = (int) ActionContext.getContext().getSession().get("user_id");


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Session session = model.Util.sessionFactory.openSession();
		
		int i;
        for (i = 1; ; i++) 
        {
        	Row row = rs.getRow(i);
        	if(row == null) break;
        	Repertory r = new Repertory();
        	
        	
        	System.out.println("GGGG======");
        	
        	r.rtType = getCellStringValue(row, 0);
        	r.rtDevice = util.Util.judgeDeviceType(r.rtType);
        	r.rtNumber = getCellStringValue(row, 1);
        	r.rtVersion = getCellStringValue(row, 2);
        	

        	r.rtProdDate = getCellTimestampValue(row, 3);
        	r.rtApprDate = getCellTimestampValue(row, 4);
        	r.rtFactorynum = getCellStringValue(row, 5);
        	
        	r.rtDeviceStatus = getCellStringValue(row, 6);
        	String teachBuildingName = getCellStringValue(row, 7);
        	String classroom_num = getCellStringValue(row, 8);      	

        	r.rtClassroom = (model.Classroom)session.createCriteria(model.Classroom.class)
        			.createAlias("teachbuilding", "teachbuilding")
        			.add(Restrictions.eq("teachbuilding.build_name", teachBuildingName))
        			.add(Restrictions.eq("classroom_num", classroom_num))
        			.uniqueResult();
        	
 



        	
        	r.rtReplacePeriod = getCellIntValue(row, 9);
        	r.rtFreqPoint = getCellStringValue(row, 10);
        	r.rtFilterCleanPeriod =  getCellIntValue(row, 11);
         	if(!isValidRepertory(r)) break;
          	session.beginTransaction();
        	session.save(r);
        	session.getTransaction().commit();
        	
        	int classroom_id = -1;        	
        	if(r.rtClassroom != null) classroom_id = r.rtClassroom.id;
         	try
        	{
        		util.Util.modifyDeviceStatus(session, r.rtId, user_id, r.rtDeviceStatus, classroom_id);	
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        	
        	

        	
        }
        
        System.out.println("IIIIIIIIiPPPPPPPP");
        
		session.close();
		rwb.close();
		stream.close();
		
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
//		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		repertory_list = c.list();
		if(repertory_list.isEmpty())
		{
			this.status = "0";
		}
		else 
		{
//			Collections.reverse(repertory_list);
			this.status = "1";
	        repertory_table = util.Util.getJspOutput("/jsp/admin/widgets/repertoryTable.jsp");
	        //System.out.println(repertory_table);
		}
		session.close();
		
		return SUCCESS;
	}
	
	public String update() 
	{
		int user_id = (int) ActionContext.getContext().getSession().get("user_id");

		Session session = model.Util.sessionFactory.openSession();
		
		Repertory old_rt = (Repertory)session
							.createCriteria(Repertory.class)
							.add(Restrictions.eq("rtId", rtId))
							.uniqueResult();
		

		Repertory new_rt = makeRepertory();
		
		try		
		{
			
			session.beginTransaction();
			session.evict(old_rt);
			session.update(new_rt);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(!old_rt.rtDeviceStatus.equals(new_rt.rtDeviceStatus))
		{
			util.Util.modifyDeviceStatus(rtId, user_id, new_rt.rtDeviceStatus, -1);
		}
		


		repertory_list = session.createCriteria(model.Repertory.class).list();
		repertoryTable = util.Util.getJspOutput("/jsp/admin/widgets/repertoryTable.jsp");		
		session.close();
		return SUCCESS;
	}
	
	public String fetch(){
		Session session = model.Util.sessionFactory.openSession();
		System.out.println(rtId);
		Criteria c = session.createCriteria(Repertory.class).add(Restrictions.eq("rtId", rtId));//eq("字段名","变量名")Integer.parseInt
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

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

		int user_id = (int) ActionContext.getContext().getSession().get("user_id");
		Repertory rt = makeRepertory();
		
		
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		session.save(rt);
		session.getTransaction().commit();
		util.Util.modifyDeviceStatus(rt.rtId, user_id, rt.rtDeviceStatus, -1);
		
		repertory_list = session.createCriteria(model.Repertory.class).list();

		repertoryTable = util.Util.getJspOutput("/jsp/admin/widgets/repertoryTable.jsp");

		
		session.close();
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
		}
		else
		{
			Repertory r = (Repertory)paramList.get(0);
			List<model.DeviceStatusHistory> dsh_list = session.createCriteria(model.DeviceStatusHistory.class)
							.add(Restrictions.eq("device.id", r.rtId))
							.list();
			
			session.beginTransaction();
			for(model.DeviceStatusHistory i: dsh_list)
			{
				session.delete(i);
			}
			
			
			
			
			session.delete(r);
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

	public java.util.Date getRtProdDate() {
		return rtProdDate;
	}

	public void setRtProdDate(java.util.Date rtProdDate) {
		this.rtProdDate = rtProdDate;
	}

	public java.util.Date getRtApprDate() {
		return rtApprDate;
	}

	public void setRtApprDate(java.util.Date rtApprDate) {
		this.rtApprDate = rtApprDate;
	}

	public String getRtDeviceStatus() {
		return rtDeviceStatus;
	}

	public void setRtDeviceStatus(String rtDeviceStatus) {
		this.rtDeviceStatus = rtDeviceStatus;
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

	public String getRepertoryTable() {
		return repertoryTable;
	}

	public void setRepertoryTable(String repertoryTable) {
		this.repertoryTable = repertoryTable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Repertory> getRepertory_list() {
		return repertory_list;
	}

	public void setRepertory_list(List<Repertory> repertory_list) {
		this.repertory_list = repertory_list;
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

	public String getSDeviceStatus() {
		return sDeviceStatus;
	}

	public void setSDeviceStatus(String sDeviceStatus) {
		this.sDeviceStatus = sDeviceStatus;
	}

	public List<T_Repertory> getRtSearch_list() {
		return rtSearch_list;
	}

	public void setRtSearch_list(List<T_Repertory> rtSearch_list) {
		this.rtSearch_list = rtSearch_list;
	}

	public String getRepertory_table() {
		return repertory_table;
	}

	public void setRepertory_table(String repertory_table) {
		this.repertory_table = repertory_table;
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

	public List getDeviceStatusHistoryList() {
		return deviceStatusHistoryList;
	}

	public void setDeviceStatusHistoryList(List deviceStatusHistoryList) {
		this.deviceStatusHistoryList = deviceStatusHistoryList;
	}

	public String getDeviceStatusHistoryTable() {
		return deviceStatusHistoryTable;
	}

	public void setDeviceStatusHistoryTable(String deviceStatusHistoryTable) {
		this.deviceStatusHistoryTable = deviceStatusHistoryTable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	

}
