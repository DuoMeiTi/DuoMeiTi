<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="mycontent">
	

<div>



  <ul class="nav  nav-pills"  style="position:relative;left:800px;">
    <li role="presentation" >
    	<a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a>
    </li>
    <li role="presentation" class="active">
    	<a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Profile</a>
    </li>
<!--     <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a></li> -->
<!--     <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li> -->
  </ul>
  
  
  

  <div class="tab-content">
    <div role="tabpanel" class="tab-pane " id="home">
    	<%@ include file="trainingInfo.jsp" %>
    </div>
    
    <div role="tabpanel" class="tab-pane active" id="profile">
		<%@ include file="exam.jsp" %>
    </div>
<!--     <div role="tabpanel" class="tab-pane" id="messages">.rwerqwerzczsdfsd.</div> -->
<!--     <div role="tabpanel" class="tab-pane" id="settings">..rrr.</div> -->
  </div>


</div>











</layout:override>

<%@ include file="base.jsp" %>