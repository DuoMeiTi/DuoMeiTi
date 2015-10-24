<%@ include file="/jsp/base/taglib.jsp" %>


<!-- 	<div class="mycontent"> -->
	
	
	
		

		<table class="table device-table-bordered" id="roompicture_table">
			<tbody>
				<s:iterator value="picture_list" var="picture" status="i">
					<div class="form-group col-lg-offset-3">
						<label for="username"><s:property value="#picture.remark"/>:</label>
					</div>
					<div class="form-group col-lg-offset-3">
						<img src="<s:property value="#picture.path"/>"  width="100" height="100" />   
					</div>
					
					<br/>
				</s:iterator>
			</tbody>
		</table>	
			
			
			
			
		
		

	
	
	