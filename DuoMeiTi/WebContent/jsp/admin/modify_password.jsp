<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">

<div class="mycontent">
	<div class="row">
			<div class="col-lg-6 col-lg-offset-3 classbuilding">
				<span>修改密码</span>
			</div>
	</div>
 	<br />
 	<br />
 	<!-- please input initial password -->
 	<form class="form-inline" style="margin-left:60px;" action="ModifyPassword" method="POST" >
  		
  		<div class="form-group">
    		<label for="input_initial_password">输入原密码</label>
    		<div class="input-group">
      			<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
      			<input type="password" class="form-control" id="input_initial_password" >
    		</div>
    		<br />
    		<br />		
  		    <label for="input_new_paasword">输入新密码</label>
    			<div class="input-group">
      				<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
      				<input type="password" class="form-control" id="input_new_password" >
    			</div>
    		<br />
    		<br />
    		<label for="repeat_new_password">确认新密码</label>
    			<div class="input-group">
      				<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
      				<input type="password" class="form-control" id="repeat_new_password" >
    			</div>
    		<br />
    		<br />
    		&nbsp &nbsp <button type="submit" class="btn btn-primary">确认</button>
	    </div>
	     
	</form>
	
	
</div>



</layout:override>

<%@ include file="/jsp/admin/base.jsp" %>