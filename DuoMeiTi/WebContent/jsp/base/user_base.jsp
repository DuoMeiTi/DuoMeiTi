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
                    			水电费水电费
                    		<s:set name="age" value="61"/>
	                    		<s:if test="#age > 60"> 
	                    		 老年人 
								</s:if>
							<s:elseif test="#age > 35">
								中年人
							</s:elseif>
							
							
<!-- 	                    <div class="menu-list"> -->
<!-- 						    <a href="/adminStaff/studentmanage" class="menu-button-middle"><span class="glyphicon glyphicon-heart">&nbsp;学生管理</span></a> -->
<!-- 						    <a href="/adminStaff/teachermanage" class="menu-button-middle"><span class="glyphicon glyphicon-heart-empty">&nbsp;教师管理</span></a> -->
<!-- 						    <a href="/adminStaff/mycourseplan" class="menu-button-middle"><span class="glyphicon glyphicon-list-alt">&nbsp;培养计划</span></a> -->
<!-- 						    <div class="menu-blank"></div> -->
<!-- 						</div>		                     -->
                    </layout:block>	
                </div>
                <div class="col-lg-9">  
                                  
                    	<layout:block name="main_content">
<!-- 	                    asdasdffsdfsf -->
<!-- 	                    <div class="btn-group" role="group" aria-label="..."> -->
<!-- 						  <button type="button" class="btn btn-default">Left</button> -->
<!-- 						  <button type="button" class="btn btn-default">Middle</button> -->
<!-- 						  <button type="button" class="btn btn-default">Right</button> -->
						  
<!-- 						  <button type="button" class="btn btn-default" aria-label="Left Align"> -->
<!--  								<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span> -->
<!--  								sdfdf -->
<!-- 						  </button> -->
<!-- 						</div> -->
                    
                    	</layout:block>
                </div>
            </div>
        </div>
	
        <layout:block name="nav_content">
<!-- 	        	sdfsf+++++++++++++++++++ -->
        </layout:block>
	</div>
	
	
</layout:override>


<%@ include file="/jsp/base/base.jsp" %>


   	