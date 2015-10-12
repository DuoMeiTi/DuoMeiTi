<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="mycontent">
	
	<div>
	
	  <!-- Nav tabs -->
	  <ul class="nav nav-pills  nav-justified" role="tablist">
	    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">培训信息</a></li>
	    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">考试管理</a></li>
<!-- 	    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a></li> -->
<!-- 	    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li> -->
	  </ul>
	
	  <!-- Tab panes -->
	  <div class="tab-content">
	    <div role="tabpanel" class="tab-pane active" id="home">
			<layout:block name="training">
			</layout:block>
		</div>
	    <div role="tabpanel" class="tab-pane" id="profile">
	    	<layout:block name="exam">
			</layout:block>
	    
	    </div>
<!-- 	    <div role="tabpanel" class="tab-pane" id="messages">...</div> -->
<!-- 	    <div role="tabpanel" class="tab-pane" id="settings">...</div> -->
	  </div>
	
	</div>
	

 
	
	
	
	
	
</layout:override>

<%@ include file="base.jsp" %>