package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

public class Util 
{
	
	public static final String RootPath;
	public static final String FileUploadPath;
	
	public static final String ProfilePhotoPath;
	
	public static final int AdminRole = 0;
	public static final int StudentRole = 1;
	public static final int teacherRole = 2;
	
	static {
		ServletContext application = ServletActionContext.getServletContext();
		RootPath = application.getRealPath("");
		
		FileUploadPath = RootPath +"/FileUpload";
		ProfilePhotoPath = FileUploadPath +"/ProfilePhoto";
		
//		System.out.println(AppPath);
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

}
