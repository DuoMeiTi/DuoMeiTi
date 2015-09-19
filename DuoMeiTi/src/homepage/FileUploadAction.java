package homepage;




import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

import model.EgFilePathSave;
import model.User;


public class FileUploadAction extends ActionSupport{
    
    private File image; //上传的文件
    private String imageFileName; //文件名称
    private String imageContentType; //文件类型
    
    private String test; 
	private List  file_path_list;
    
    @Inject(value="struts.multipart.maxSize")
    private String maxSize="空值";


    public String execute() throws Exception 
    {
    	System.out.println("test::::" + test);
    	
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(model.EgFilePathSave.class);		
		file_path_list = q.list();
    	if(ServletActionContext.getRequest().getMethod().equalsIgnoreCase("get"))
		{
    		System.out.println("get method in file upload!!!!!!");
    		session.close();
			return ActionSupport.SUCCESS; 
		}  	
    	
    	System.out.println("file upload!!!!!!#####");
    	
    	System.out.println(imageFileName);

        if (image != null) 
        {
            File savefile = new File(new File(util.Util.RootPath + util.Util.FileUploadRelativePath), imageFileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            
            System.out.println("saved****::" + savefile.getAbsolutePath());
            FileUtils.copyFile(image, savefile);
            
            EgFilePathSave file_path = new EgFilePathSave();
            
            file_path.setFilePath(util.Util.FileUploadRelativePath + imageFileName);
            session.beginTransaction();
            session.save(file_path);
            session.getTransaction().commit();

        
        }
        
        session.close();
        return "success";
    }


	public File getImage() {
		return image;
	}


	public void setImage(File image) {
		this.image = image;
	}


	public String getImageFileName() {
		return imageFileName;
	}


	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}


	public String getImageContentType() {
		return imageContentType;
	}


	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}


	public String getTest() {
		return test;
	}


	public void setTest(String test) {
		this.test = test;
	}


	public List getFile_path_list() {
		return file_path_list;
	}


	public void setFile_path_list(List file_path_list) {
		this.file_path_list = file_path_list;
	}


	public String getMaxSize() {
		return maxSize;
	}


	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}



    
    
    
    
    
    
    













}
