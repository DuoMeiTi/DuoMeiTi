<%@ include file="/jsp/base/taglib.jsp" %>



<layout:override name="base_content">
	
<a href="/">
    <div id="headerpic" >

        <ul class="nav navbar-right">
            <li id="welcome_word">
            	欢迎您: <span id="login_user_name"><s:property value="#session.username"/></span>
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
<div id="message-list-box" class="hide">
	<div class="message-list-top">
		<span class="title">消息</span>
		<span class="glyphicon glyphicon-remove closed"></span>
	</div>
	<div class="message-list">
		<ul class="list-group">
			<li class="list-group-item">消息1</li>
			<li class="list-group-item">消息2</li>
			<li class="list-group-item">消息3</li>
			<li class="list-group-item">更多消息..</li>
		</ul>
	</div>
	<div class="message-list-bottom" id="write-message">
		<span>发信息</span>
	</div>
</div>

<div id="message-content-box" class="hide">
	<div class="message-content-top">
		<span class="title">某某某</span>
		<span class="glyphicon glyphicon-remove closed"></span>
		<span class="glyphicon glyphicon-menu-left back"></span>
	</div>
	<div class="message-content">
		<div class="message clearfix">
            <span class="triangle"></span>
            <div class="article">我发信息给你了我发信息给你了我发信息给你了我发信息给你了我发信息给你了我发信息给你了我发信息给你了</div>
        </div>
        <div class="message clearfix fr">
            <span class="triangle right"></span>
            <div class="article">我发信息给你了我发信息给你了我发信息给你了我发信息给你了我发信息给你了我发信息给你了我发信息给你了</div>
        </div>
    	<div class="message clearfix">
    		<span class="triangle right"></span>
    		<div class="article">我发信息给你了我发信息给你了我发信息给你了我发信息给你了</div>
    	</div>
        <div class="message clearfix">
            <span class="triangle right"></span>
            <div class="article">我发信息给你了我发信息给你了我发信息给你了我发信息给你了</div>
        </div>
        <div class="message clearfix fr">
            <span class="triangle"></span>
            <div class="article">我发信息给你了我发信息给你了我发信息给你了我发信息给你了我发信息给你了</div>
    	</div>
    	<div class="message clearfix">
            <span class="triangle right"></span>
            <div class="article">我发信息给你了我发信息给你了我发信息给你了我发信息给你了</div>
        </div>
        <div class="message clearfix fr">
            <span class="triangle"></span>
            <div class="article">我发信息给你了我发信息给你了我发信息给你了我发信息给你了我发信息给你了</div>
    	</div>
	</div>
	<div class="message-contacts">
		
	</div>
	<div class="message-writeboard">
		
	</div>
</div>

<script type="text/javascript" src="/js/base/websocket.js"></script>
        
 <layout:block name="nav_content">
 
 </layout:block>
	

	
	
</layout:override>


<%@ include file="/jsp/base/base.jsp" %>


   	