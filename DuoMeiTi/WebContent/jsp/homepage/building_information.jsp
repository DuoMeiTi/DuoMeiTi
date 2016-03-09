<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">
	<script type='text/javascript' src="/js/admin/teachbuilding.js"></script>
	<div class="mycontent">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3 classbuilding">
				<span>教学楼信息</span>
			</div>
		</div>
		<hr>
		
		<div class="classroomTableDiv""> 
			<table class="table table-bordered table-striped" id="building_table" >
				<s:iterator begin="0" end="builds.size()-1" var="i" step="5">
					<tr class="">
					
						<s:iterator  var="j" begin="0" end="@@min(builds.size()-#i-1,4)" step="1">
							<td
								build_name='<s:property value="builds.get(#i+#j).build_name"/>'
								build_id='<s:property value="builds.get(#i+#j).build_id"/>'
							>
								<a href="/classroom/classroomManageNew?build_id=<s:property value="builds.get(#i+#j).build_id"/>&build_name=<s:property value="builds.get(#i+#j).build_name"/>" class="btn btn-success btn-lg">
									<s:property value="builds.get(#i+#j).build_name"/>
								</a>
								
								<%-- <s:if test="makeUrl().contains(@util.Const@AdminRole)"> --%>
									<!-- <button class="btn btn-info  btn-primary delete">删除</button> -->
								<%-- </s:if> --%>
							
							</td>
						</s:iterator>
					 </tr>
				</s:iterator>
			</table>
		</div>
		
		
		<%-- <div class="container-fluid">
			<s:iterator value="builds" var="build" status="i">
				<div class="col-lg-4">
				
<!-- 				classroom_information -->
					<a href="/classroom/classroomManageNew?build_id=<s:property value="#build.build_id"/>&build_name=<s:property value="#build.build_name"/>">
						<div class="teachbuilding-div"><s:property value="#build.build_name"/></div>
					</a>
				</div>
			</s:iterator>
		</div> --%>
	</div>
	<style>
		.teachbuilding-div {
			margin:10%;
			height:auto;
			line-height:100px;
			border:1px solid black;
			text-align:center;
			vertical-align:middle;
			font-size:2.0em;
			overflow:hidden;
		} 
		
		a:hover{
			color:black;
		}
	</style>
	<!-- <script>
		$(".delete").click(function(){
			var temp = confirm("删除不可恢复！");
			if(temp == true){
				var id = $(this).closest("td").attr("build_id");
				$.ajax({
					url:'build_delete',
					type:'post',
					dataType:'json',
					data:{buildId:id},
					success:deleteCallBack
				});
			}
		})
		
		function deleteCallBack(data){
			if(data.status == 0){
				var td = $("td[build_id='"+data.buildId+"']");
				td.html("");
			}
			else alert("请先删除教室！");
		}
	</script> -->
</layout:override>


<%@ include file="base.jsp" %>