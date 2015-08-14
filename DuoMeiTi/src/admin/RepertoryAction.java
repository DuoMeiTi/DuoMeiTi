package admin;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import model.Repertory;

public class RepertoryAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rtId;
	private String rtType;
	private String rtNumber;
	
	public int getRtId() {
		return rtId;
	}

	public void setRtId(int rtId) {
		this.rtId = rtId;
	}

	public String getRtType() {
		return rtType;
	}

	public void setRtType(String rtType) {
		this.rtType = rtType;
	}

	public String getRtNumber() {
		return rtNumber;
	}

	public void setRtNumber(String rtNumber) {
		this.rtNumber = rtNumber;
	}

	public String execute(){
		return SUCCESS;
	}
	
	public String insert() throws Exception{
		Session session = null;
		
		try{
			Repertory rt = new Repertory();
			rt.setRtType(rtType);;
			rt.setRtNumber(rtNumber);
			session = model.Util.sessionFactory.openSession();
			session.beginTransaction();
			session.save(rt);
			session.getTransaction().commit();
			JOptionPane.showMessageDialog(null, "提交成功");  
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		System.out.println(rtNumber);
		System.out.println(rtType);
		
		return SUCCESS;
	}
}
