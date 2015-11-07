<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">

<div class="mycontent">
	<div >
	<h>添加管理员账户</h>
	<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" style="margin:2px;" data-target="#admin_add" id="addbutton">添&nbsp;&nbsp;加</button>
	</div>
	<div class="student_table">
		<table class="table table-bordered" id="admin_information_table">
			
			<tr class="row" >
				<th >姓名</th>
				<th >性别</th>
				<th >电话</th>
				<th >工作单位</th>
				<th >备注</th>
				<th >删除</th>
			</tr>
			<s:iterator value="allAdminProfilelist" var="i"  status="index"> 
    			<tr class="row" id=<s:property value="#i.id"/>>
					<td class="col-lg-1.5"> <s:property value="#i.user.fullName"/> </td>
					<td class="col-lg-1.5"> <s:property value="#i.user.sex"/>  </td>
					<td class="col-lg-1"> <s:property value="#i.user.phoneNumber"/> </td>
					<td class="col-lg-1.5"> <s:property value="#i.unitinfo"/> </td>
					<td class="col-lg-1.5"> <s:property value="#i.user.remark"/> </td>			
					<td class="col-lg-1 ">
						<button type="button" class="btn btn-primary btn-sm delete" id="delede-button">删除</button>	
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	
	
	<!-- 添加管理员模态框 -->
	<div class="modal fade" id="admin_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   		<div class="modal-dialog">
      		<div class="modal-content">
         		<div class="modal-header">
		            <button type="button" class="close" 
		               data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
			            <h4 class="modal-title" id="myModalLabel">
			               		添加管理员账户
			            </h4>
         		</div>
		        <form class="bs-example bs-example-form" id="add_admin_form" role="form">
		         <div class="input-group">
			         <span class="input-group-addon">用户名</span>
			          <input type="text" name="username" class="form-control" id="usernameInput">
			      </div>
			      <br>
			      
			      <div class="input-group">
			         <span class="input-group-addon">真实姓名</span>
			          <input type="text" name="fullName" class="form-control" id="fullNameInput">
			      </div>
			      <br>
			      
			      <div class="input-group">
			         <span class="input-group-addon">密码</span>
			         <input type="password" name="password" class="form-control" placeholder="password" id="passwordInput1">
			      </div>
			      <br>
			       <div class="input-group">
			         <span class="input-group-addon">再次输入密码</span>
			          <input type="password" name="password2" class="form-control" placeholder="password" id="passwordInput2">
			      </div>
			      <br>
			   </form>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" id="addaccount">
              		 添加
            </button>
         </div>
	      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
	</div>
	
</div>
<script type='text/javascript' src="/js/admin/adminaccount.js"></script>
</layout:override>

<%@ include file="/jsp/admin/base.jsp" %>