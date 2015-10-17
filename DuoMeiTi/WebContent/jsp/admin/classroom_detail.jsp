<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">
	<div class="mycontent">
		<br>
		<ul class="nav  nav-pills"  style="position:relative;left:800px;">
		    <li role="presentation" >
		    	<a href="#home" aria-controls="home" role="tab" data-toggle="tab">教室照片</a>
		    </li>
		    <li role="presentation" class="active">
		    	<a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">详细信息</a>
		    </li>
		</ul>
		
		<div class="tab-content">
	    <div role="tabpanel" class="tab-pane " id="home">
	    	<%@ include file="classroom_picture.jsp" %>
	    </div>
	    
	    <div role="tabpanel" class="tab-pane active" id="profile">
			<%@ include file="classroom_information.jsp" %>
		</div>
	
		</div>
		
		<br>
		<br>
		<br>
		
		
	</div>
	
</layout:override>

<%@ include file="base.jsp" %>