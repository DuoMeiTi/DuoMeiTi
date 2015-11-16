<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">

<div class="row news_frame">
  <div class="col-lg-6">
    <!--news base template-->
		<div class="">
		  <div class="front-wrap">
		    <div class="home-news">
		      <div class="usoft-listview-header">
		        <h3>通知公告          <small>announcement </small> </h3>
		        <span class="usoft-listview-more">						          
					<strong><a href="home/more_announcement">更多»</a></strong>						
		        </span>
		      </div>
		      
		      <div class="news-list">
		      
		      
		        <div class="news-documents documents-list ">
		         	
		         	<s:iterator value="notice_list" var="i" status="index" >
		         	 	
	         	 		<div class="usoft-listview-basic">
						  <ul>
						    <li>
						      <span class="usoft-listview-item-date">
						      		<s:property value="#i.time.toString()"/> 
							  </span>
						      <span>
						      	<a href="#" > 
						      		<s:property value="#i.title"/> 
						      		:
						      		<s:property value="#i.content"/>
						      	</a>
						      </span>
						    </li>
						  </ul>
						</div>
		         	</s:iterator>	        	
		        

		
		
		        </div><!--end news-documents-->
		      </div><!--end news-list-->
		      
		    </div><!--home-news-->
		  </div><!--front wrap-->
		</div>

  </div>  <!--<div class="col-lg-6"> -->
  
  
  <div class="col-lg-6">
    <!--important news-->
			<!--news base template-->
			<div class="">
			  <div class="front-wrap">
			    <div class="home-news">
			      <div class="usoft-listview-header">
			        <h3>检查教室 <small>check-classroom</small> </h3>
			        <span class="usoft-listview-more">
						<strong><a href="home/more_classroom">更多»</a></strong>
			        </span>
			      </div>			      
			      <div class="news-list">
			        <div class="news-documents documents-list ">
							<%@ include file = "/jsp/homepage/widgets/more_classroomTable.jsp" %>
<%-- 						<s:iterator value="check_list" var="i" status="index" >    --%>
						
<!-- 						   <div class="usoft-listview-basic"> -->
<!-- 							  <ul> -->
<!-- 							    <li> -->
<!-- 							      <span class="usoft-listview-item-date"> -->
<%-- 							      	<s:property value="#i.checkdate"/> --%>
<!-- 								  </span> -->
<!-- 							      <span> -->
<!-- 							      	<a href="#" title=""> -->
<%-- 							      		<s:property value="#i.checkman.fullName"/>检查<s:property value="#i.classroom.teachbuilding.build_name"/><s:property value="#i.classroom.classroom_num"/> --%>
<!-- 							      		: -->
<%-- 							      		<s:property value="#i.checkdetail"/> --%>
<!-- 							      	</a> -->
<!-- 							      </span> -->
<!-- 							    </li> -->
<!-- 							  </ul> -->
<!-- 							</div> -->
<%-- 						 </s:iterator> --%>

			
			        </div><!--end news-documents-->
			      </div><!--end news-list-->
			      
			    </div><!--home-news-->
			  </div><!--front wrap-->
			</div>

  </div>
</div>
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
<div class="row news_frame">
  <div class="col-lg-6">
    <!--news base template-->
		<div class="">
		  <div class="front-wrap">
		    <div class="home-news">
		      <div class="usoft-listview-header">
		        <h3>设备维修记录          <small>Equipment - maintenance - records </small> </h3>
		        <span class="usoft-listview-more">					          
				<strong><a href="home/more_equipment_maintenance">更多»</a></strong>						
		        </span>
		      </div>						      
		      <div class="news-list">
		        <div class="news-documents documents-list "> 		        
					<%@ include file = "/jsp/homepage/widgets/more_equipment_maintenanceTable.jsp" %>		        
		        </div><!--end news-documents-->
		      </div><!--end news-list-->
		      
		    </div><!--home-news-->
		  </div><!--front wrap-->
		</div>				
  </div>  <!--<div class="col-lg-6"> -->
  
  
  
  
  
  
  
  
  
  
  
  
  
    <div class="col-lg-6">
    <!--news base template-->
		<div class="">
		  <div class="front-wrap">
		    <div class="home-news">
		      <div class="usoft-listview-header">
		        <h3>设备更换提醒         <small>Equipment- replacement- reminder </small> </h3>
		        <span class="usoft-listview-more">					          
				<strong><a href="home/more_equipment_replacement">更多»</a></strong>						
		        </span>
		      </div>						      
		      <div class="news-list">
		        <div class="news-documents documents-list "> 
		        
		        	<%@ include file = "/jsp/homepage/widgets/more_equipment_replacementTable.jsp" %>
		        
		        </div><!--end news-documents-->
		      </div><!--end news-list-->
		      
		    </div><!--home-news-->
		  </div><!--front wrap-->
		</div>				
  </div>  <!--<div class="col-lg-6"> -->
  

</div>
				
				
				
				
				
				
				
				
				
				




</layout:override>










<%@ include file="/jsp/homepage/new_home_base.jsp" %>

