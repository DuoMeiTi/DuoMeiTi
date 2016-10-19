package admin;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


import model.RepairRecord;
import model.Repertory;
import model.User;
import model.Classroom;
import model.TeachBuilding;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

//import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.text.SimpleDateFormat;


public class RepairRecordAction extends ActionSupport {
	
	
	
	public String execute() throws Exception {		
		
		System.out.println("FFJFJFJJ");
		
		return ActionSupport.SUCCESS;
	}
	
	
	int selectDevice;
	String inputRepairman;
	String selectTeachingBuildingName;
	String inputClassroom;
	
	
	java.util.Date inputBeginDate;
	java.util.Date inputEndDate;
	
	String repairRecordTable; 
	String exportPath;
	List repairRecordList;
	
	public String search() throws Exception
	{
		System.out.println("FFFssss");
		Session session = model.Util.sessionFactory.openSession();
		
		Criteria c = session.createCriteria(model.RepairRecord.class);		
		
		System.out.println("FFFssss111");
		if(selectDevice != -1)
		{
			c.add(Restrictions.eq("deviceType", util.Util.DeviceList.get(selectDevice)));		
//			c.createAlias("device", "device").add(Restrictions.eq("device.rtType", util.Util.DeviceList.get(selectDevice)));			
		}
			
		
		if(!inputRepairman.isEmpty())
		{
			c.add(Restrictions.eq("repairmanFullName", inputRepairman));
		}
		
		if(!selectTeachingBuildingName.isEmpty())
		{
			c.add(Restrictions.eq("teachingBuildingName", selectTeachingBuildingName));
		}
		
		if(!inputClassroom.isEmpty())
		{
			c.add(Restrictions.eq("classroomName", inputClassroom));		
		}
		
		System.out.println("SBSB&&&&&&");
		System.out.println(inputBeginDate);
		
		
		
		if(inputBeginDate != null)
		{
			c.add(Restrictions.ge("repairdate", inputBeginDate));
		}
		
		if(inputEndDate != null)
		{
		     Calendar   calendar   =   new   GregorianCalendar(); 
		     calendar.setTime(inputEndDate); 
		     calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
		     inputEndDate=calendar.getTime();   //这个时间就是日期往后推一天的结果 

		     c.add(Restrictions.le("repairdate", inputEndDate));
		}
		
		
		
		try
		{
			repairRecordList = c.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
		repairRecordTable = util.Util.getJspOutput("/jsp/admin/widgets/repairRecordTable.jsp");
		
		
		session.close();
		
		return SUCCESS;
		
	}
	
	
	public String export() throws Exception
	{
		System.out.println("abc");
		this.search();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		try{
			HSSFRow row0 = sheet.createRow(0);
			HSSFCell cell = row0.createCell(0);
			cell.setCellValue(new HSSFRichTextString("设备"));
			cell = row0.createCell(1);
			cell.setCellValue(new HSSFRichTextString("维修人"));
			cell = row0.createCell(2);
			cell.setCellValue(new HSSFRichTextString("教学楼"));
			cell = row0.createCell(3);
			cell.setCellValue(new HSSFRichTextString("教室"));
			cell = row0.createCell(4);
			cell.setCellValue(new HSSFRichTextString("维修内容"));
			cell = row0.createCell(5);
			cell.setCellValue(new HSSFRichTextString("维修时间"));
			for(int i=0; i<repairRecordList.size(); i++){
				HSSFRow row = sheet.createRow(i+1);
				List<RepairRecord> RestrictionsList = repairRecordList;
				RepairRecord r = RestrictionsList.get(i); 
				
				cell = row.createCell(0);
				cell.setCellValue(r.getDeviceType());
				
				cell = row.createCell(1);
				cell.setCellValue(r.getRepairmanFullName());
				
				cell = row.createCell(2);
				cell.setCellValue(r.getTeachingBuildingName());
				
				cell = row.createCell(3);
				cell.setCellValue(r.getClassroomName());

				cell = row.createCell(4);
				cell.setCellValue(r.getRepairdetail());
				
				cell = row.createCell(5);				
				cell.setCellValue(util.Util.formatTimestamp(r.repairdate));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
//		java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
//		String FileName = now.toString() + "设备维修记录.xls"; 
//		exportPath = util.Util.RecordExportPath + FileName;
//		OutputStream out = new FileOutputStream(util.Util.RootPath + util.Util.RecordExportPath + FileName);
		
		
		
		exportPath = util.Util.RecordExportPath +  "设备维修记录.xls";
		OutputStream out = new FileOutputStream(util.Util.RootPath + exportPath);
		
		workbook.write(out);
		workbook.close();
		out.close();
		return SUCCESS;
	}
	
	public int getSelectDevice() {
		return selectDevice;
	}
	public void setSelectDevice(int selectDevice) {
		this.selectDevice = selectDevice;
	}
	public String getInputRepairman() {
		return inputRepairman;
	}
	public void setInputRepairman(String inputRepairman) {
		this.inputRepairman = inputRepairman;
	}
	
public String getSelectTeachingBuildingName() {
		return selectTeachingBuildingName;
	}


	public void setSelectTeachingBuildingName(String selectTeachingBuildingName) {
		this.selectTeachingBuildingName = selectTeachingBuildingName;
	}


	//	public int getSelectTeachBuilding() {
//		return selectTeachBuilding;
//	}
//	public void setSelectTeachBuilding(int selectTeachBuilding) {
//		this.selectTeachBuilding = selectTeachBuilding;
//	}
	public String getInputClassroom() {
		return inputClassroom;
	}
	public void setInputClassroom(String inputClassroom) {
		this.inputClassroom = inputClassroom;
	}

	
	
	public java.util.Date getInputBeginDate() {
		return inputBeginDate;
	}




	public void setInputBeginDate(java.util.Date inputBeginDate) {
		this.inputBeginDate = inputBeginDate;
	}





	public java.util.Date getInputEndDate() {
		return inputEndDate;
	}




	public void setInputEndDate(java.util.Date inputEndDate) {
		this.inputEndDate = inputEndDate;
	}




	public String getRepairRecordTable() {
		return repairRecordTable;
	}
	public void setRepairRecordTable(String repairRecordTable) {
		this.repairRecordTable = repairRecordTable;
	}
	public List getRepairRecordList() {
		return repairRecordList;
	}
	public void setRepairRecordList(List repairRecordList) {
		this.repairRecordList = repairRecordList;
	}
	
	public String getExportPath(){
		return exportPath;
	}
	
	public void setExportPath(String exportPath){
		this.exportPath = exportPath;
	}
	
	
	
	


	
	
	
	
//	public int deviceId;
//	
//	public String repairdetail;
//	
//	public String savestatus;
//
//	public String repairrecordsave() {
//		Session session = null;
//		try	{
//			int user_id = (int) ActionContext.getContext().getSession().get("user_id");
//			session = model.Util.sessionFactory.openSession();
//			Criteria user_criteria = session.createCriteria(User.class);
//			user_criteria.add(Restrictions.eq("id", user_id));
//			User repairman = (User) user_criteria.uniqueResult();
//			
//			Timestamp repairdate = new Timestamp(new java.util.Date().getTime());
//			
//			Criteria repertory_criteria = session.createCriteria(Repertory.class);
//			repertory_criteria.add(Restrictions.eq("rtId", deviceId));
//			Repertory device = (Repertory) repertory_criteria.uniqueResult();
//			
//			RepairRecord repairrecord = new RepairRecord();
//			repairrecord.setDevice(device);
//			repairrecord.setRepairdate(repairdate);
//			repairrecord.setRepairdetail(repairdetail);
//			repairrecord.setRepairman(repairman);
//			
//			session.beginTransaction();
//			session.save(repairrecord);
//			session.getTransaction().commit();
//			
//			this.savestatus = "success";
//		} catch(Exception e)	{
//			this.savestatus = "fail";
//			e.printStackTrace();
//		} finally {
//			if(session != null) session.close();
//		}
//		return SUCCESS;
//	}
//
//	
//	
//	
//	
//	
//	
//	
//	
//	public int getDeviceId() {
//		return deviceId;
//	}
//
//	public void setDeviceId(int deviceId) {
//		this.deviceId = deviceId;
//	}
//
//	public String getRepairdetail() {
//		return repairdetail;
//	}
//
//	public void setRepairdetail(String repairdetail) {
//		this.repairdetail = repairdetail;
//	}
//
//	public String getSavestatus() {
//		return savestatus;
//	}
//
//	public void setSavestatus(String savestatus) {
//		this.savestatus = savestatus;
//	}
}
