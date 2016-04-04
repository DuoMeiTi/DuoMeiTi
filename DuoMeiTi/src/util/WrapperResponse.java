package util;

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

//import model.EgFilePathSave;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class WrapperResponse  extends HttpServletResponseWrapper {
   private MyPrintWriter tmpWriter;
   private ByteArrayOutputStream output;
   
 public WrapperResponse (HttpServletResponse httpServletResponse) {
      super(httpServletResponse);
      output = new ByteArrayOutputStream();
      tmpWriter = new MyPrintWriter(output);
   }
    public void finalize() throws Throwable {
      super.finalize();
      output.close();
      tmpWriter.close();
   }
   public String getContent() {
      try {
         tmpWriter.flush();   //刷新该流的缓冲，详看java.io.Writer.flush()
         String s = tmpWriter.getByteArrayOutputStream().toString("UTF-8");
         //此处可根据需要进行对输出流以及Writer的重置操作
       //比如tmpWriter.getByteArrayOutputStream().reset()
         return s;
      } catch (UnsupportedEncodingException e) {
         return "UnsupportedEncoding";
      }
   }
   //覆盖getWriter()方法，使用我们自己定义的Writer
   public PrintWriter getWriter() throws IOException {
      return tmpWriter;
   }
   public void close() throws IOException {
      tmpWriter.close();
   }
   //自定义PrintWriter，为的是把response流写到自己指定的输入流当中
   //而非默认的ServletOutputStream
   private static class MyPrintWriter extends PrintWriter {
      ByteArrayOutputStream myOutput;   //此即为存放response输入流的对象
      public MyPrintWriter(ByteArrayOutputStream output) {
         super(output);
         myOutput = output;
      }
      public ByteArrayOutputStream getByteArrayOutputStream() {
         return myOutput;
      }
   }
}


