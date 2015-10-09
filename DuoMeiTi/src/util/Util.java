package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
	public static final String ClassroomFilePath = 
			FileUploadPath + "ClassroomFile/";//相对于Rootpath

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
	throws Exception
	{
      HttpServletRequest request = ServletActionContext.getRequest();        
      HttpServletResponse response = ServletActionContext.getResponse();

		WrapperResponse wrapperResponse = new WrapperResponse(response);
		request.getRequestDispatcher(jsppath).include(request, wrapperResponse);
		return wrapperResponse.getContent();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
