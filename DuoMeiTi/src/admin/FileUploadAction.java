package admin;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import model.EgFilePathSave;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

//class WrapperResponse extends HttpServletResponseWrapper {
//   private MyPrintWriter tmpWriter;
//   private ByteArrayOutputStream output;
//   
// public WrapperResponse(HttpServletResponse httpServletResponse) {
//      super(httpServletResponse);
//      output = new ByteArrayOutputStream();
//      tmpWriter = new MyPrintWriter(output);
//   }
//    public void finalize() throws Throwable {
//      super.finalize();
//      output.close();
//      tmpWriter.close();
//   }
//   public String getContent() {
//      try {
//         tmpWriter.flush();   //刷新该流的缓冲，详看java.io.Writer.flush()
//         String s = tmpWriter.getByteArrayOutputStream().toString("UTF-8");
//         //此处可根据需要进行对输出流以及Writer的重置操作
//       //比如tmpWriter.getByteArrayOutputStream().reset()
//         return s;
//      } catch (UnsupportedEncodingException e) {
//         return "UnsupportedEncoding";
//      }
//   }
//   //覆盖getWriter()方法，使用我们自己定义的Writer
//   public PrintWriter getWriter() throws IOException {
//      return tmpWriter;
//   }
//   public void close() throws IOException {
//      tmpWriter.close();
//   }
//   //自定义PrintWriter，为的是把response流写到自己指定的输入流当中
//   //而非默认的ServletOutputStream
//   private static class MyPrintWriter extends PrintWriter {
//      ByteArrayOutputStream myOutput;   //此即为存放response输入流的对象
//      public MyPrintWriter(ByteArrayOutputStream output) {
//         super(output);
//         myOutput = output;
//      }
//      public ByteArrayOutputStream getByteArrayOutputStream() {
//         return myOutput;
//      }
//   }
//}




public class FileUploadAction extends util.FileUploadBaseAction 
{
	public List file_path_list;
//	public String inserted_file_path;
	public String classroom_file_table;
	
    public List getFile_path_list() {
		return file_path_list;
	}
	public void setFile_path_list(List file_path_list) {
		this.file_path_list = file_path_list;
	}
	public String getClassroom_file_table() {
		return classroom_file_table;
	}
	public void setClassroom_file_table(String classroom_file_table) {
		this.classroom_file_table = classroom_file_table;
	}
	public String execute() throws Exception 
    {
    	
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(model.ClassroomFilePath.class);		
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
        	util.Util.saveFile(file, fileFileName, util.Util.RootPath + util.Util.ClassroomFilePath);
        	model.ClassroomFilePath file_path= new model.ClassroomFilePath();
        	
        	String inserted_file_path = util.Util.ClassroomFilePath + fileFileName;
            file_path.setFilePath(inserted_file_path);
            session.beginTransaction();
            session.save(file_path);
            session.getTransaction().commit();
        }        
        
    	List classroomFilePath = session.createCriteria(model.ClassroomFilePath.class).list();
        
//        HttpServletRequest request = ServletActionContext.getRequest();        
//        HttpServletResponse response = ServletActionContext.getResponse();
        

        file_path_list = classroomFilePath;
        

        classroom_file_table = util.Util.getJspOutput("/jsp/admin/HomepageModify/ClassroomFileTable.jsp");

        System.out.println("wrapperResponsse.toString()::" + classroom_file_table);
        System.out.println("LIST::" + classroomFilePath);
        session.close();
        return "success";
    }


}
