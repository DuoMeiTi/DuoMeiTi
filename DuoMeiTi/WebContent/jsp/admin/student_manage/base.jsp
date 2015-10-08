<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">
	
	
	
	<div class="mynavbar">
		<div class="container-fluid nopadding">
    		<div class="row nomargin">
        
        		<div class="col-lg-4 nopadding">
           			<a href="/admin/student_manage/student_information" class="navbar-button navbar-button-left">学生信息</a> 
        		</div>
        
       			<div class="col-lg-4 nopadding" >
           			<a href="/admin/student_manage/rules" class="navbar-button">规章制度</a> 
        		</div>
        		
        		<div class="col-lg-4 nopadding">
           			<a href="/admin/student_manage/techteach" class="navbar-button navbar-button-left">技术培训</a> 
        		</div>
    
    		</div>
		</div>
	</div>
	
<div class="mycontent">

<layout:block name="mycontent">



</layout:block>



</div>	
	
	
	
</layout:override>


<%@ include file="/jsp/admin/base.jsp" %>

