<%@page import="java.util.ArrayList,model.Classroom"%>
<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">
<script type='text/javascript' src="/js/homepage/classroom_information.js"></script>
<script type="text/javascript">

</script>


<div class="mycontent">
	<div class="row">
 	<div class="col-lg-6 col-lg-offset-3 classbuilding ">
		<span id="build_name"><s:property value="build_name"/></span>教学楼
	</div>
	</div>
	<hr>
	
	
	<form class="form-horizontal" id="classroom_search_form">
	<div class="form-horizontal">
		<div class="form-group">
        
			<label class="col-lg-2 control-label" style="margin:2px">教室查询：</label>
            
            
			<div class="col-lg-2">
				<select class="col-lg-2 form-control" style="margin:3px;"name="searchselect" id="searchselect">
					<option value="1">教室号</option>
					<option value="2">负责人</option>
				</select>
			</div>
            
			<div class="col-lg-2">
				<input type="text" class="col-lg-3 form-control" style="margin:3px;height:34px;" aria-describedby="basic-addon1" name="search" id="query_condition">
			</div>

			<div class="col-lg-1">
				<button type="button" class="btn btn-primary" style="margin:2px;" id="sc_button" onclick="queryclassrooms()">查&nbsp;&nbsp;询</button>
			</div>
			<div class="col-lg-2">
				<button type="button" class="btn btn-primary" style="margin:2px;" id="all_button" onclick="queryAll()">所&nbsp;有&nbsp;教&nbsp;室</button>
			</div>
		</div>
	</div>
	</form>
	
	
	<!-- 新加的教室列表 -->
	<table class="table table-bordered table-striped" id="classroom_table">
		
		<tr class="success" id="search_infor">
			<th>教室号</th>
            <th>负责人</th>
            <th>管理教室</th>
		</tr>
		
		
		
		<tr class="success" id="search_classroom" style="display: none;">
			<td class="success" id="search_class_num"></td>
			<td class="success" id="search_principal_name"></td>
			<td >
				<button type="button" class="btn btn-info edit" id="detail-button" name="edit-button" >详&nbsp;&nbsp;细</button></td>
		</tr>
		
		<s:iterator value="classrooms" var="i" status="index">
			<tr class="success" id=<s:property value="#i.id"/> studId=<s:property value="#i.principal_stuId"/>>
				<td ><s:property value="#i.classroom_num"/> </td>
				<td studId=<s:property value="#i.principal_stuId"/>><s:property value="#i.principal_name"/>    </td>
				<td >
				   <a href="classroom_detail?classroomId=<s:property value="#i.id"/>" class="btn btn-info">详&nbsp;&nbsp;细</a>     </td>
			</tr>		
		</s:iterator>
	</table>
	
	
	
	
	
	
	<%-- <div id="classroom_search_table">
	<table class="table table-bordered table-striped" id="classroom_table">
	<thead>
	<tr>
		<th>教室号</th>
            <th style="display:none;">设备</th>
            <!-- <th>教室大小</th> -->
            <th>负责人</th>
            <!-- <th>编辑</th> -->
            <th>查看教室</th>
        </tr>
      </thead>

      <s:iterator value="classrooms" var="classroom" status="i">  
			<tr class="success" classroom_id=<s:property value="#classroom.id"/> >
				<td>   <s:property value="#classroom.classroom_num"/>    </td>
				<td width="40%" style="display:none;">   <s:property value="#classroom.repertorys"/>    </td>
				<td>   <s:iterator value="#classroom.repertorys" var="rt"> <s:property value="#rt.rtType"/>&nbsp;&nbsp;&nbsp;</s:iterator>   </td>
				<td>   <s:property value="#classroom.capacity"/>    </td>
				<td studId="<s:property value="#classroom.principal_stuId"/>">   <s:property value="#classroom.principal_name"/>    </td>
				<td>   <a class="btn btn-info" onclick="edit_classroom(<s:property value="#i.index"/>)">编&nbsp;&nbsp;辑</a></td>
				<td>   <a href="classroom_detail?classroomId=<s:property value="#classroom.id"/>" class="btn btn-info">详&nbsp;&nbsp;细</a>    </td>
				<td> <a class="btn btn-info" onclick="mypost(<s:property value="#i.index"/>)">详&nbsp;&nbsp;细</a></td>
			</tr>
		</s:iterator>
      
      
    </table>
  </div> --%>
		
</div>

</layout:override>

<%@ include file="base.jsp" %>