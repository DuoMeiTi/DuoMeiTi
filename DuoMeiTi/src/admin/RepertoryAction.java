package admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import dto.T_Repertory;
import model.Classroom;
import model.RepairRecord;
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
	private java.util.Date rtApprDate;
	private String rtDeviceStatus;
	private int rtReplacePeriod;
	private int rtFilterCleanPeriod;
	private String rtFreqPoint;
	
	
	String repertoryTable;
	
	private String status;
	private List<Repertory> repertory_list;
	//search tag's name
//	private String sDevice;
//	private String sMainDevice;
//	private String sCostDevice;
//	private String sDeviceStatus;
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

	// 按照成员字段构建一个Repertory 对象
	Repertory makeRepertory()
	{
		Repertory r = new Repertory();
		r.rtId = rtId;
		r.rtType = rtType;
		r.rtNumber = rtNumber;
		r.rtVersion = rtVersion;
		r.rtFactorynum = rtFactorynum;

		
		if(rtProdDate != null)
		r.rtProdDate = new java.sql.Timestamp(rtProdDate.getTime());
		
		if(rtApprDate != null)
		r.rtApprDate = new java.sql.Timestamp(rtApprDate.getTime());
		
		
		r.rtReplacePeriod = rtReplacePeriod;		
		r.rtDeadlineDate = addDays(new java.util.Date() , rtReplacePeriod);
		
		r.rtFilterCleanPeriod = rtFilterCleanPeriod;		
		r.rtFreqPoint = rtFreqPoint;


		// 新的设备状态一定不会是 ‘教室’状态，所以设置rtClassroom字段为null
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
		costDevice = Const.costDevice;
		
		deviceStatus = new String[5];
		deviceStatus[0] = Const.deviceStatus[0];
		deviceStatus[1] = Const.deviceStatus[1];
		deviceStatus[2] = Const.deviceStatus[3];
		deviceStatus[3] = Const.deviceStatus[4];
		deviceStatus[4] = Const.deviceStatus[2];
				
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
		else 
		{
			int ans = -1;
			try
			{
				ans = Integer.parseInt(res); 
			}
			catch(Exception e)
			{
//				e.printStackTrace();
			}
			return ans;
		}
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
//  灯泡：有更新时间段
	static String isValidRepertory(Repertory r)
	{		
		if(r.rtType == null || r.rtDeviceStatus == null) 
			return "1设备名称为空或者设备状态为空";
		
		if(util.Util.judgeDeviceType(r.rtType) == null) 
			return "1错误的设备名称"; 
		
		if(!util.Util.isValidDeviceStatus(r.rtDeviceStatus)) 
			return "1错误的设备状态名称"; 
		
		if(r.rtDeviceStatus.equals(util.Util.DeviceClassroomStatus) && r.rtClassroom == null)
			return "1设备状态和设备所属教室不一致";
		if(!r.rtDeviceStatus.equals(util.Util.DeviceClassroomStatus) && r.rtClassroom != null)
			return "1设备状态和设备所属教室不一致";
		
		if(r.rtType.equals("灯泡") && r.rtReplacePeriod < 0)
			return "1灯泡的更换时间段为负数！";

		
		System.out.println("SBSB*****||||s");
		
		
		return "0";
	}
	
	
	String exportExcelPath;	
	final static int ColumnNumber = 12;
	
	public String exportExcel() throws Exception{
		
		try{

		this.search();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
			
		HSSFRow row = sheet.createRow(0);
		for(int j = 0; j < ColumnNumber; ++ j) 
			row.createCell(j );
		
		row.getCell(0).setCellValue("设备类型");
		row.getCell(1).setCellValue("资产编号");
		row.getCell(2).setCellValue("型号");
		row.getCell(3).setCellValue("出厂日期");
		row.getCell(4).setCellValue("审批日期");
		row.getCell(5).setCellValue("出厂号");
		row.getCell(6).setCellValue("使用状态");
		row.getCell(7).setCellValue("教学楼");
		row.getCell(8).setCellValue("教室号");
		row.getCell(9).setCellValue("更换时间段");
		row.getCell(10).setCellValue("频点");
		row.getCell(11).setCellValue("过滤网更换时间");
		
		
		CellStyle cellStyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();            
        cellStyle.setDataFormat(format.getFormat("@"));
		
		for(int i = 0; i < repertory_list.size(); i++)
		{
			Repertory device = repertory_list.get(i);
			
			row = sheet.createRow(i + 1);
			
            
			
			for(int j = 0; j < ColumnNumber; ++ j) 
				row.createCell(j, Cell.CELL_TYPE_STRING);
			

			System.out.println("pppppppppp222222");
			row.getCell(0).setCellValue(device.rtType);
			row.getCell(1).setCellValue(device.rtNumber);
			row.getCell(2).setCellValue(device.rtVersion);

			row.getCell(3).setCellValue(device.rtProdDate != null ? util.Util.formatTimestampToOnlyDate(device.rtProdDate) : "");
			row.getCell(4).setCellValue(device.rtApprDate != null ? util.Util.formatTimestampToOnlyDate(device.rtApprDate) : "");
			
			
			row.getCell(5).setCellValue(device.rtFactorynum);
			row.getCell(6).setCellValue(device.rtDeviceStatus);


			row.getCell(7).setCellValue(device.rtClassroom != null ? device.rtClassroom.teachbuilding.build_name : "");
			row.getCell(8).setCellValue(device.rtClassroom != null ? device.rtClassroom.classroom_num : "");

			row.getCell(9).setCellValue(device.rtType.equals("灯泡") ? Integer.toString(device.rtReplacePeriod) : "");
			row.getCell(10).setCellValue(device.rtType.equals("麦克") ? device.rtFreqPoint : "");
			row.getCell(11).setCellValue(device.rtType.equals("投影仪") ? Integer.toString(device.rtFilterCleanPeriod) : "");
			
			
			
			for(int j = 0; j < ColumnNumber; ++ j) 
			{
				
//	            CellStyle cellStyle = workbook.createCellStyle();
//	            DataFormat format = workbook.createDataFormat();
//	            
//	            cellStyle.setDataFormat(format.getFormat("@"));
	            row.getCell(j).setCellStyle(cellStyle);
	            

			}

//			CellStyle cellStyle = workbook.createCellStyle();
//            DataFormat format = workbook.createDataFormat();            
//            cellStyle.setDataFormat(format.getFormat("@"));
//            row.setRowStyle(cellStyle);
			
		}
//			
//		CellStyle cellStyle = workbook.createCellStyle();
//        DataFormat format = workbook.createDataFormat();            
//        cellStyle.setDataFormat(format.getFormat("@"));
//        
//        for(int j = 0; j < ColumnNumber; ++ j)	
//        	sheet.setDefaultColumnStyle(j, cellStyle);

		
		
		for(int j = 0; j < ColumnNumber; ++ j)			
			sheet.autoSizeColumn(j);
		

		for(int j = 0; j < ColumnNumber; ++ j)
		{
			sheet.setColumnWidth(j, (int)(sheet.getColumnWidth(j) * 1.5));
		}

		
		exportExcelPath = util.Util.ExportDeviceInfoPath + "设备信息.xls";
		
		String fullPath = util.Util.RootPath + exportExcelPath;

		
		OutputStream out = new FileOutputStream(fullPath);
		
		workbook.write(out);
		workbook.close();
		out.close();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return this.SUCCESS;
	}
	
	public String importExcel() throws Exception 
	{
		System.out.println("SBSBSB****************");
		if(this.file == null)
		{
			this.status = "0";
			return this.SUCCESS;
		}
		
		try{ // try begin

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

            this.status = "1您输入的excel格式不正确";
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
        	if(row == null) 
    		{
        		this.status = "0全部设备信息导入成功";
        		break;
    		}
        	Repertory r = new Repertory();
        	
        	
        	System.out.println("GGGG======");
        	
        	r.rtType = getCellStringValue(row, 0);
//        	r.rtDevice = util.Util.judgeDeviceType(r.rtType);
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
        	String validStatus = isValidRepertory(r);
        	if(validStatus.charAt(0) != '0')
        	{
        		this.status = "1成功导入" + (i - 1) + "个设备信息，第" + i + "设备信息有错误：" + validStatus.substring(1);
        		break;
        	}

          	session.beginTransaction();
        	session.save(r);
        	session.getTransaction().commit();        	
        	int classroom_id = -1;        	
        	if(r.rtClassroom != null) classroom_id = r.rtClassroom.id;
    		util.Util.modifyDeviceStatus(session, r.rtId, user_id, r.rtDeviceStatus, classroom_id);	

        }
        
        System.out.println("import END---------------");
        
		session.close();
		rwb.close();
		stream.close();
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
//		this.status = "1";		
		return SUCCESS;
	}
	
	int selectDeviceType;
	
	String selectDeviceStatus;
	public String search() throws Exception{
		
		Session s = model.Util.sessionFactory.openSession();
		Criteria c = s.createCriteria(Repertory.class);
		
		System.out.println("GG");
		System.out.println(org.apache.struts2.ServletActionContext.getRequest().getRequestURI());
		System.out.println(selectDeviceType);
		System.out.println(selectDeviceStatus);
		System.out.println("GG***************");
		if(selectDeviceType >= 0)
		{
			c.add(Restrictions.eq("rtType", util.Util.DeviceList.get(selectDeviceType) ));
		}
		System.out.println("GG===========");
		if(!selectDeviceStatus.equals("all"))
		{
			System.out.println("JJJJJJJ------------");
			c.add(Restrictions.eq("rtDeviceStatus", selectDeviceStatus));
			System.out.println("JJJJJJJ+++++++++++++");
		}
		System.out.println("JJJJJJJ");

		repertory_list = c.list();

		
		System.out.println("JJJJJJJ}}}}}}}}}}}}}}}}");
		System.out.println(repertory_list);
		repertory_table = util.Util.getJspOutput("/jsp/admin/widgets/repertoryTable.jsp");

		
		s.close();
		

		
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
		
		if(old_rt.rtDeviceStatus.equals(this.rtDeviceStatus))
		{
			new_rt.rtDeviceStatus = old_rt.rtDeviceStatus;
			new_rt.rtClassroom = old_rt.rtClassroom;
		}
		
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
			util.Util.modifyDeviceStatus(session, rtId, user_id, new_rt.rtDeviceStatus, -1);
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
		
		System.out.println("ssssssssssss");
		System.out.println(rt.rtId);
		util.Util.modifyDeviceStatus( session, rt.rtId, user_id, rt.rtDeviceStatus, -1);
		
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

//	public String getSDevice() {
//		return sDevice;
//	}
//
//	public void setSDevice(String sDevice) {
//		this.sDevice = sDevice;
//	}
//
//	public String getSMainDevice() {
//		return sMainDevice;
//	}
//
//	public void setSMainDevice(String sMainDevice) {
//		this.sMainDevice = sMainDevice;
//	}
//
//	public String getSCostDevice() {
//		return sCostDevice;
//	}
//
//	public void setSCostDevice(String sCostDevice) {
//		this.sCostDevice = sCostDevice;
//	}
//
//	public String getSDeviceStatus() {
//		return sDeviceStatus;
//	}
//
//	public void setSDeviceStatus(String sDeviceStatus) {
//		this.sDeviceStatus = sDeviceStatus;
//	}

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

	public int getSelectDeviceType() {
		return selectDeviceType;
	}

	public void setSelectDeviceType(int selectDeviceType) {
		this.selectDeviceType = selectDeviceType;
	}

	public String getSelectDeviceStatus() {
		return selectDeviceStatus;
	}

	public void setSelectDeviceStatus(String selectDeviceStatus) {
		this.selectDeviceStatus = selectDeviceStatus;
	}

	public String getExportExcelPath() {
		return exportExcelPath;
	}

	public void setExportExcelPath(String exportExcelPath) {
		this.exportExcelPath = exportExcelPath;
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	

}
