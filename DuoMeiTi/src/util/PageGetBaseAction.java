package util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.apache.struts2.json.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

public class PageGetBaseAction extends ActionSupport  {
	
	
	public static final String getPage = "getPage";
	
	
	private int totalPageNum;
	private int currentPageNum = 1;
	private String paginationHtml;
	
	private boolean isAjaxTransmission = false;
	
	
	
	//page_size:每一页显示的元素条数
	//total_num:元素的总数，元素的条数从0开始计数
	private static int getTotalPageNum(int page_size, int total_num)
	{
		int ans = total_num / page_size;
		if(total_num % page_size > 0) ans ++;
		return ans;
	}
	
	
	/*
	 *page_num:第几页，页数， 从第一页开始计数
	 *page_size:每一页显示的元素条数
	 *total_num:元素的总数，元素的条数从0开始计数
	 *返回一个长度为3的数组：
	 *第0个元素表示这个页的起始条目的索引，
	 *第1个元素表示这个页容纳的元素数目，
	 *第2个元素表示总页数 
	 */
	private static int[] getPageRange(int page_num, int page_size, int total_num)
	{
		int[] res = new int[3];
		
		if(total_num < 1)
		{
			res[0] = 0;
			res[1] = 0;
			return res; 
		}
		int tot_page = getTotalPageNum(page_size, total_num);
		res[2] = tot_page;
		res[0] = (page_num - 1) * page_size;
		if(page_num < tot_page)
		{			
			res[1] = page_size;
		}
		else 
		{
			res[1] = total_num % page_size;
			if(res[1] == 0) res[1] = page_size;
		}
		return res;
	}
	
	
	// 根据l 所表示整体List返回页大小为page_size并且页码为this.currentPageNum 的List
	public List makeCurrentPageList(List l, int page_size)
	{
		this.totalPageNum = getTotalPageNum(page_size, l.size());
		int[] res = getPageRange(currentPageNum, page_size, l.size());
		if(this.getIsAjaxTransmission())
		{
			this.paginationHtml = util.Util.getJspOutput("/jsp/base/widgets/paginationTable.jsp");
		}
		else 
		{
			this.paginationHtml = null;
		}

		
		List ans = new ArrayList<Object>() ;
		for(int i = res[0]; i < res[0] + res[1]; ++ i)
		{
			ans.add(l.get(i));
		}
		return ans;
	}
	
	// 根据数据库的Criteria q 所表示整体数据量返回页大小为page_size的List
	public List makeCurrentPageList(Criteria q, int page_size)
	{		
		q.setProjection(Projections.rowCount());
		int tot_row = ((Long)q.uniqueResult()).intValue();
		System.out.println("tot_row "+tot_row);
		q.setProjection(null);
		q.setResultTransformer(Criteria.ROOT_ENTITY);		
		 
		int[] res = getPageRange(currentPageNum, page_size, tot_row);
		this.totalPageNum = res[2];
		if(this.getIsAjaxTransmission())
		{
			this.paginationHtml = util.Util.getJspOutput("/jsp/base/widgets/paginationTable.jsp");
		}
		else 
		{
			this.paginationHtml = null;
		}
		System.out.println("makeCurrentPageList "+this.currentPageNum+"  "+this.totalPageNum+" "+this.isAjaxTransmission);
		q.setFirstResult(res[0]);
		q.setMaxResults(res[1]);
		return q.list();
	}

	
	
	
	
	

	public String getPaginationHtml() {
		return paginationHtml;
	}



	public void setPaginationHtml(String paginationHtml) {
		this.paginationHtml = paginationHtml;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean getIsAjaxTransmission() {
		return isAjaxTransmission;
	}

	public void setIsAjaxTransmission(boolean isAjaxTransmission) {
		this.isAjaxTransmission = isAjaxTransmission;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}
	
	
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	public int getCurrentPageNum() {
		return currentPageNum;
	}
	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}


}
