<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">
	<div class="mycontent">
	
	
	
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3 classbuilding">
				<span>
					<s:property value="classroom.teachbuilding.build_name"/>&nbsp;&nbsp;
						<s:property value="classroom.classroom_num"/>
				</span>
				<span>负责人:</span>
				<span id="classroomid" style="visibility:hidden"><s:property value="classroom.id"/></span>
				<span class="director-span"><s:property value="classroom.principal.user.fullName"/></span>
			</div>
		</div>
		
	
	
		<br>
		<ul class="nav  nav-pills"  style="position:relative;left:500px;">
		    
		    <li role="presentation" class="active">
		    	<a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">详细信息</a>
		    </li>
		    <li role="presentation" >
		    	<a href="#check" aria-controls="check" role="tab" data-toggle="tab">周检查记录</a>
		    </li>
		    <li role="presentation" >
		    	<a href="#repair" aria-controls="repair" role="tab" data-toggle="tab">维修记录</a>
		    </li>
		    <li role="presentation" >
		    	<a href="#home" aria-controls="home" role="tab" data-toggle="tab">教室照片</a>
		    </li>
		</ul>
		<hr>
		
		<div class="tab-content">
	    <div role="tabpanel" class="tab-pane " id="home">
	    	<%@ include file="classroom_picture.jsp" %>
	    </div>
	    
	    <div role="tabpanel" class="tab-pane active" id="profile">
			<%@ include file="classroom_detail_device.jsp" %>
		</div>
		<div role="tabpanel" class="tab-pane " id="check">
			<%@ include file="classroom_detail_check.jsp" %>
		</div>
		<div role="tabpanel" class="tab-pane " id="repair">
			<%@ include file="classroom_detail_repair.jsp" %>
		</div>
	
		</div>		
		
	</div>
	
</layout:override>

<%-- <%@ include file="/jsp/homepage/new_home_base.jsp" %> --%>

<%@ include file="base.jsp" %>

