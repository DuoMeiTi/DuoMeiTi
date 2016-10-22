<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">
	
	
	
<div class="mycontent">
	<div class="row">
		<div class="col-lg-6 col-lg-offset-3 classbuilding">
			<span>教学楼管理</span>
		</div>
	</div>
	<hr>
	<!-- Modal -->
	<div class="modal fade" id="teachBuildingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">新增教学楼</h4>
	      </div>
	      <div class="modal-body">
		      <form class="form-horizontal">
			        <div class="form-group">
						<label class="control-label col-sm-3" for="input_buildName_num">教学楼</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="input_buildName_num">
							<!-- <p class="help-block">字母，数字，汉字皆可</p> -->
						</div>
						<div style="text-align:center" class="col-sm-4 control-label">
							<span id="exist"></span>
						</div>
					</div>
				</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" onclick="addTeachBuilding()">确定新增</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<div class="row">
	  <div class="col-md-4">
			<button type="button" class="btn btn-primary btn-lg" onclick="add()">
		 		 新增教学楼
			</button>
	  </div>
	  
	  <div class="col-md-4">
			<button type="button" class="btn btn-danger btn-lg" id = "editAllTeachBuilding" 
				 	show="true">
		 	 	编辑所有教学楼
			</button>
	  </div>
	  
  	  <div class="col-md-4">
  	  
<!--   	  		<i class="glyphicon glyphicon-envelope"> -->
<%--   	  		共有<s:property value="builds.size()"/>个教学楼 --%>
<!--   	  		</i> -->
<!--   	  		<button type="button" class="btn btn-default btn-lg"  > -->
			<div style="font-size:20px;">
				共有<s:property value="builds.size()"/>个教学楼
			</div>
		 	 	
<!-- 			</button> -->
			
	  </div>
	  
	</div>
	<br/>
	
	<div class="classroomTableDiv""> 
		<table class="table table-bordered table-striped" id="building_table" >			
			<s:iterator begin="0" end="builds.size()-1" var="i" step="5">
				<tr >					
					<s:iterator  var="j" begin="0" end="@@min(builds.size()-#i-1,4)" step="1">
						<td
							build_name='<s:property value="builds.get(#i+#j).build_name"/>'
							build_id='<s:property value="builds.get(#i+#j).build_id"/>'
						>
							<a href="classroomManageNew?build_id=<s:property value="builds.get(#i+#j).build_id"/>&build_name=<s:property value="builds.get(#i+#j).build_name"/>" 
								class="btn btn-success btn-lg showTeachBuilding">
								<s:property value="builds.get(#i+#j).build_name"/>
							</a>
								
							<button class="btn btn-info  btn-lg  delete deleteTeachBuilding" style="display:none;">
								删除<s:property value="builds.get(#i+#j).build_name"/>
							</button>
						
						</td>
					</s:iterator>
				 </tr>
			</s:iterator>
			
			
		</table>
	</div>
	
</div>


<style>
	.teachbuilding-div {
		margin:10%;
		height:auto;
		line-height:100px;
		border:none;
		text-align:center;
		vertical-align:middle;
		font-size:2.0em;
		overflow:hidden;
	}
	
	a:hover{
		color:black;
	}
	
</style>

<script type='text/javascript' src="/js/admin/teachbuilding.js"></script>
<script>
</script>
</layout:override>


<%@ include file="base.jsp" %>