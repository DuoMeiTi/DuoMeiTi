<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">

<div class="row news_frame">
  <div class="col-lg-6">
    <!--news base template-->
		<div class="">
		  <div class="front-wrap">
		    <div class="home-news">
		      <div class="usoft-listview-header">
		        <h3>通知公告        
		          <small>
		        	  <span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>
				  </small>  
					 
					 
		          </h3>
		        <span class="usoft-listview-more">						          
					<strong><a href="home/more_announcement">更多»</a></strong>						
		        </span>
		      </div>
		      
		      <div class="news-list">
		      
		      
		        <div class="news-documents documents-list ">
		         	<%@ include file="/jsp/homepage/widgets/more_announcementTable.jsp" %>

		        

		
		
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
			        <h3>检查教室 
						<small> <span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>
						</small>
					</h3>
			        <span class="usoft-listview-more">
						<strong><a href="home/more_classroom">更多»</a></strong>
			        </span>
			      </div>			      
			      <div class="news-list">
			        <div class="news-documents documents-list ">
							<%@ include file = "/jsp/homepage/widgets/more_classroomTable.jsp" %>


			
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
		        <h3>设备维修记录          <!-- <small>Equipment - maintenance - records </small> -->
			        <small> <span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>
					</small>
			        
		         </h3>
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
		      <div class="usoft-listview-header" >
		        <h3>设备更换提醒      
		           <!-- <small>Equipment- replacement- reminder </small> -->
		           <small> <span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>
						</small>
		            </h3>
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

