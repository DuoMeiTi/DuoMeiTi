//package page;
//
//import util.PropertyUtil;
//public class PageMessage {
//	public static PageBean getPageMessage(int currPage, int totalSize)
//			throws Exception {
//		PageBean pagebean = new PageBean();
//		int pageSize = PropertyUtil.getPageSize();
//		int pageCount = 0;
//		int prePage = 0;
//		int nextPage = 0;
//		int begindex;
//		int endindex;
//		// 总页数
//		if (totalSize % pageSize == 0) { // 判断是否整除
//			pageCount = (int) totalSize / pageSize;
//		} else {
//			pageCount = (int) totalSize / pageSize + 1;
//		}
//		if (currPage > 1) {
//			prePage = currPage - 1; // 上一页
//		} else {
//			prePage = 1;
//		}
//		if (currPage < pageCount) {
//			nextPage = currPage + 1; // 下一页
//		} else {
//			nextPage = currPage;
//		}
//		 //开始索引
//		 if (currPage > pageCount) {
//			 begindex = (pageCount - 1) * pageSize;
//			} else {
//				begindex = (currPage - 1) * pageSize;
//			}
//		 //结束索引
//		 if(currPage*pageSize>totalSize){
//			 endindex = totalSize ;
//		 }else{
//			 endindex=currPage*pageSize ;
//		 }
//		
//		pagebean.setPrePage(prePage);
//		pagebean.setPageSize(pageSize);
//		pagebean.setCurrPage(currPage);
//		pagebean.setTotalSize(totalSize);
//		pagebean.setPageCount(pageCount);
//		pagebean.setLastPage(pageCount);
//		pagebean.setNextPage(nextPage);
//		pagebean.setBeginIndex(begindex) ;
//		pagebean.setEndIndex(endindex) ;
//		return pagebean;
//	}
//}
