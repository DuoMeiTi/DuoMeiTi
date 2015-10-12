<%@page import="java.util.ArrayList,model.Classroom"%>
<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">
<script type='text/javascript' src="/js/admin/classroom_manage.js"></script>

<div class="mycontent">
  <div class="row">
    <div class="col-lg-6 col-lg-offset-3 classbuilding ">
      <span id="build_name"><s:property value="build_name"/></span>教学楼
    </div>
  </div>
  <hr>
	
  
  <div id="classroom_search_table">
    <table class="table table-bordered table-striped" id="classroom_table">
      <thead>
        <tr>
            <th>教室号</th>
            <th>设备</th>
            <!-- <th>教室大小</th> -->
            <th>负责人</th>
            <th>管理教室</th>
        </tr>
      </thead>

      <s:iterator value="classrooms" var="classroom" status="i">  
			<tr class="success" classroom_id=<s:property value="#classroom.id"/> >
				<td>   <s:property value="#classroom.classroom_num"/>    </td>
				<td width="40%">   <s:property value="#classroom.repertorys"/>    </td>
				<td studId="<s:property value="#classroom.principal_stuId"/>"><s:property value="#classroom.principal_name"/>    </td>				
				<td>   <a href="/student/classroom_detail?classroom_id=<s:property value="#classroom.id"/>&build_id=<s:property value="#classroom.teachbuilding_id"/>" class="btn btn-info">详&nbsp;&nbsp;细</a>    </td>			
				</tr>
		</s:iterator>
      
      
    </table>
  </div>
		<pt:pageInfo
			pageCount="${pageBean.pageCount }"
			currPage="${pageBean.currPage }"
			param="currPage" 
			path="${path}"
			totalPage="${pageBean.lastPage }" 
			pageSize="${pageBean.pageSize }"
			totalSize="${pageBean.totalSize }" 
		/>
		<%-- <span style="visibility:hidden" id="pageSize"><s:property value="pageSize"/></span> --%>
	</div>

</layout:override>

<%@ include file="/jsp/student/base.jsp" %>