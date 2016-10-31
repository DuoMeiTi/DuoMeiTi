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
import model.StudentProfile;

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
	
	private static Classroom obtainClassoom(Session session, String teachBuildingName, Cell classroomCell)
	{
		if(classroomCell == null) return null;
		String classroomNum = classroomCell.toString();
		// 如果为空则跳过
		if(classroomNum.isEmpty()) return null;


		if(classroomNum.contains("."))
		{
			classroomNum = classroomNum.substring(0, classroomNum.indexOf('.'));
		}

		Criteria classroomCriteria =  util.Util.getCriteriaWithOneEqualRestriction(
				session, model.Classroom.class, "teachbuilding.build_name", teachBuildingName);
		classroomCriteria.add(Restrictions.like("classroom_num", classroomNum + "%"));
		
		List<Classroom> classrooms = classroomCriteria.list();
		//有教室号前缀一致，跳过不予理睬
		if(classrooms.size() != 1)
		{
			return null;
		}		
		Classroom classroom = classrooms.get(0);
		return classroom;
	}
	// 返回值表示对应的当前的 教室负责人
	private static StudentProfile saveClassroomPrincipal(
			Session session, Classroom classroom, String principalFullName, StudentProfile previousPrincipal)
	{
		StudentProfile principal = null;
		if(principalFullName.isEmpty())
		{
			principal = previousPrincipal;
		}
		else 
		{
			List<StudentProfile> principals = util.Util.getListWithOneEqualRestriction(
					session, model.StudentProfile.class, "user.fullName", principalFullName);
			if(principals.size() == 1)
			{	
				principal = principals.get(0);
			}
			
			else if(principals.size() == 0)
			{
				System.out.println("无此学生");
			}
			else 
			{
				System.out.println("学生有重名");
			}
		}
		classroom.principal = principal;
		try
		{
			session.beginTransaction();
			session.update(classroom);
			session.getTransaction().commit();
		} 
		catch(Exception e)
		{
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
		
		return principal;
	}
	
	// 表示是否存储成功
	private static boolean saveClassroomDevice(Session session, Classroom classroom, String deviceName, String deviceInfo)
	{
		int user_id = (int) ActionContext.getContext().getSession().get("user_id");
		deviceName = deviceName.trim();
		deviceInfo = deviceInfo.trim();
		
		//表示此设备类型（主要设备还是耗材设备）未知
		if(util.Util.judgeDeviceType(deviceName) == null)
		{
			System.out.println("==="+deviceName + "----");
			return false;
		}
		
		if(deviceInfo.isEmpty())
		{
			return false;
		}
		
								
		Criteria deviceCriteria = util.Util.getCriteriaWithOneEqualRestriction(
				session, model.Repertory.class, "rtClassroom.id", classroom.id);
		util.Util.addOneEqualRestriction(deviceCriteria, "rtType", deviceName);

		
		if(!deviceCriteria.list().isEmpty())
		{
			System.out.println(classroom.classroom_num + ", " + deviceName + " 已经添加过");
			return false;
		}
		
		Repertory device = new Repertory();
		device.rtType = deviceName;
		
		//先将设备的所有描述信息存入设备资产编号中，之后再做处理
		device.rtNumber =  deviceInfo;		
		session.beginTransaction();
		session.save(device);
		session.getTransaction().commit();
		
		util.Util.modifyDeviceStatus(session, device.rtId, user_id, util.Util.DeviceClassroomStatus, classroom.id);
		return true;
//		System.out.print(cell.toString() + " ");

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
		try{ // try begin

			InputStream stream = new FileInputStream (this.file);
			
			String fileType = fileFileName.substring(fileFileName.lastIndexOf(".") + 1, fileFileName.length());			
			Workbook workbook;
			//目前只支持xls格式
			if (fileType.equals("xls")) 
			{
				workbook = new HSSFWorkbook(stream);
	        }    
	        else 
	        {
//	            this.status = "1您输入的excel格式不正确";
	            stream.close();
	            return SUCCESS;
	        }    
			
			
			Session session = model.Util.sessionFactory.openSession();			
			for(int sheetId = 0; sheetId < workbook.getNumberOfSheets(); ++ sheetId)
			{
				Sheet sheet = workbook.getSheetAt(sheetId);
				String teachBuildingName = workbook.getSheetName(sheetId);				
				System.out.println(teachBuildingName);
				Row firstRow = sheet.getRow(0);
				
				
				StudentProfile previousPrincipal = null;
				for(int rowId = 1; ; rowId++)
				{	
					Row row = null;					
					//探测接下来10行是否有数据，防止中间有空行，后边的数据没有检测到
					for(int cot = 0; cot < 10; cot ++)
					{
						row = sheet.getRow(rowId);
						if(row != null) break;
						else rowId ++;
					}
					// 表示已经没有数据了
					if(row == null) break;					
					
					
//					 第0列是教室号					
					Classroom classroom = obtainClassoom(session,  teachBuildingName, row.getCell(0));					
					// 解析classroom 不成功, 跳过
					if(classroom == null) continue;
					
					
					//遍历设备或者负责人
					for(int colId = 1; colId < 20 ; colId++)
					{
						if(firstRow.getCell(colId) == null) continue;
						
						String firstRowCellValue = firstRow.getCell(colId).toString();
						Cell cell = row.getCell(colId);
						String cellValue="";
						if(cell != null) cellValue = cell.toString();
						
						if(firstRowCellValue.equals("负责人"))
						{
							previousPrincipal = saveClassroomPrincipal(session, classroom, cellValue, previousPrincipal);
						}

						else // 表示这是个设备 
						{
							saveClassroomDevice(session, classroom, firstRowCellValue, cellValue );
						}
						
					}
					
					System.out.println("");
				}

			}
			
			
			
	        
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
