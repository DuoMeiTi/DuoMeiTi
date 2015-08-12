<%@ include file="/jsp/base/taglib.jsp" %>



<layout:override name="base_content">
	
	<div id="nav-container">
	    <div id="headerpic">
	        <ul class="nav navbar-right">
	            <li id="welcome_word">
	            	欢迎您：{{}} 
	            </li>
	        </ul>
	    </div>
	   
        <div class="container-fluid main-container">
            <div class="row col-lg-12">
                <div class="col-lg-3 nav-left">	                    
                    <layout:block name="menu_list">
                    </layout:block>	
                </div>
                <div class="col-lg-9">
                	<div class="main-content">
	                   	<layout:block name="main_content">
	                   	</layout:block>
	                </div>
                </div>
            </div>
        </div>	
        <layout:block name="nav_content">
        </layout:block>
	</div>
	
	
</layout:override>


<%@ include file="/jsp/base/base.jsp" %>


   	