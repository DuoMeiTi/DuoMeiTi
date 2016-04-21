<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="base_content">
	
 

<div class="row pageHeader"  >
   	
   
	  <div class="col-md-5  "  >
			 <a href="/">
			 	<div class="pageHeader" >			 	
			 	</div>
			 </a>
	  </div>
	  
  
	 	<div class="col-md-5"  >  	
	       <ul class="nav navbar-right">
	           <li id="welcome_word">            	
		           	欢迎您: 
		           	<span id="login_user_name">
		           		<s:property value="#session.fullName"/>
		           	</span>
		           	            	
		           	<s:if test="#session.role.equals(@util.Const@AdminRole)">
		           		(您是管理员)
		           	</s:if>
		           	<s:elseif test="#session.role.equals(@util.Const@StudentRole)">
		           		(您是在职学生)
		           	</s:elseif>
		           	<s:else>
		           		(您是有管理员权限的在职学生)
		           	</s:else>           	
	           	
	           </li>
	       </ul>
		</div>
		

  
	  <div class="col-md-2 pageHeader"  >	  	


			
		  	<button type="button" class="btn  btn-lg" id="emergencyInfoButton"
		  			style="margin-top:15px;">
	  			 <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
	  			 <span id="emergencyInfoButtonSpan">紧急消息</span>
	  			 	  			 
				
			</button>
			
<!-- 			<button class="btn btn-default" id="testAnimate"> -->
<!-- 				TEST -->
<!-- 			</button> -->



	  </div>
	
</div>



<div class="modal fade "   id="emergencyInfoModal" >
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">紧急消息处理</h4>
      </div>
      
      <div class="modal-body">
      
      	<div class="well">
      		<h4>发布紧急消息 </h4>
      		<div class="row">
      			<div class="col-md-11">
      				<textarea class="form-control" rows="3" id="publishEmergencyInfoTextarea" ></textarea>	
      			</div>
      			<div class="col-md-1">
      				<br/>
      				<button class="btn btn-info" id="publishEmergencyInfoButton" >发布</button>	
      			</div>
      		</div>     	
      		
      		<div class="row">
      			<div class="col-md-4">
      				<br/>
      				<button class="btn btn-info"   id="watchAllEmergencyInfoButton" > 查看所有紧急消息</button>	
      			</div>
      			
      			<div class="col-md-4">
      				<br/>
      				<button class="btn btn-info"   id="watchNotReadEmergencyInfoButton" > 查看新的未读消息</button>	
      			</div>

      		</div>	      		
      	</div>
      	

      	
        <div  class=" well" >

<!-- 			<div> -->
<!--   				<button class="btn btn-info" id="" >有新的紧急消息</button> -->
<!--       		</div>  -->

        	
        	<div id="emergencyInfoTableDiv">       

        	</div> 
        </div>
        
        
        
      </div>
      
      
      
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
<!--         <button type="button" class="btn btn-primary">Save changes</button> -->
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->











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


<!-- 
<div id="envelope"></div>
<div id="message-list-box" class="hide">
	<div class="message-list-top">
		<span class="title">消息</span>
		<span class="glyphicon glyphicon-remove closed"></span>
		<span class="glyphicon glyphicon-user users"></span>
	</div>
	<div class="message-list"></div>
	<div class="message-list-bottom" id="write-message">
		<span>发信息</span>
	</div>
</div>

<div id="message-contacts-box" class="hide">
	<div class="message-contacts-top">
		<span class="title">联系人</span>
		<span class="glyphicon glyphicon-remove closed"></span>
	</div>
	<div class="message-contacts">
    	<h4>
        	<a class="contacts-expand collapsed" data-toggle="collapse" href="#admin-contacts" aria-expanded="false" aria-controls="admin-contacts">
          		<span class="glyphicon glyphicon-plus"></span>  管理员
        	</a>
      	</h4>
    	<div id="admin-contacts" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
      		<ul class="contacts-list-group list-group"></ul>
    	</div>
	    <h4>
	        <a class="contacts-expand collapsed" data-toggle="collapse" href="#teacher-contacts" aria-expanded="false" aria-controls="teacher-contacts">
	          	<span class="glyphicon glyphicon-plus"></span>  教师
	        </a>
	    </h4>
	    <div id="teacher-contacts" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
	      	<ul class="contacts-list-group list-group"></ul>
	    </div>
	    <h4>
	        <a class="contacts-expand collapsed" data-toggle="collapse" href="#student-contacts" aria-expanded="false" aria-controls="student-contacts">
	          	<span class="glyphicon glyphicon-plus"></span>  学生
	        </a>
	    </h4>
	    <div id="student-contacts" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
	      	<ul class="contacts-list-group list-group"></ul>
	    </div>
	</div>
</div>

<div id="message-content-box" class="hide" from=<s:property value="#session.user_id"/>>
	<div class="message-content-top">
		<span class="title">某某某</span>
		<span class="glyphicon glyphicon-remove closed"></span>
		<span class="glyphicon glyphicon-user users"></span>
		<span class="glyphicon glyphicon-menu-left back"></span>
	</div>
	<div class="message-content"></div>
	<div class="message-tool"></div>
	<div class="message-writeboard">
		<textarea class="form-control" rows="4"></textarea>
		<div class="send-bottom">
			<button type="button" class="btn btn-sm" onclick="sendMessage()">发送</button>
		</div>
	</div>
</div>

<script type="text/javascript" src="/js/base/websocket.js"></script>
 -->
        
 <layout:block name="nav_content">
 
 </layout:block>
	

	
	
</layout:override>

<%@ include file="/jsp/base/base.jsp" %>


   	