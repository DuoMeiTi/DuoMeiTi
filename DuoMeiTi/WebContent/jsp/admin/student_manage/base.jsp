<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">
	
	
	
	<div class="mynavbar">
		<div class="container-fluid nopadding">
    		<div class="row nomargin">
        
        		<div class="col-lg-3 nopadding">
           			<a href="/admin/student_manage/student_information" class="navbar-button navbar-button-left">学生信息</a> 
        		</div>
        		
        		<div class="col-lg-3 nopadding" >
           			<a href="/admin/student_manage/duty_manage" class="navbar-button">值班管理</a> 
        		</div>
        		
       			<div class="col-lg-3 nopadding" >
           			<a href="/admin/student_manage/rule_show" class="navbar-button">规章制度</a> 
        		</div>
        		
        		
        		
        		<div class="col-lg-3 nopadding">
           			<a href="/admin/student_manage/training" class="navbar-button">技术培训</a> 
<!--         			<a class="dropdown-toggle navbar-button" data-toggle="dropdown" href="" role="button" aria-haspopup="true" aria-expanded="false">技术培训<span class="caret"></span></a> -->
<!--         			<ul class="dropdown-menu"> -->
<!--         				<li><a href="/admin/student_manage/training">培训通知</a> -->
<!--         				<li><a href="/admin/student_manage/exam">考试系统</a> -->
<!--         			</ul> -->
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


