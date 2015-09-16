<%@page import="java.util.ArrayList,model.Classroom"%>
<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">
<div class="mycontent">
  <div class="row">
    <div class="col-lg-6 col-lg-offset-3 classbuilding ">
      <s:property value="build_name"/>教学楼
    </div>
  </div>
  <hr>
  <form class="form-horizontal" action="classroom_search" id="classroom_search_form" method="POST" >
    <div class="form-horizontal" id="search">
        <div class="form-group">
        
            <label class="col-lg-2 control-label" style="margin:2px">教室查询：</label>
            
            
            <div class="col-lg-2">
                <select class="col-lg-2 form-control" style="margin:3px;"name="searchselect" id="classroom_search">
                <option value="1">教室号</option>
                <option value="2">负责人</option>
            </select>
            </div>
            
			<div class="col-lg-3">
				<input type="text" class="col-lg-3 form-control" style="margin:3px;height:34px;" aria-describedby="basic-addon1" name="search" id="search">
			</div>

			<div class="col-lg-1">
                <button type="button" class="btn btn-primary" style="margin:2px;" id="sc_button">查&nbsp;&nbsp;询</button>
            </div>
            
        </div>
    </div>
  </form>
  
  <div id="classroom_search_table">
  
   
<%--     <%@include file="classroom_search_table.jsp" %> --%>
    <table class="table table-bordered table-striped" id="classroom_table">
      <thead>
        <tr>
            <th>教室号</th>
            <th>设备</th>
            <th>教室大小</th>
            <th>负责人</th>
            <th>管理</th>
        </tr>
      </thead>
   <%--    <%
          ArrayList list = (ArrayList)session.getAttribute("classroom_list");
          if(list.isEmpty()){
        	  %>
        	  <tr>
        	      <td align="center"><span>暂无相关教室信息！</span></td>
        	  </tr>
        	  <%
          }else{
        	  for(int i=0;i<list.size();i++){
        		  Classroom info = (Classroom)list.get(i);
        	      %>  
        	      <tr>
        	          <td><%=info.getClassroom_num() %></td>
        	          <td><%=info.getRepertorys() %></td>
                      <td>打印机</td>
        	          <td><%=info.getCapacity() %></td>
        	          <td><%=info.getPrincipal() %></td>
        	          <td><a href="classroom_detail" class="btn btn-info active">详&nbsp;&nbsp;细</a></td>
        	      </tr>
        	      <%
        	  }
          }
        	      %> --%>
        	 
        	  
        	  

      <s:iterator value="classroom_list" var="item" >  
			<tr class="success" classroom_id=<s:property value="#item.id"/> >
				<td>   <s:property value="#item.classroom_num"/>    </td>
				<td>   <s:property value="#item.repertorys"/>    </td>
				<td>   <s:property value="#item.capacity"/>    </td>
				<td>   <s:property value="#item.principal"/>    </td>
				<td>   <a href="classroom_detail" class="btn btn-info active">详&nbsp;&nbsp;细</a>    </td>
			</tr>
		</s:iterator>
      
      
    </table>
  </div>
</div>

<script>
    
    //     alert(params);
    
    /* var id = document.getElementById("classroom_search").selectedindex;
    var index = {"index":id};
    var params = $.extends({},param,index); */
    
    // alert(params);
    //jquery发送ajax请求
    $("#sc_button").Click(function(){
    	var params = $('#classroom_search_form').serialize(); //利用jquery将表单序列化
        params = decodeURIComponent(params,true);
		$.ajax({
			url : 'classroom_search',
			type : 'post',
			dataType : 'json',
			data : params,
			success : ClassroomSearchCallback
		});
	});

	function ClassroomSearchCallback(data) {
		if (data.status == "0") {

			$("#classroom_search_table").html(data.classroominfo.html);
			animatedShow("查询成功");
		} else if (data.status == "1") {
			animatedShow("查询关键字为空");
		} else {
			alert("error with status" + data.status);
		}
		//$("#add_new_courseplan").attr("disabled",false);

		//     	alert($(document).find("#added_user_tr").html());
		//     	alert(data.added_user_html);
		//     	alert(data.username);
	}
</script>

</layout:override>

<%@ include file="/jsp/admin/base.jsp" %>