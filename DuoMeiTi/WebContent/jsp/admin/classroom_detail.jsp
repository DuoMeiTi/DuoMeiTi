<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">

	<div class="mycontent">
	
	
	
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3 classbuilding">
				<span><s:property value="classroom.teachbuilding.build_name"/></span>&nbsp;&nbsp;<span><s:property value="classroom.classroom_num"/></span>&nbsp;&nbsp;&nbsp;&nbsp;
				<span>负责人:</span>
				<span id="classroomid" style="visibility:hidden"><s:property value="classroom.id"/></span>
				<span class="director-span"><s:property value="classroom.principal.user.fullName"/></span>
			</div>
		</div>
		
	
	
		<br>

		<ul class="nav  nav-pills"  >
		
		    <li role="presentation" class="active">
		    	<a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">详细信息</a>
		    </li>
		    
   		    <li role="presentation" >
		    	<a href="#home" aria-controls="home" role="tab" data-toggle="tab">教室照片</a>
		    </li>
		    
		
		 	<li role="presentation" >
		    	<a href="#schedule" aria-controls="schedule" role="tab" data-toggle="tab">教室课表</a>
		    </li>
		    
		</ul>
		<hr>
		<div class="tab-content">
		
		    <div role="tabpanel" class="tab-pane active" id="profile">
		    
		    
<%-- 				<%@ include file="classroom_information.jsp" %> --%>

				<%@ include file="/jsp/classroom/classroomInformation.jsp" %>
				
			</div>
			
		    <div role="tabpanel" class="tab-pane " id="home">
		    	<%@ include file="classroom_picture.jsp" %>
		    </div>
		
		
			<div role="tabpanel" class="tab-pane " id="schedule">
		    	<%@ include file="classroom_schedule.jsp" %>
		    </div>
	
		</div>		
		<br>
		<br>
		<br>
		
	</div>
	
</layout:override>

<%@ include file="base.jsp" %>