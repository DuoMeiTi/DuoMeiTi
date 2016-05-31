package admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//import checkin.TimeUtil;
import model.CheckInRecord;
import util.Util;




public class CheckinManageAction extends ActionSupport{
	
	public String execute() throws Exception
	{
		return ActionSupport.SUCCESS;
	}
	java.util.Date startTime;
	java.util.Date endTime;
	String stuName;
	
	String checkinRecordTable;
	List checkinRecordList;
	public String search() throws Exception
	{
		
		System.out.println("SB-------");
		Session session = model.Util.sessionFactory.openSession();
		Criteria c = session.createCriteria(CheckInRecord.class)		
							.addOrder(Order.desc("id"));
				
		
		if(!stuName.equals(""))
		{
			c.createAlias("student", "student")
			.createAlias("student.user", "user")
			.add(Restrictions.eq("user.fullName", stuName));
		}
			
		if(startTime != null)
		{
			c.add(Restrictions.ge("recordtime", startTime));
		}
		
		if(endTime != null)
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(endTime);
			cal.add(Calendar.DATE, 1);			
			c.add(Restrictions.le("recordtime", cal.getTime()));
		}
		
		checkinRecordList = c.list();

		
		checkinRecordTable = Util.getJspOutput("/jsp/admin/widgets/checkinTable.jsp");
		session.close();
		return ActionSupport.SUCCESS;
	}
	
	
	String checkinRecordExcelPath;
	public String exportExcel() throws Exception{

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("签到记录");
		HSSFRow row = sheet.createRow((int) 0);  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        
        HSSFCell cell = row.createCell(0, Cell.CELL_TYPE_STRING);  
        cell.setCellValue("姓名");  
        cell.setCellStyle(style);  
        cell = row.createCell(1, Cell.CELL_TYPE_STRING);  
        cell.setCellValue("学号");  
        cell.setCellStyle(style);  
        cell = row.createCell(2, Cell.CELL_TYPE_STRING);  
        cell.setCellValue("签到时间");  
        cell.setCellStyle(style);

        
        this.search();        

        
		for(int i = 0; i < checkinRecordList.size(); ++i) {
//			System.out.println(L.get(i));
			row = sheet.createRow((int) i + 1);  
			CheckInRecord cr = (CheckInRecord) checkinRecordList.get(i);
            row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(cr.getStudent().getUser().getFullName());  
            row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(cr.getStudent().getStudentId());            
            row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue(util.Util.formatTimestamp(cr.recordtime));
		}
				
		checkinRecordExcelPath = util.Util.CheckInExcelExportPath + "签到记录.xls";
		
		try  
        {
			
            FileOutputStream fout = new FileOutputStream(util.Util.RootPath + checkinRecordExcelPath);  
            wb.write(fout);  
            fout.close();
            wb.close();
        }
		catch (Exception e)  
        {
			e.printStackTrace();
        }

		
		
		return SUCCESS;
	}

	
	
	
	

	
	public java.util.Date getStartTime() {
		return startTime;
	}


	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}


	public java.util.Date getEndTime() {
		return endTime;
	}


	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}


	public String getStuName() {
		return stuName;
	}


	public void setStuName(String stuName) {
		this.stuName = stuName;
	}


	public String getCheckinRecordTable() {
		return checkinRecordTable;
	}


	public void setCheckinRecordTable(String checkinRecordTable) {
		this.checkinRecordTable = checkinRecordTable;
	}


	public List getCheckinRecordList() {
		return checkinRecordList;
	}


	public void setCheckinRecordList(List checkinRecordList) {
		this.checkinRecordList = checkinRecordList;
	}
	public String getCheckinRecordExcelPath() {
		return checkinRecordExcelPath;
	}
	public void setCheckinRecordExcelPath(String checkinRecordExcelPath) {
		this.checkinRecordExcelPath = checkinRecordExcelPath;
	}
	
}
