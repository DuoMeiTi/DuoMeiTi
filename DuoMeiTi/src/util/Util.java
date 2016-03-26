package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

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
	static 
	{
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
	
	
	
	
	
	
	
	
	

}
