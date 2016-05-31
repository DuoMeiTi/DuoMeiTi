<%@ include file="/jsp/base/taglib.jsp" %>



<layout:override name="main_content">
	

    <div class="mynavbar">
        <div class="container-fluid nopadding">
		    <div class="row nomargin">
		        <div class="col-lg-3 nopadding">
		           <a href="notice" class="navbar-button">公告管理</a> 
		        </div>
		        
    	        <div class="col-lg-3 nopadding">
		           <a href="resourceFileManage" class="navbar-button navbar-button-left">资源文件管理</a> 
		        </div>
		        
		        <div class="col-lg-3 nopadding">
		           <a href="CommonProblem" class="navbar-button">常见问题记录</a> 
		        </div>
		        
		        
    			<div class="col-lg-3 nopadding">
		           <a href="globalSetting" class="navbar-button">学期第一周设置</a> 
		        </div>
		        
		    </div>
		</div>

    </div>
    <div class="mycontent">

        	
        	<layout:block name="mycontent">
        		
        	</layout:block>
    </div>
	
	<script>
// 		alert("SBSB");
	</script>
	

</layout:override>








<%@ include file="/jsp/admin/base.jsp" %>
