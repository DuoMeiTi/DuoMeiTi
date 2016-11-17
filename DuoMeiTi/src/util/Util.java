package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import model.Classroom;
import model.DutyPiece;
import model.DutySchedule;
import model.RepairRecord;
import model.Repertory;
import model.StudentProfile;
import model.User;

import org.hibernate.Criteria;

public class Util
{
	
	public static final String RootPath;
	
	public static final String FileUploadPath = "/FileUpload/"; //相对于Rootpath
	
	public static final String ProfilePhotoPath = 
			FileUploadPath + "ProfilePhoto/";//相对于Rootpath	
	
	public static final String ResourceFilePath = 
			FileUploadPath + "ResourceFile/";//相对于Rootpath
	
	public static final String ClassroomInfoFilePath = 
			FileUploadPath + "ClassroomInfoFile/";//相对于Rootpath
	
	public static final String ClassroomSchedulePath = 
			FileUploadPath + "ClassroomScheduleFile/";//相对于Rootpath	

	public static final String RecordExportPath = 
			FileUploadPath + "RecordExportPath/";
	

	public static final String CheckInExcelExportPath = 
			FileUploadPath + "ExportCheckInFile/";//相对于Rootpath	
	
	public static final String ExportDeviceInfoPath = 
			FileUploadPath + "ExportDeviceInfoPath/";//相对于Rootpath
	
	public static void makeDir(String path)
	{
		File file = new File(path);
		if(!file.exists())
		{
			makeDir(file.getParent().toString());
			file.mkdir();
		}
	}
	
	
	static 
	{	
		ServletContext application = ServletActionContext.getServletContext();
		RootPath = application.getRealPath("");		
		
		makeDir(RootPath + ProfilePhotoPath);
		makeDir(RootPath + ResourceFilePath);
		makeDir(RootPath + ClassroomInfoFilePath);
		makeDir(RootPath + ClassroomSchedulePath);
		makeDir(RootPath + CheckInExcelExportPath);
		
		makeDir(RootPath + RecordExportPath);
		makeDir(RootPath + ExportDeviceInfoPath);

	}
	
	static public String fileToString(String fileName) 
	{
		fileName = RootPath + fileName;
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(fileName));			
			String line="";
			StringBuffer  buffer = new StringBuffer();
			while((line=br.readLine())!=null)
			{
				buffer.append(line);
			}
			String fileContent = buffer.toString();
			return fileContent;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return "";
	}
	
	
	/*
	 *把文件file，其文件名称为fileName， 将这个文件存到targetFilePath路径下
	 */
	static public void saveFile(File file, String fileName, String targetFilePath)	
	{
		File savefile = new File(new File(targetFilePath), fileName);
		System.out.println("root:"+targetFilePath);
        if (!savefile.getParentFile().exists())
            savefile.getParentFile().mkdirs();
        try
        {
        	FileUtils.copyFile(file, savefile);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
	/*
	 *删除file文件，filePath为完整路径名
	 */
	static public void deleteFile(String filePath)
	{
		System.out.println("deleteFile:");
		System.out.println(filePath);
		File file = new File(filePath);
		if(file.isFile() && file.exists()){
			file.delete();
			System.out.println("OK");
		}
	}
	
	static public String getFileNameFromPath(String path)
	{
		String []  list = path.split("/");
		int n = list.length;
		return list[n - 1];
	}
	
	
	static public  String getJspOutput(String jsppath, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		WrapperResponse wrapperResponse = new WrapperResponse(response);
		request.getRequestDispatcher(jsppath).include(request, wrapperResponse);
		return wrapperResponse.getContent();
	}

	
	static public  String getJspOutput(String jsppath)
//	throws Exception
	{
		WrapperResponse wrapperResponse = null;
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();        
			HttpServletResponse response = ServletActionContext.getResponse();
			
			wrapperResponse = new WrapperResponse(response);
			request.getRequestDispatcher(jsppath).include(request, wrapperResponse);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return wrapperResponse.getContent();
	}

	
	
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
	private static final SimpleDateFormat dfOnlyDate = new SimpleDateFormat("yyyy-MM-dd");
	
	static public String formatTimestamp(Timestamp s)
	{
		return df.format(s);
	}
	static public String formatTimestampToOnlyDate(Timestamp s)
	{
		return dfOnlyDate.format(s);
	}
	
	static public String formatUtilDateToOnlyDate(java.util.Date s)
	{
		return dfOnlyDate.format(s);
	}
	
	static public String formatSqlDateToOnlyDate(java.sql.Date s)
	{
		
		return dfOnlyDate.format(new java.util.Date(s.getTime()));
	}

	
	
	
	// 与值班表相关的常量定义：
	// 0-6 一共七天
	public static final List<String> dutyWeekList;
	
	// 0-4 一共五个时间段
	public static final List<String> dutyPeriodList;
	public static final List<java.time.LocalTime> dutyPeriodBeginList;
	public static final List<java.time.LocalTime> dutyPeriodEndList;
	
	//针对每个值班时间段的签到时间时段	
	public static final List<java.time.LocalTime> dutyCheckinPeriodBeginList;
	public static final List<java.time.LocalTime> dutyCheckinPeriodEndList;
	
	
	public static int getWeekFromDutyPieceTime(int time)
	{
		return time % 7;
	}
	public static int getPeriodFromDutyPieceTime(int time)
	{
		return time / 7;
	}
	
	// 获取今天是星期几
	// 返回一个0~6之间的一个整数表示含义如下：
	// 0: 星期一
	// 1: 星期二
	//...
	// 6:星期日
	public static int getDayOfWeek(java.util.Date d)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int day = c.get(Calendar.DAY_OF_WEEK);
		day -= 2;
		if(day == -1) day = 6;
		return day;
		
	}

	/**
	 * 返回一个0~34 的一个数字, 一周有7天，一天有5个值班时间段
	 */
	public static int makeDutyTime(int week, int period)
	{
		return period * 7 + week;
	}
	
	
	
	static 
	{
//		java.time.LocalTime.now()
		
		
		String[] tmpDutyWeekList = {"一", "二", "三", "四", "五", "六", "日"};
		dutyWeekList = new ArrayList<String>();
		for(int i = 0; i < tmpDutyWeekList.length; ++ i)
		{
			dutyWeekList.add(tmpDutyWeekList[i]);
		}
		
		
		String[] tmpDutyPeriodList = {"7:40~9:20", "9:50~11:30", "13:15~14:50", "15:20~17:00", "17:45~19:20"};
		dutyPeriodList = new ArrayList<String>();
		for(int i = 0; i < tmpDutyPeriodList.length; ++ i)
		{
			dutyPeriodList.add(tmpDutyPeriodList[i]);
		}
		
		dutyPeriodBeginList = new ArrayList<java.time.LocalTime>();
		dutyPeriodBeginList.add(LocalTime.of(7, 40));
		dutyPeriodBeginList.add(LocalTime.of(9, 50));
		dutyPeriodBeginList.add(LocalTime.of(13, 15));
		dutyPeriodBeginList.add(LocalTime.of(15, 20));
		dutyPeriodBeginList.add(LocalTime.of(17, 45));		
		
		dutyPeriodEndList = new ArrayList<java.time.LocalTime>();
		dutyPeriodEndList.add(LocalTime.of(9, 20));
		dutyPeriodEndList.add(LocalTime.of(11, 30));
		dutyPeriodEndList.add(LocalTime.of(14, 50));
		dutyPeriodEndList.add(LocalTime.of(17, 00));
		dutyPeriodEndList.add(LocalTime.of(19, 20));
		
		
		
		
		dutyCheckinPeriodBeginList = new ArrayList<java.time.LocalTime>();
		dutyCheckinPeriodEndList = new ArrayList<java.time.LocalTime>();
		
		dutyCheckinPeriodBeginList.add(LocalTime.of(6, 40));
		dutyCheckinPeriodBeginList.add(LocalTime.of(8, 50));
		dutyCheckinPeriodBeginList.add(LocalTime.of(12, 15));
		dutyCheckinPeriodBeginList.add(LocalTime.of(14, 20));
		dutyCheckinPeriodBeginList.add(LocalTime.of(16, 45));
		
		dutyCheckinPeriodEndList.add(LocalTime.of(7, 40));
		dutyCheckinPeriodEndList.add(LocalTime.of(9, 50));
		dutyCheckinPeriodEndList.add(LocalTime.of(13, 15));		
		dutyCheckinPeriodEndList.add(LocalTime.of(15, 20));
		dutyCheckinPeriodEndList.add(LocalTime.of(17, 45));
		
		
		
//		File savefile = new File(RootPath + RecordExportPath);
//        if (!savefile.getParentFile().exists())
//            savefile.getParentFile().mkdirs();
//        if (!savefile.exists())
//            savefile.mkdirs();

		
	}
	
	
	
	
	
	
	
	
	
	public static String convertToMultiLine(String s, int limit)
	{
		StringBuffer ans = new StringBuffer();
		ans.append("<nobr>");
		for(int i = 0; i < s.length(); ++ i)
		{			
			ans.append(s.charAt(i));
			if( (i + 1) % limit == 0)
			{
				ans.append("</nobr>\n");
				ans.append("<nobr>");
			}
		}
		ans.append("</nobr>");
		return ans.toString();
		
	}
	
	
	
	
	
	
    		
	
// 设备类型
	
	public static final List<String> DeviceList;
	private static final String[] AllMainDevice = {
			"计算机",
			"投影仪",
			"功放",
    		"中控",
    		"幕布",
    		"音响",
    		"控制台",    		
    		
    		
    		"显示器",
    		"机柜",    		
    		"麦克",
    		"数字处理器",
    };
    private static final String[] AllCostDevice = {
    		"灯泡",
    		"翻页器",
    		"鼠标",
    		"键盘",
    		"电池",
    		"USB延长线",
    		"光驱",
    		"音频线",
    		"视频线",
    		"电源线",
    		"网卡",
    };
	
	
	public static String judgeDeviceType(String deviceName)
	{
		for(String i:AllMainDevice)
		{
			if(i.equals(deviceName)) return "主要设备";
		}		
		for(String i: AllCostDevice)
			if(i.equals(deviceName)) return "耗材设备";
		
		return null;
	}
    static 
    {
    	DeviceList = new ArrayList<String>();
    	for(String i: AllMainDevice) DeviceList.add(i);
    	for(String i: AllCostDevice) DeviceList.add(i);
    }
    
    public static List getAllTeachBuildingList()
    {
    	
    	org.hibernate.Session s = model.Util.sessionFactory.openSession();
    	
    	List L = s.createCriteria(model.TeachBuilding.class).list();
    	s.close();
    	return L;
    	
    }
    
    public final static String DeviceBackupStatus = "备用";
    public final static String DeviceClassroomStatus = "教室";
    public final static String DeviceRepairStatus = "维修";
    public final static String DeviceScrappedStatus = "报废";
    
    
    public static boolean isValidDeviceStatus(String status)
    {
    	if(status.equals(DeviceBackupStatus)) return true;
    	if(status.equals(DeviceClassroomStatus)) return true;
    	if(status.equals(DeviceRepairStatus)) return true;
    	if(status.equals(DeviceScrappedStatus)) return true;
    	
    	return false;
    }
    
    // 更改设备的状态
    public static void modifyDeviceStatus(org.hibernate.Session s, int device_id, int user_id, String newStatus, int classroom_id)
    {

    	s.beginTransaction();
    	model.Repertory device = (model.Repertory) 
    							 s.createCriteria(model.Repertory.class)
    			 				  .add(Restrictions.eq("id", device_id))
    			 				  .uniqueResult();
//    	model.Repertory device = new model.Repertory();
//    	device.rtId = device_id;
    	if(device == null)
    	{
    		System.out.println("SSSSSSSSSJJJJJJCAOCAOCAO");
    	}

    	model.DeviceStatusHistory dsh = new model.DeviceStatusHistory();
    	dsh.setStatus(newStatus);
    	dsh.setDate(new java.sql.Timestamp(System.currentTimeMillis()));
    	
    	model.Classroom classroom; 
    	if(classroom_id < 0) classroom = null;
    	else
		{
    		classroom =  new model.Classroom();
    		classroom.setId(classroom_id);
		}
    	
    	
    	device.rtDeviceStatus = newStatus;
    	device.rtClassroom = classroom;    	
    	
    	dsh.setClassroom(classroom);    	
    	dsh.setDevice(device);
    	
    	User user = new User();
    	user.setId(user_id);    	
    	dsh.setUser(user); 	
    	
    	
    	
    	s.update(device);
    	s.save(dsh);
    	s.getTransaction().commit();    	

    }
    
    private static void modifyDeviceStatus(int device_id, int user_id, String newStatus, int classroom_id)
    {
    	org.hibernate.Session s = model.Util.sessionFactory.openSession();
    	modifyDeviceStatus(s, device_id, user_id, newStatus, classroom_id);
    	s.close();    	

    }
    
    


    
    // 返回文件名称中的真实名称与 扩展名称
	static public String[] splitFileName(String s)
	{
		int lastPos = s.lastIndexOf('.');
		String[] res = new String[2];
		if(lastPos == -1)
		{			
			res[0] = s;
			res[1] = "";
			
		}
		else 
		{
			res[0] = s.substring(0, lastPos);
			res[1] = s.substring(lastPos + 1);
		}
		
		return  res;
	}
	
	
	
	//删除一个dutySchedule，保证dutySchedule和duty剩余量的一致性！
	// 在调用此函数时应该其放在一个transaction中
	public static void deleteDutySchedule(org.hibernate.Session session, int dutyScheduleId) throws Exception{		
//		session.beginTransaction();
		DutySchedule ds = (DutySchedule)session
						.createCriteria(model.DutySchedule.class)
						.add(Restrictions.eq("id", dutyScheduleId))
						.uniqueResult();
		
		if(ds != null)
		{
			DutyPiece dp = ds.dutyPiece;
			session.delete(ds);		
			dp.dutyLeft++;
			session.update(dp);
			
		}
//		session.getTransaction().commit();
	}

	// 返回值表示是否存储成功
	public static boolean saveClassroomScheduleFile(org.hibernate.Session session, 
			Classroom classroom, File scheduleFile, String scheduleFileName)
	{
		if(classroom.getClass_schedule_path() != null)
		{
			File old_class_schedule = new File(util.Util.RootPath + classroom.getClass_schedule_path());
	    	if(!old_class_schedule.delete()) // 删除旧课表
	    	{
	    		
	    	}
		}
		
		
		String newScheduleFileName = classroom.teachbuilding.build_name + "-" + classroom.classroom_num;
		
		int lastDotIndex = scheduleFileName.lastIndexOf(".");
		if(lastDotIndex != -1)
		{
			newScheduleFileName += scheduleFileName.substring(lastDotIndex);
		}
		
    	util.Util.saveFile(scheduleFile, newScheduleFileName, util.Util.RootPath + util.Util.ClassroomSchedulePath);
    	String inserted_file_path = util.Util.ClassroomSchedulePath + newScheduleFileName;
    	classroom.class_schedule_path = inserted_file_path;
    	
        session.beginTransaction();
        session.update(classroom);
        session.getTransaction().commit();

        return true;
	}
	
	/** 获取一个教学楼内的所有教室Criteria*/
	public static Criteria obtainClassroomListCriteria(org.hibernate.Session s, int selectTeachBuildingId)
	{
		return s.createCriteria(Classroom.class)
				.add(Restrictions.eq("teachbuilding.build_id", selectTeachBuildingId))
				.addOrder(Order.asc("classroom_num"));
	}
	
	/** 获取一个教学楼内的所有教室列表*/
	@SuppressWarnings("unchecked")
	public static List<Classroom> obtainClassroomList(org.hibernate.Session s, int selectTeachBuildingId)
	{
		return (List<Classroom>)obtainClassroomListCriteria(s, selectTeachBuildingId).list();
	}


	/** 获取学生账户，根据学生在数据库中存储的ID. */
	public static StudentProfile getStudentByDatabaseId(org.hibernate.Session s, int id) {
		return (StudentProfile) 
				s.createCriteria(model.StudentProfile.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	
	
	/** 获取学生账户，根据学生的学号. */
	public static StudentProfile getStudentByStudentId(org.hibernate.Session s, String studentId) {
		return (StudentProfile) 
				s.createCriteria(model.StudentProfile.class)
				.add(Restrictions.eq("studentId", studentId))
				.uniqueResult();
	}

	/** 获取教室，根据教室的ID. */
	public static Classroom getClassroomById(org.hibernate.Session s, int id) {
		return (Classroom) 
				s.createCriteria(model.Classroom.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}
	
	/** 获取教室，根据教室所在的教学楼ID和其教室号 */
	public static Classroom getClassroom(org.hibernate.Session s, int teachBuildingId, String classroomNum) {
		return (Classroom) 
				
				s.createCriteria(Classroom.class)
				.add(Restrictions.eq("teachbuilding.build_id", teachBuildingId))
				.add(Restrictions.eq("classroom_num", classroomNum))
				.uniqueResult();
	}

	/** 获取值班总表每一列的颜色，列编号从0开始，第0列为第一周第一个教学楼所在列 */
	public static String getDutySummaryTableColumnColor(int column)
	{
		String color;
		if(column % 4 == 0)
			color = "yellow";
		else if(column % 4 == 1)
			color = "#0080FF";
		else if(column % 4 == 2)
			color = "#00A600";
		else
			color = "#66CDAA";
		
		return color;
	}
	

	
	/**添加一个等于的限制条件**/
	public static Criteria addOneEqualRestriction(Criteria c, String propertyName, Object propertyValue)
	{
		if(propertyName.contains("."))
		{
			String [] splittedPropertyNameArray = propertyName.split("\\.");
			String cntAliasPropertyName ="";
			for(int i = 0; i < splittedPropertyNameArray.length - 1; ++ i )
			{
				String splittedPropertyName = splittedPropertyNameArray[i];
				
				if(!cntAliasPropertyName.isEmpty()) cntAliasPropertyName += ".";
				
				cntAliasPropertyName +=  splittedPropertyName;
				
				c.createAlias(cntAliasPropertyName, splittedPropertyName);
			}
		}
		return c.add(Restrictions.eq(propertyName, propertyValue));
	}
	
	
//	public static Criteria addMultiEqualRestrictions(Criteria c, java.util.Map<String, Object> propertyList)
//	{	
//		for(java.util.Map.Entry<String, Object> entry : propertyList.entrySet())
//		{
//			c = addOneEqualRestriction(c, entry.getKey(), entry.getValue());
//		}
//		return c;
//	}
//	
	
	
	public static <T> Criteria getCriteriaWithOneEqualRestriction(
			org.hibernate.Session s, Class<T> classInfo, String propertyName, Object propertyValue)
	{
		Criteria c = s.createCriteria(classInfo);
		return addOneEqualRestriction(c, propertyName, propertyValue);
	}
	public static <T> List<T> getListWithOneEqualRestriction(
			org.hibernate.Session s, Class<T> classInfo, String propertyName, Object propertyValue) {
		
		
		return getCriteriaWithOneEqualRestriction(s, classInfo, propertyName, propertyValue).list();
//		Criteria c = s.createCriteria(classInfo);
//		
//		return addOneEqualRestriction(c, propertyName, propertyValue).list();
//		if(propertyName.contains("."))
//		{
//			String [] splittedPropertyNameArray = propertyName.split("\\.");
//			for(int i = 0; i < splittedPropertyNameArray.length - 1; ++ i )
//			{				
//				
//				
//				String splittedPropertyName = splittedPropertyNameArray[i];
//				c.createAlias(splittedPropertyName, splittedPropertyName);
//			}
//		}
//		
//		return (List<T>)c.add(Restrictions.eq(propertyName, propertyValue)).list();
	}
	
	public static <T> T getUniqueResultWithOneEqualRestriction(
			org.hibernate.Session s, Class<T> classInfo, String propertyName, Object propertyValue) {
		return (T)getListWithOneEqualRestriction(s, classInfo, propertyName, propertyValue).get(0);
		
//		return (T)s.createCriteria(classInfo).add(Restrictions.eq(propertyName, propertyValue)).uniqueResult();
	}

	public static boolean isExistWithOneEqualRestriction(
			org.hibernate.Session s, Class classInfo, String propertyName, Object propertyValue) {
		return !getListWithOneEqualRestriction(s, classInfo, propertyName, propertyValue).isEmpty();
	}
	
	
	/** 把src中对应的fieldListString 中的字段复制到dst中*/
	public static void copyWithSpecificFields(Object src, Object dst, List<String> fieldListString) 
	{	
		for(String fieldString : fieldListString)
		{
			try 
			{
				
				int dotPos = fieldString.indexOf('.');
				if(dotPos == -1)
				{
					Field field = dst.getClass().getField(fieldString);
					field.setAccessible(true);
					field.set(dst, field.get(src));
				}
				else
				{
					List<String> nextField = new ArrayList<String>();
					nextField.add(fieldString.substring(dotPos + 1));
					
					Field field = dst.getClass().getField(fieldString.substring(0, dotPos));
					field.setAccessible(true);
					copyWithSpecificFields(field.get(src), field.get(dst), nextField);

				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}		
	}
	
	/** 把src中对应的fieldListString 中的字段复制到dst中*/
	public static void copyWithSpecificFields(Object src, Object dst, String[] fieldStringArray)
	{
		List<String> list = new ArrayList<String>();
		
		Collections.addAll(list, fieldStringArray);
		
		copyWithSpecificFields(src, dst,list );
		
	}
	
	/** 
	 * 设置user的用户头像为file所表示的图片，其文件名为{@code fileFileName}
	 * 此函数不会对数据库操作， 只会对文件进行复制保存，并且设置user中的profilePhotoPath字段
	 *  
	 * */
	public static void setUserProfilePhoto(User user, File file, String fileFileName)
	{
		fileFileName = user.username + "_" + fileFileName;
		util.Util.deleteFile(util.Util.RootPath + user.profilePhotoPath);
		util.Util.saveFile(file,  fileFileName, util.Util.RootPath + util.Util.ProfilePhotoPath);
		user.profilePhotoPath = util.Util.ProfilePhotoPath +  fileFileName;

	}
	
	
	/**
	 * 检查studentProfile信息是否有错误
	 * 若有错误这返回错误列表，否则返回空列表 
	 */	
	public static List<String> validate(org.hibernate.Session s, User user)
	{
		List<String> ans = new ArrayList<String>();
		
		if(user.username.isEmpty() || user.password.isEmpty() || user.fullName.isEmpty()  )
		{
			ans.add( "含有未填项");
		}
		if(user.username.contains(" "))
		{
			ans.add( "用户名中含有空格");
		}
		if(user.fullName.contains(" "))
		{
			ans.add("真实姓名中含有空格");
		}
		
		
		//判断用户名是否重复
		if(util.Util.isExistWithOneEqualRestriction(s, User.class, "username", user.username))
		{
			ans.add("用户名有重复");
		}
		return ans;


	}
	
	/**
	 * 检查studentProfile信息是否有错误
	 * 若有错误这返回错误列表，否则返回空列表 
	 */
	public static List<String> validate(org.hibernate.Session s, StudentProfile studentProfile)
	{
		List<String> ans = new ArrayList<String>();
		ans.addAll(validate(s,studentProfile.user));
		
		if(studentProfile.studentId.isEmpty())
		{
			ans.add("学号为空");
		}

		if(studentProfile.studentId.contains(" "))
		{
			ans.add("学号中含有空格");
		}		
		//判断学号是否重复		
		if(util.Util.isExistWithOneEqualRestriction(s, StudentProfile.class, "studentId", studentProfile.studentId))
		{
			ans.add("学号有重复");
		}
		return ans;

	}
	
	
	
	public static Criteria getRegisterPassedStudentProfileCriteria(org.hibernate.Session s)
	{
		return s.createCriteria(StudentProfile.class).add(Restrictions.eq("isPassed", model.StudentProfile.Passed));
	}
	
	// 根据key和value查询满足条件的学生列表，排除了注册未通过的学生
	public static List<StudentProfile> getRegisterPassedStudentProfileList(org.hibernate.Session s, String queryKey, Object queryValue)
	{
		Criteria c = getRegisterPassedStudentProfileCriteria( s);
		return util.Util.addOneEqualRestriction(c, queryKey, queryValue).list();
	}

	
	
	/**根据repairman,   classroom,   device,   detail 设置RepairRecord 的相关信息*/
	
	public static void setRepairRecord(RepairRecord repairRecord, 
			User repairman, Classroom classroom, Repertory device, String detail)
	{
		repairRecord.setDeviceType(device.getRtType());
		repairRecord.setDeviceNumber(device.getRtNumber());
		repairRecord.setDeviceVersion(device.getRtVersion());
		repairRecord.setDeviceFactorynum(device.getRtFactorynum());
		repairRecord.setDeviceProdDate(device.getRtProdDate());
		repairRecord.setDeviceApprDate(device.getRtApprDate());

		repairRecord.setRepairdate(new Timestamp(new java.util.Date().getTime()));
		repairRecord.setRepairdetail(detail);

		repairRecord.setRepairmanFullName(repairman.getFullName());
		repairRecord.setRepairmanPhoneNumber(repairman.getPhoneNumber());

		repairRecord.setClassroomName(classroom.getClassroom_num());
		repairRecord.setTeachingBuildingName(classroom.getTeachbuilding().getBuild_name());

	}
	
	/**根据repairman,   classroom,   device,   detail 设置RepairRecord 的相关信息*/	
	public static void setRepairRecord(RepairRecord repairRecord, 
			List<StudentProfile> studentList, Classroom classroom, Repertory device, String detail)
	{
		repairRecord.setDeviceType(device.getRtType());
		repairRecord.setDeviceNumber(device.getRtNumber());
		repairRecord.setDeviceVersion(device.getRtVersion());
		repairRecord.setDeviceFactorynum(device.getRtFactorynum());
		repairRecord.setDeviceProdDate(device.getRtProdDate());
		repairRecord.setDeviceApprDate(device.getRtApprDate());

		repairRecord.setRepairdate(new Timestamp(new java.util.Date().getTime()));
		repairRecord.setRepairdetail(detail);
		
		repairRecord.repairmanFullName = "";
		repairRecord.repairmanPhoneNumber = "";
		repairRecord.repairmanStudentId = "";
						
		for(int i = 0; i < studentList.size(); ++ i)
		{
			if(i > 0)
			{
				// 使用逗号分割：
				repairRecord.repairmanFullName += ",";
				repairRecord.repairmanPhoneNumber += ",";
				repairRecord.repairmanStudentId += ",";
			}
			repairRecord.repairmanFullName  += studentList.get(i).getUser().getFullName();
			repairRecord.repairmanPhoneNumber += studentList.get(i).getUser().getPhoneNumber();
			repairRecord.repairmanStudentId += studentList.get(i).getStudentId();			
		}

		repairRecord.setClassroomName(classroom.getClassroom_num());
		repairRecord.setTeachingBuildingName(classroom.getTeachbuilding().getBuild_name());

	}

			
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
