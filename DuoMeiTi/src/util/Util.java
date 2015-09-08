package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

public class Util 
{
	
	public static final String RootPath;
	
	static {
		ServletContext application = ServletActionContext.getServletContext();
		RootPath = application.getRealPath("");
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
