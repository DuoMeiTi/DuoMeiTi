package admin;

import java.io.File;
import java.awt.print.Printable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.transaction.Transaction;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import model.EgFilePathSave;
import model.ResourceFilePath;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;





public class ResourceFileUploadAction extends util.FileUploadBaseAction 
{
	public List file_path_list;
//	public String inserted_file_path;
	public String resource_file_table;
	public List<ResourceFilePath> filePath_list;
	
    public List<ResourceFilePath> getFilePath_list() {
		return filePath_list;
	}
	public void setFilePath_list(List<ResourceFilePath> filePath_list) {
		this.filePath_list = filePath_list;
	}
	public List getFile_path_list() {
		return file_path_list;
	}
	public void setFile_path_list(List file_path_list) {
		this.file_path_list = file_path_list;
	}
	public String getClassroom_file_table() {
		return resource_file_table;
	}
	public void setClassroom_file_table(String classroom_file_table) {
		this.resource_file_table = classroom_file_table;
	}
	public String execute() throws Exception 
    {
    	
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(model.ResourceFilePath.class);		
		file_path_list = q.list();
//    	if(ServletActionContext.getRequest().getMethod().equalsIgnoreCase("get"))
//		{
//    		session.close();
//			return ; 
//		}  	
        session.close();
        
        System.out.println("***********************");
//        String page1 = "We are in ${data1}";
//        ServletActionContext.getRequest().setAttribute("data1", "sbsbBSBSB");
               
//        HttpServletRequest request = ServletActionContext.getRequest();        
//        HttpServletResponse response = ServletActionContext.getResponse();
//        
//        WrapperResponse wrapperResponse = new WrapperResponse(response);
//        request.getRequestDispatcher("/jsp/admin/HomepageModify/FileUpload.jsp").include(request, wrapperResponse);
//        request.getRequestDispatcher("/jsp/admin/base.jsp").include(request, wrapperResponse);
        
//        System.out.println("wrapperResponsse.toString()::" + wrapperResponse.getContent());
        
//        wrapperResponse.getContent();s
        return "success";
    }    
    public String insert() throws Exception
    {
    	Session session = model.Util.sessionFactory.openSession();
    	if (file != null) 
        {
        	util.Util.saveFile(file, fileFileName, util.Util.RootPath + util.Util.ResourceFilePath);
        	model.ResourceFilePath file_path= new model.ResourceFilePath();
        	
        	String inserted_file_path = util.Util.ResourceFilePath + fileFileName;
            file_path.setFilePath(inserted_file_path);
            session.beginTransaction();
            session.save(file_path);
            session.getTransaction().commit();
        }        
        
    	file_path_list = session.createCriteria(model.ResourceFilePath.class).list();
        
//        HttpServletRequest request = ServletActionContext.getRequest();        
//        HttpServletResponse response = ServletActionContext.getResponse();
        

//        file_path_list = classroomFilePath;
        

        resource_file_table = util.Util.getJspOutput("/jsp/admin/HomepageModify/ResourceFileTable.jsp");

//        System.out.println("wrapperResponsse.toString()::" + resource_file_table);
//        System.out.println("LIST::" + classroomFilePath);
        session.close();
        return "success";
    }
    
    public String delete() throws Exception{
    	System.out.println("hi");
    	Session session = model.Util.sessionFactory.openSession();
    	org.hibernate.Transaction trans = session.beginTransaction();
    	Criteria q = session.createCriteria(ResourceFilePath.class);
    	q.add(Restrictions.eq("filePath", file));
    	System.out.println("2");
    	System.out.println(file);
    	System.out.println(q.list().get(0));
    	session.delete(q.list().get(0));
    	session.getTransaction().commit();
    	session.close();
    	trans.commit();
    	
    	
    	return ActionSupport.SUCCESS;
    }


}
