<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>

<center>		

		<table class="table table-bordered table-hover" id="contacts_table"  >
			<s:iterator begin="0" end="contacts_list.size()-1" var="i" step="6">
				<tr>
					<s:iterator var="j" begin="0" end="@@min(contacts_list.size()-#i-1,5)" step="1"> 
						<td> 
							<div>
								<img src="<s:property value="contacts_list.get(#i+#j).user.profilePhotoPath"/>" 
								height="120px" width="100px" >  
							</div>
							<div>
								<s:property value="contacts_list.get(#i+#j).user.fullName"/>  
							</div>
							<div>
								学号：<s:property value="contacts_list.get(#i+#j).studentId"/>  
							</div>
							<div>
								电话：<s:property value="contacts_list.get(#i+#j).user.phoneNumber"/>  
							</div>
							<div>
								学院：
								
								<s:set name="collgeName" value="contacts_list.get(#i+#j).college.substring(0, 2)" >  </s:set>
								<s:if test = "#collgeName == '管理' ">
									管经
								</s:if>
								<s:elseif test = "#collgeName == '电子' ">
									电信
								</s:elseif>
								<s:elseif test = "#collgeName == '建设' ">
									建工
								</s:elseif>
								<s:elseif test = "#collgeName == '建筑' ">
									建艺
								</s:elseif>
								
								
								<s:else>
									<s:property value =  "collgeName"/>
								</s:else>
													
								
																
								
							</div>
							
						</td>
					</s:iterator>
				</tr>
			
			</s:iterator>
		
		</table>

</center>