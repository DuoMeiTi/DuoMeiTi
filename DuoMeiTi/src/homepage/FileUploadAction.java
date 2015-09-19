package homepage;




import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;


public class FileUploadAction extends ActionSupport{
    
    private File image; //上传的文件
    private String imageFileName; //文件名称
    private String imageContentType; //文件类型
    
    @Inject(value="struts.multipart.maxSize")
    private String maxSize="3b";


    public String execute() throws Exception {
    	
    	
    	System.out.println("file upload!!!!!!");
    	System.out.println(maxSize);
//        String realpath = ServletActionContext.getServletContext().getRealPath("/images");
//    	String realpath = "D:/tmp";
    	String realpath = util.Util.RootPath;
    	
    	String path=new File(getClass().getClassLoader().getResource("").toURI()).getPath();
    	
    	System.out.println(path);
    
//    	ActionContext.getContext().getApplication().get(key)
    	
    	System.out.println(new File("").getCanonicalPath());
        System.out.println("realpath: "+realpath);
        if (image != null) {
            File savefile = new File(new File(realpath), imageFileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(image, savefile);
//            ActionContext.getContext().put("message", "文件上传成功");
        }
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

    
}
