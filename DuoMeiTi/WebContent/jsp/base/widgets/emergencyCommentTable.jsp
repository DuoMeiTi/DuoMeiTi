<%@ include file="/jsp/base/taglib.jsp" %>
			
			
	<s:iterator var="i" begin="0" end="emergencyCommentList.size() - 1" step = "1" >
	
		<li class="list-group-item list-group-item-info emergencyComment
		
			<s:if test='notReadList != null && notReadList.get(#i)'>
 					list-group-item-danger
			</s:if>
		
		"  >
	
			<div class="row">					
				<div class="col-md-1">
					<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
				</div>
				
				<div class="col-md-10">
					<div class="row">
						 	<div class="col-md-3" style="font-size:10px!important;font-weight:bolder!important;" >
					 			<s:property value="emergencyCommentList.get(#i).user.fullName" />
	  						</div>			  						
	  						<div class="col-md-8" 
	  						style="font-size:10px!important; filter:alpha(opacity=70)!important;opacity:0.7!important;" >
	  							发布于
	  							<s:property value="@util.Util@formatTimestamp(emergencyCommentList.get(#i).date)" />
	  						</div>
					</div>			
					<div class="row">				
						 	<div class="col-md-12 "  style="font-size:10px!important;font-weight:normal!important; ">
						 		<s:property value="emergencyCommentList.get(#i).content" />
<%-- 						 		<s:property value="#i.content" /> --%>
							</div>  						
					</div>
			 	</div> 
			 	<div class="col-md-1"></div>
			</div>
		</li>
			
	</s:iterator>
	
	<li class="list-group-item list-group-item-info ">
		 
		 				
		<div class="row">				
			<div class="col-md-1">
				<span class="glyphicon glyphicon-comment" aria-hidden="true"></span> 
			</div>
			<div class="col-md-10">		
				<div class="row">				
					<textarea class="form-control  publishEmergencyCommentTextarea" rows="3" ></textarea>
				</div>
		 	</div> 
		 	<div class="col-md-1"> </div>
		</div>
		<div class="row">
			<div class="col-md-2">
				<button class="btn btn-danger btn-sm publishEmergencyCommentButton">发布评论 </button>
			</div>
			<div class="col-md-10">
				
			</div>
		</div>
	</li>
