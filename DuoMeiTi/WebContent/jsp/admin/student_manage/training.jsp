<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="mycontent">
	

<div>
  <ul class="nav  nav-pills"  style="position:relative;left:800px;">
  
      <li role="presentation" class="active">
        <a href="#exam" aria-controls="exam" role="tab" data-toggle="tab">考试系统</a>
    </li>
  
    <li role="presentation" >
    	<a href="#info" aria-controls="info" role="tab" data-toggle="tab">培训通知</a>
    </li>

  </ul>

  <div class="tab-content">
    <div role="tabpanel" class="tab-pane " id="info">
    	<%@ include file="info.jsp" %>
    </div>
    <div role="tabpanel" class="tab-pane active" id="exam">    
		<%@ include file="exam.jsp" %>
    </div>


  </div>
</div>

</layout:override>

<%@ include file="base.jsp" %>