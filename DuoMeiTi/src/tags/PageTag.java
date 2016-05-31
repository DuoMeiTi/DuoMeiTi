package tags;

//import java.io.IOException;
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.JspWriter;
//import javax.servlet.jsp.tagext.SimpleTagSupport;
//import util.PropertyUtil;
/**
 * @author beckham 分页标签 Oct 22, 2009 9:20:49 PM
 */
//public class PageTag extends SimpleTagSupport {
//	private String path; // 请求路径
//	private String param; // 传递参数
//	private int currPage; // 当前页
//	private int totalPage;// 总页数
//	private int totalSize;
//	private int pageCount;
//	private int pageSize;
//	public int getTotalSize() {
//		return totalSize;
//	}
//	public void setTotalSize(int totalSize) {
//		this.totalSize = totalSize;
//	}
//	public int getPageCount() {
//		return pageCount;
//	}
//	public void setPageCount(int pageCount) {
//		this.pageCount = pageCount;
//	}
//	public int getPageSize() {
//		return pageSize;
//	}
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//	public String getPath() {
//		return path;
//	}
//	public void setPath(String path) {
//		this.path = path;
//	}
//	public String getParam() {
//		return param;
//	}
//	public void setParam(String param) {
//		this.param = param;
//	}
//	public int getCurrPage() {
//		return currPage;
//	}
//	public void setCurrPage(int currPage) {
//		this.currPage = currPage;
//	}
//	public int getTotalPage() {
//		return totalPage;
//	}
//	public void setTotalPage(int totalPage) {
//		this.totalPage = totalPage;
//	}
//	public void doTag() throws JspException, IOException {
//		JspWriter out = this.getJspContext().getOut();
//		StringBuffer sb = new StringBuffer();
//		
//		sb.append("<div id=\"pagediv\"><ul class=\"pagination pagination-right\">") ;
//
//		if (currPage == 1) {
//			sb.append("<li><span>首页</span></li><li><span>上一页</span></li>");
//			
//		} else {
//			sb.append("<li><a href=\"" + path + param + "=" + (currPage - 1) + "\">上一页</a></li>" +
//					"<li><a href=\"" + path + param + "=1\">首页</a></li>");
//		}
//		// 显示页码 默认显示pageNumber个页码
//		int pageNumber = PropertyUtil.getPageNumber();
//		// 总页数小于需要显示的页码数
////		int result = (currPage-1) / pageNumber;
//		int begin = currPage - 2 < 1 ? 1 : currPage - 2;
//		int end = begin + pageNumber * 2 > totalPage ? totalPage : begin + pageNumber * 2;
//		int i;
//		for (i = begin; i <currPage; i++) {
//			sb.append("<li>").append("<a href=\"").append(path).append(param).append("=")
//					.append(i).append("\"").append(">").append(i).append("</a>").append("</li>");
//		}
//		sb.append("<li class=\"disabled\"><span>").append(currPage).append("</span></li>");
//		for (i = currPage+1; i <= end; i++) {
//			sb.append("<li>").append("<a href=\"").append(path).append(param).append("=")
//					.append(i).append("\"").append(">").append(i).append("</a>").append("</li>");
//		}
//		if (currPage == totalPage) {
//			sb.append("<li><span>下一页</span></li><li><span>末页</span></li>");
//		} else {
//			sb.append("<li><a href=\"" + path + param + "=" + totalPage + "\">末页</a></li>" +
//					  "<li><a href=\"" + path + param + "=" + (currPage + 1) + "\">下一页</a></li>");
//		}
//		sb.append("<li>&nbsp;&nbsp;&nbsp;&nbsp;总共<font color=\"red\">" + totalSize + "</font>条结果&nbsp;&nbsp;&nbsp;&nbsp;" +
//				  "当前<font color=\"red\">" + currPage + "</font>/<font color=\"red\">" + pageCount + "页</font></li>&nbsp;&nbsp;&nbsp;&nbsp;</ul></div>");
//		out.println(sb);
//	}
//}
