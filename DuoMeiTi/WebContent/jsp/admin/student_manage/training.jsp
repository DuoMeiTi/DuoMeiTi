<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="mycontent">
	

<div>
  <ul class="nav  nav-pills"  style="position:relative;left:800px;">
    <li role="presentation" >
    	<a href="#info" aria-controls="info" role="tab" data-toggle="tab">培训通知</a>
    </li>
    <li role="presentation" class="active">
        <a href="#exam" aria-controls="exam" role="tab" data-toggle="tab">考试系统</a>
    </li>
<!--     <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a></li> -->
<!--     <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li> -->
  </ul>

  <div class="tab-content">
    <div role="tabpanel" class="tab-pane " id="info">
    	<%@ include file="info.jsp" %>
    </div>
    <div role="tabpanel" class="tab-pane active" id="exam">
    
		<%@ include file="exam.jsp" %>
		
		
    </div>
<!--     <div role="tabpanel" class="tab-pane" id="messages">.rwerqwerzczsdfsd.</div> -->
<!--     <div role="tabpanel" class="tab-pane" id="settings">..rrr.</div> -->
  </div>
</div>

</layout:override>

<%@ include file="base.jsp" %>