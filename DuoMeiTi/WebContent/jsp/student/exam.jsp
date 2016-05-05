<%@ include file="/jsp/base/taglib.jsp"%>


<layout:override name="main_content">
<link rel="stylesheet" type="text/css" media="screen" href="/css/student/train.css" />
<div class="mycontent">
	

		<div id="showContent"  > 
		
		<div class="panel panel-primary">
			<div class="panel-heading center"><h2 style="font-weight:bold;">考试系统</h2></div>
  			<div class="panel-body">
  				<div class="item-info row">
  						<p class="col-sm-4">考试及格：需要答对80%及以上的题目</p>
  						<p class="col-sm-4">考试说明： 面向多媒体学生助教</p>  						
  				</div>  				
  			</div>
  			
		</div>
		
			
		
		<div id="showexamCont">
			<div id="exCont" class="panel panel-default"  >
				<div class="panel-body ">
				
	  			<s:iterator var="i" begin="0" end="examTitleList.size()-1" step="1"  >
					<ul class="exam_margin well" titleId=<s:property value="examTitleList.get(#i).emId" /> >
						<li class="margin_liTitle">
							<span><s:property value="#i + 1"/>.&nbsp;</span>
							<s:property value="examTitleList.get(#i).emTitle" escape="false"/>
						</li>
						
						<s:iterator var = "j" begin = "0" end = "examOptionList.get(#i).size() - 1">
							<li>
								<div opId=<s:property value="examOptionList.get(#i).get(#j).emId" /> 
								     class="opToback">
								
								
									<s:if test="status.charAt(0) != '0'">						
										<input type="checkbox" class="checks checkOption "										 
											<s:if test="studentOptoinList.get(#i).get(#j) == true">
												checked
											</s:if>									
								 		 />
									</s:if>
									<s:else>
									
										 <s:if test="studentOptoinList.get(#i).get(#j) == true" >
											<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
										</s:if>
										<s:else>
											<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
										</s:else>
										 
										 
									</s:else>
									
									
									<span class="">&nbsp;<s:property value="intToChar(#j)" />&nbsp;</span>
									<s:property value="examOptionList.get(#i).get(#j).emOption" />								
								</div>
							</li>
							
						
						
						</s:iterator>
						

					</ul>
				</s:iterator>
				<div class="center-block" style="max-width:300px;">
					<s:if test="status.charAt(0) != '0'">
						<button type="button" class="btn btn-primary btn-lg btn-block center" id="examSubmit">我要交卷</button>
					</s:if>
					<s:else>
						<h4>
							<s:property value="status.substring(1)"/>
						</h4>
					</s:else>
				</div>
			</div>			
	  </div>
	</div>
		
		
	</div>
		
<%-- 	</s:if> --%>
	
<%-- 	<s:else> --%>
<!-- 		<div class="alert alert-success" role="alert"> -->
<!-- 			<h2> 您已经通过考试，不需要答题了 </h2> -->
<!-- 		</div>	 -->
<%-- 	</s:else> --%>
	
	
	
		<script>
		
		
		

		$(document).on("click",".checkOption", function(){
			
			var checked = $(this).parent().children("input")[0].checked;
			
// 			alert(checked);
			
			params = {
					"checked":checked,
// 					"newNum": Number($("#exCont").attr("newNum")),
// 					"titleId":Number( $(this).parents("[titleId]").attr("titleId")),
					"opId":Number( $(this).parents("[opId]").attr("opId")),
			}
			$.ajax({
				url : 'exam_checkChange',
				type : 'post',
				dataType : 'json',
				data : params,
				traditional : true,
				success : checkChangeCallback
			});
		})
		
		function checkChangeCallback(data)
		{
// 			alert(data.status);
		}
		

		$(document).on("click","#examSubmit", function(){

// 			alert("SB");
			$.ajax({
				url : 'exam_submit',
				type : 'post',
				dataType : 'json',
				data : {},
				traditional : true,
				success : submitCallback
			});
		})
		
		function submitCallback(data)
		{
// 			alert("您答对了：" + data.score + "个题目" + "\n" + data.status);
// 			alert("HEHE");
			alert(data.status.substr(1));
			window.location.reload();
		}
		
		


		
		
		
		
		
		
		
		
		
		
		
		
		
		</script>
		
		
		
		
		
</div>
</layout:override>

<%@ include file="base.jsp"%>