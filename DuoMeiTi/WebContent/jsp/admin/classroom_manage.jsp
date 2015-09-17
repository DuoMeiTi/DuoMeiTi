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
  <form class="form-horizontal" action="classroom_search" id="classroom_search_form">
    <div class="form-horizontal">
        <div class="form-group">
        
            <label class="col-lg-2 control-label" style="margin:2px">教室查询：</label>
            
            
            <div class="col-lg-2">
                <select class="col-lg-2 form-control" style="margin:3px;"name="searchselect" id="searchselect">
                <option value="1">教室号</option>
                <option value="2">负责人</option>
            </select>
            </div>
            
			<div class="col-lg-3">
				<input type="text" class="col-lg-3 form-control" style="margin:3px;height:34px;" aria-describedby="basic-addon1" name="search" id="query_condition">
			</div>

			<div class="col-lg-1">
                <button type="button" class="btn btn-primary" style="margin:2px;" id="sc_button" onclick="queryclassrooms()">查&nbsp;&nbsp;询</button>
            </div>
            <div class="col-lg-1">
            	<button type="button" class="btn btn-primary" style="margin:2px;" id="all_button" onclick="queryAll()">所&nbsp;有&nbsp;教&nbsp;室</button>
            </div>
        </div>
    </div>
  </form>
  
  <div id="classroom_search_table">
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

      <s:iterator value="classrooms" var="classroom" >  
			<tr class="success" classroom_id=<s:property value="#classroom.id"/> >
				<td>   <s:property value="#classroom.classroom_num"/>    </td>
				<td width="25%">   <s:property value="#classroom.repertorys"/>    </td>
				<%-- <td>   <s:iterator value="#classroom.repertorys" var="rt"> <s:property value="#rt.rtType"/>&nbsp;&nbsp;&nbsp;</s:iterator>   </td> --%>
				<td>   <s:property value="#classroom.capacity"/>    </td>
				<td>   <s:property value="#classroom.principal"/>    </td>
				<td>   <a href="classroom_detail?classroom_id=<s:property value="#classroom.id"/>&classroom_num=<s:property value="#classroom.classroom_num"/>" class="btn btn-info active">详&nbsp;&nbsp;细</a>    </td>
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
	</div>

<script>
	function GetRequest() {
		//url例子：XXX.aspx?ID=" + ID + "&Name=" + Name；  
		var url = location.search; //获取url中"?"符以及其后的字串  
		var theRequest = new Object();
		if (url.indexOf("?") != -1)//url中存在问号，也就说有参数。  
		{
			var str = url.substr(1);
			strs = str.split("&");
			for (var i = 0; i < strs.length; i++) {
				theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
			}
		}
		return theRequest;
	}
	
	//jquery发送ajax请求
	function queryclassrooms() {
		//var params = $('#classroom_search_form').serialize(); //利用jquery将表单序列化
		//params = decodeURIComponent(params,true);
		$("#pagediv").css("display","none");
		var searchselect = $("#searchselect  option:selected").attr("value");
		var query_condition = $("#query_condition").val();
		var Request = new Object();
		//获取url中的参数  
		Request = GetRequest();
		var build_id = Request['build_id'];
		var build_name = Request['build_name'];
		var params = {
			"searchselect" : searchselect,
			"query_condition" : query_condition,
			"build_id" : build_id,
			"build_name" : build_name
		};
		
		$.ajax({
			url : '/admin/classroom_json/classroom_search',
			type : 'post',
			dataType : 'json',
			data : params,
			success : ClassroomSearchCallback
		});
	}

	function ClassroomSearchCallback(data) {
		if (data.status == "0") {
			$("#classroom_table tr:not(:first)").remove();
			$("#classroom_table tr:first").after(data.classroominfo_html);
			var classrooms = data.classrooms;
			var table = $("#classroom_search_table");
			var row;
			$(classrooms).each(function(i) {
				row = $(table).find("tr:eq(" + (i + 1) + ")");
				$(row).find("td:eq(0)").text(classrooms[i].classroom_num);
				$(row).find("td:eq(1)").text(classrooms[i].repertorys);
				$(row).find("td:eq(2)").text(classrooms[i].capacity);
				$(row).find("td:eq(3)").text(classrooms[i].principal);
				$(row).find("td:eq(4)").children().eq(0).attr("href", "classroom_detail?classroom_id=" + classrooms[i].id +"&classroom_num=" +classrooms[i].classroom_num);
			});
		} else if (data.status == "1") {
			animatedShow("查询关键字为空");
		} 
	}
	
	function queryAll() {
		$("#pagediv").css("display","block");
		$("#query_condition").val("");
		//获取url中的参数  
		Request = GetRequest();
		var build_id = Request['build_id'];
		var build_name = Request['build_name'];
		var params = {
			"searchselect" : "",
			"query_condition" : "",
			"build_id" : build_id,
			"build_name" : build_name
		};
		
		$.ajax({
			url : '/admin/classroom_json/classroom_search',
			type : 'post',
			dataType : 'json',
			data : params,
			success : ClassroomSearchCallback
		});
	}
</script>

</layout:override>

<%@ include file="/jsp/admin/base.jsp" %>