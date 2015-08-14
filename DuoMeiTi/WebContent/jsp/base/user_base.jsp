<%@ include file="/jsp/base/taglib.jsp" %>



<layout:override name="base_content">
	
<a href="/">
    <div id="headerpic" >

        <ul class="nav navbar-right">
            <li id="welcome_word">
            	欢迎您：{{}} 
            </li>
        </ul>
    </div>
</a>

<div class="container-fluid main-container">
    <div class="row col-lg-12">
        <div class="col-lg-3 ">	                    
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

<div id="envelope"></div>
<div id="message-box" class="hide">
	<div class="message-box-top">
		<span class="title">消息</span>
		<span class="glyphicon glyphicon-remove closed"></span>
		<span class="glyphicon glyphicon-menu-left back"></span>
	</div>
	<div class="message-list"></div>
	<div class="message-content"></div>
</div>
        
 <layout:block name="nav_content">
 
 </layout:block>
	

	
	
</layout:override>


<%@ include file="/jsp/base/base.jsp" %>


   	