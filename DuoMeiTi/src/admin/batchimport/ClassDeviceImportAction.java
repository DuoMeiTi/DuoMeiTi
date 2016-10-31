package admin.batchimport;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Classroom;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Repertory;

/**教室设备批量导入*/
public class ClassDeviceImportAction extends util.FileUploadBaseAction{
	
	
	int execute_selectTeachBuilding;
	Map<String, ArrayList<Repertory> > execute_classroomDeviceMap;
	public String execute() throws Exception{
 
		Session s = model.Util.sessionFactory.openSession();
		List<model.Classroom> execute_classroomList;
		execute_classroomList = util.Util.obtainClassroomList(s, execute_selectTeachBuilding);
		
		execute_classroomDeviceMap = new TreeMap<String, ArrayList<Repertory>>();
		
		for(Classroom classroom: execute_classroomList)
		{
			execute_classroomDeviceMap.put(classroom.classroom_num, new ArrayList<Repertory>());
		}
		
		
		
		List<Repertory> deviceList =
					
				util.Util.getListWithOneEqualRestriction(
						s, Repertory.class, "rtClassroom.teachbuilding.build_id", execute_selectTeachBuilding);
		
		
		for(Repertory device : deviceList)
		{
			execute_classroomDeviceMap.get(device.rtClassroom.classroom_num).add(device);
		}
		
		

		
		s.close();


		return this.SUCCESS;
	}	
	
	
	
	String importExcel_status;
	public String importExcel() throws Exception 
	{
		System.out.println("SBSBSB****************");
		System.out.println(fileFileName);
		
		if(this.file == null)
		{
			return SUCCESS;
		}
		
		System.out.println("SBSBSB****************");
		
		try{ // try begin

			InputStream stream = new FileInputStream (this.file);
			
			String fileType = fileFileName.substring(fileFileName.lastIndexOf(".") + 1, fileFileName.length());
			System.out.println("gggggggggggg");
			System.out.println(fileType);
			System.out.println(this.fileContentType);
			
			
			
			Workbook workbook;
			if (fileType.equals("xls")) 
			{
				System.out.println("gggggggggggg333");
				workbook = new HSSFWorkbook(stream);
	        }    
//	        else if (fileType.equals("xlsx")) 
//	        {    
//	        	System.out.println("gggggggggggg1111");
//	        	try
//	        	{
//	        		System.out.println("gggggggggggg1111");
//	        		workbook = new XSSFWorkbook(stream);
//	        		System.out.println("ggggggggggggttttt");
//	        	}
//	        	catch(Exception e)
//	        	{
//	        		System.out.println("ggggggggggggttttt435345");
//	        		e.printStackTrace();
//	        		return ActionSupport.SUCCESS;
//	        	}
//	        	System.out.println("gggggggggggg888");
//	        	
//	        }    
	        else 
	        {    
	
	        	System.out.println("gggggggggggg2222");
//	            this.status = "1您输入的excel格式不正确";
	            stream.close();
	            return ActionSupport.SUCCESS;
	        }    
			
			
			System.out.println("SBSBSB****************++");
			Session session = model.Util.sessionFactory.openSession();
			int user_id = (int) ActionContext.getContext().getSession().get("user_id");
			for(int sheetId = 0; sheetId < workbook.getNumberOfSheets(); ++ sheetId)
			{
				Sheet sheet = workbook.getSheetAt(sheetId);
				
				
				String teachBuildingName = workbook.getSheetName(sheetId);
				System.out.println(teachBuildingName);
				Row firstRow = sheet.getRow(0);
				for(int rowId = 1; ; rowId++)
				{	
					
					Row row = null;
					
					//探测接下来10行是否有数据，防止中间有空行，后边的数据没有检测到
					for(int cot = 0; cot < 10; cot ++)
					{
						if(sheet.getRow(rowId) != null)
						{
							row = sheet.getRow(rowId);
							break;
						}
						else rowId ++;
					}
					// 表示已经没有数据了
					if(row == null)
					{
						break;
					}
					
					// 第0列位教师号
					Cell classroomCell = row.getCell(0);
					//表示此行没有数据，继续探测下一行
					if(classroomCell == null)
					{
						continue;
					}
					
					String classroomNum = classroomCell.toString();
					if(classroomNum.contains("."))
					{
						classroomNum = classroomNum.substring(0, classroomNum.indexOf('.'));
					}
					// 如果为空则跳过
					if(classroomNum.isEmpty())
					{
						continue;
					}

					Criteria classroomCriteria =  util.Util.getCriteriaWithOneEqualRestriction(
							session, model.Classroom.class, "teachbuilding.build_name", teachBuildingName);
					classroomCriteria.add(Restrictions.like("classroom_num", classroomNum + "%"));
					
					List<Classroom> classrooms = classroomCriteria.list();
					//有教室号前缀一致，跳过不予理睬
					if(classrooms.size() != 1)
					{
						continue;
					}
					
					Classroom classroom = classrooms.get(0);
					
					System.out.print(classroom.classroom_num + " ");					
					for(int colId = 1; ; colId++)
					{
						Cell cell = row.getCell(colId);
						if(cell == null) break;
						
						String deviceName = firstRow.getCell(colId).toString();
						//表示此设备类型（主要设备还是耗材设备）未知
						if(util.Util.judgeDeviceType(deviceName) == null)
						{
							System.out.println("==="+deviceName + "----");
							continue;
						}
												
						Criteria deviceCriteria = util.Util.getCriteriaWithOneEqualRestriction(
								session, model.Repertory.class, "rtClassroom.id", classroom.id);
						util.Util.addOneEqualRestriction(deviceCriteria, "rtType", deviceName);

						
						if(!deviceCriteria.list().isEmpty())
						{
							System.out.println(classroomNum + ", " + deviceName + "已经添加过");
							continue;
						}
						
						Repertory device = new Repertory();
						device.rtType = deviceName;
						
						//先将设备的所有描述信息存入设备资产编号中，之后再做处理
						device.rtNumber =  cell.toString();						
						session.beginTransaction();
						session.save(device);
						session.getTransaction().commit();
						
						util.Util.modifyDeviceStatus(session, device.rtId, user_id, util.Util.DeviceClassroomStatus, classroom.id);
						System.out.print(cell.toString() + " ");
					}
					
					System.out.println("");
					
					
				}
				System.out.println("");
				System.out.println("");
			}
			
			
			
//			Sheet rs= rwb.getSheetAt(0);
//			int user_id = (int) ActionContext.getContext().getSession().get("user_id");
//	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	        
//			
//	        
//	        
//			int i;
//	        for (i = 1; ; i++) 
//	        {
//	        	Row row = rs.getRow(i);
//	        	if(row == null) 
//	    		{
//	//        		this.status = "0全部设备信息导入成功";
//	        		break;
//	    		}
//	        	Repertory r = new Repertory();
//	        	
//	        	
//	        	System.out.println("GGGG======");
//	        	
//
//	
//	          	session.beginTransaction();
//	        	session.save(r);
//	        	session.getTransaction().commit();        	
//	        	int classroom_id = -1;        	
//	        	if(r.rtClassroom != null) classroom_id = r.rtClassroom.id;
//	    		util.Util.modifyDeviceStatus(session, r.rtId, user_id, r.rtDeviceStatus, classroom_id);	
//	
//	        }
//	        
//	        System.out.println("import END---------------");
	        
			session.close();
			workbook.close();
			stream.close();
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
//		this.status = "1";		
		return SUCCESS;
	}

	public int getExecute_selectTeachBuilding() {
		return execute_selectTeachBuilding;
	}

	public void setExecute_selectTeachBuilding(int execute_selectTeachBuilding) {
		this.execute_selectTeachBuilding = execute_selectTeachBuilding;
	}

	public Map<String, ArrayList<Repertory>> getExecute_classroomDeviceMap() {
		return execute_classroomDeviceMap;
	}

	public void setExecute_classroomDeviceMap(Map<String, ArrayList<Repertory>> execute_classroomDeviceMap) {
		this.execute_classroomDeviceMap = execute_classroomDeviceMap;
	}

//	public List<Classroom> getExecute_classroomList() {
//		return execute_classroomList;
//	}
//
//	public void setExecute_classroomList(List<Classroom> execute_classroomList) {
//		this.execute_classroomList = execute_classroomList;
//	}
	
	
	


	
	
}
