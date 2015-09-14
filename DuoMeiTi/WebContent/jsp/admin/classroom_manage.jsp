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
            
            <!-- <div class="col-lg-2 btn-group">
              <button class="btn btn-default btn-lg dropdown-toggle" style="height:40px" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                教室号
                <span class="caret"></span>
              </button>
              <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                <li><a href="#">教室号</a></li>
                <li><a href="#">负责人</a></li>
              </ul>
            </div> -->
            
            <div class="col-lg-2">
                <select class="col-lg-2 form-control" style="margin:3px;"name="search" id="classroom_search">
                <option value="1">教室号</option>
                <option value="2">负责人</option>
            </select>
            </div>
            
			<div class="col-lg-3">
				<input type="text" class="form-control" style="margin:3px;" aria-describedby="basic-addon1" id="search">
			</div>

			<div class="col-lg-1">
                <button class="btn btn-primary" style="margin:2px;" id="sc_button">查&nbsp;&nbsp;询</button>
            </div>
            
        </div>
    </div>
  </form>
  
  <div id="classroom_search_table">
  
    <%@include file="classroom_search_table.jsp" %>
    
    <%-- <table class="table table-bordered table-striped" id="classroom_table">
      <thead>
        <tr>
            <th>教室号</th>
            <th>设备</th>
            <th>教室大小</th>
            <th>负责人</th>
            <th>管理</th>
        </tr>
      </thead>
      <!-- <tr>
        <td>201</td>
        <td>打印机</td>
        <td>20*40</td>
        <td>张三</td>
        <td><a href="classroom_detail" class="btn btn-info active">详&nbsp;&nbsp;细</a></td>
      </tr> -->
      <s:iterator value="classroom_list" var="i" status="index" >  
			<tr class="success">
				<td>   <s:property value="#i.classroom_num"/>    </td>
				<td>   <s:property value="#i.equipment"/>    </td>
				<td>   <s:property value="#i.size"/>    </td>
				<td>   <s:property value="#i.principal"/>    </td>
				<td>   <a href="classroom_detail" class="btn btn-info active">详&nbsp;&nbsp;细</a>    </td>
			</tr>
		</s:iterator>
      
      
    </table> --%>
  </div>
</div>

<script>
    $(document).on("click", "button", function (){
    var params = $('#classroom_search_form').serialize(); //利用jquery将表单序列化
    //     alert(params);
    //jquery发送ajax请求
    $("#classroom_search").change(function(){
    	$.ajax({
    	      url: 'classroom_search',
    	      type: 'post',
    	      dataType: 'json',
    	      data: params,
    	      success: ClassroomSearchCallback
    	    });
        });
    });
    
    function ClassroomSearchCallback(data)
    {
    	if(data.status =="0"){
    		$("#classroom_search_table").html(data.classroom_manage.jsp);
    		animatedShow("查询成功");
    	}
    	else if(data.status == "1"){
    		animatedShow("查询关键字为空");
    	}
    	else{
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