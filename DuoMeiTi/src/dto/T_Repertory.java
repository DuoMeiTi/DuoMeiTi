package dto;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import model.Repertory;

public class T_Repertory extends Repertory 
{


	public String rtProdDateString;
	public String rtApprDateString;
	
	// 这个字段只有两个值： 主要设备，耗材设备
	public String rtDevice;
//	public T_Repertory(){}
	
	public T_Repertory(Repertory r)
	{	
		try 
		{
			Field[] fs = Repertory.class.getDeclaredFields();//java的反射机制通过类中的成员名字得到类中的成员变量

			for(int i = 0; i < fs.length; ++ i)
			{
				fs[i].set(this, fs[i].get(r));		
			}
			if(rtProdDate == null) rtProdDateString ="";
			else rtProdDateString = util.Util.formatTimestampToOnlyDate(rtProdDate);
			
			if(rtApprDate == null) rtApprDateString = "";
			else rtApprDateString = util.Util.formatTimestampToOnlyDate(rtApprDate);

		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		
		rtDevice = util.Util.judgeDeviceType(r.getRtType());
		

		
				
	}

	public String getRtProdDateString() {
		return rtProdDateString;
	}

	public void setRtProdDateString(String rtProdDateString) {
		this.rtProdDateString = rtProdDateString;
	}

	public String getRtApprDateString() {
		return rtApprDateString;
	}

	public void setRtApprDateString(String rtApprDateString) {
		this.rtApprDateString = rtApprDateString;
	}

	public String getRtDevice() {
		return rtDevice;
	}

	public void setRtDevice(String rtDevice) {
		this.rtDevice = rtDevice;
	}
	
	
	

	
}
