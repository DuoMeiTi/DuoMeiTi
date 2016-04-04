package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
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

	static 
	{
		
		ServletContext application = ServletActionContext.getServletContext();
		RootPath = application.getRealPath("");
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
//		System.out.println("LIST");
		
		
		int n = list.length;
//		System.out.println(list[n - 1]);
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
	static public String formatTimestamp(Timestamp s)
	{
		return df.format(s);
	}
	
	
	
	
	
	// 与值班表相关的常量定义：
	public static final List<String> dutyWeekList;
	public static final List<String> dutyPeriodList;
	public static final List<java.time.LocalTime> dutyPeriodBeginList;
	public static final List<java.time.LocalTime> dutyPeriodEndList;
	
	
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
    		"中控",
    		"功放",
    		"投影机",
    		"计算机主机",
    		"显示器",
    		"机柜",
    		"幕布",
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
		return "耗材设备";
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
    
    
    
    
	
	
	
	
	

}
