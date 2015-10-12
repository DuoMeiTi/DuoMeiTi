package util;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.apache.struts2.json.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

public class PageGetBaseAction extends ActionSupport  {
	
	
	public static final String getPage = "getPage";
	
	
	public int totalPageNum;
	public int currentPageNum;
	public String paginationHtml;
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	//page_size:每一页显示的元素条数
	//total_num:元素的总数，元素的条数从0开始计数
	public static int getTotalPageNum(int page_size, int total_num)
	{
		int ans = total_num / page_size;
		if(total_num % page_size > 0) ans ++;
		return ans;
	}
	
	//page_num:第几页，页数， 从第一页开始计数
	//page_size:每一页显示的元素条数
	//total_num:元素的总数，元素的条数从0开始计数
	// 返回一个长度为2的数组：第一个元素表示这个页的起始条目的索引， 第二个元素表示这个页应该容纳的元素数目
	public static int[] getPageRange(int page_num, int page_size, int total_num)
	{
		int[] res = new int[2];
		
		if(total_num < 1)
		{
			res[0] = 0;
			res[1] = 0;
			return res; 
		}
		int tot_page = getTotalPageNum(page_size, total_num);		
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
	
	// newCurrentPageNum  新的页面值！！！
	public List makeAllOk(int newCurrentPageNum, Criteria q, int page_size)
	{		
		q.setProjection(Projections.rowCount());
		int tot_row = ((Long)q.uniqueResult()).intValue();		
		q.setProjection(null);
		q.setResultTransformer(Criteria.ROOT_ENTITY);
		
		this.totalPageNum = getTotalPageNum(page_size, tot_row);
		int[] res = getPageRange(newCurrentPageNum, page_size, tot_row);
		
		if(this.currentPageNum != 0)
		{
			this.currentPageNum = newCurrentPageNum;
			this.paginationHtml = util.Util.getJspOutput("/jsp/base/widgets/pagination.jsp");
		}
		else 
		{
			this.currentPageNum = newCurrentPageNum;
			this.paginationHtml = null;
		}
		
		
		
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
