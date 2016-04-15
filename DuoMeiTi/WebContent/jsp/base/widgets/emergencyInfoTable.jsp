<%@ include file="/jsp/base/taglib.jsp" %>



<!-- 	<div class="well">  -->
	
<!-- 	notReadList -->
<%-- 					<s:if test='notReadList != null && notReadList.get(#i)'> --%>
<!-- 					list-group-item-danger -->
<%-- 				</s:if> --%>
<%-- 				<s:else> --%>
<%-- 				</s:else> --%>
	
	<s:iterator begin="0" end ="emergencyInfoList.size() - 1" var="i" step="1">
		<ul class="list-group " >
				
			<li 
				class= "list-group-item emergencyInfo
				
				<s:if test='notReadList != null && notReadList.get(#i)'>
 					list-group-item-danger
				</s:if>
				 "
				
				emergencyInfoId = '<s:property value="emergencyInfoList.get(#i).id" />'   >
				
				<div class="row">
					 	<div class="col-md-3" style="font-size:17px!important;font-weight:bolder!important;" >
					 			<s:property value="emergencyInfoList.get(#i) .user.fullName" />
  						</div>
  						
  						<div class="col-md-8" style="font-size:13px!important; filter:alpha(opacity=70)!important;opacity:0.7!important;" >
  							发布于<s:property value="@util.Util@formatTimestamp(emergencyInfoList.get(#i) .date)" />
  						</div>
				</div>				

				<div class="row">				
					 	<div class="col-md-12 "  style="font-size:17px!important;font-weight:normal!important; ">
					 		 <s:property value="emergencyInfoList.get(#i).content" />
						</div>  						
				</div>				
				
			</li>
			
			
			


		</ul>


	</s:iterator>
	
	
		
		
		
		
<!-- </div> -->

















