<%@ include file="/jsp/base/taglib.jsp" %>
<layout:override name="main_content">


<div class="mycontent">


	<div class="row">
		<div class="col-lg-6" >
		
				<select class="form-control" id="selectTeachBuilding"    >			
	
				<option value=-1> 所有教学楼 </option>		
				
				<s:iterator var = "i" begin="0" end="execute_teachBuildingList.size() - 1" step="1">	
					<option  value= '<s:property value = "execute_teachBuildingList.get(#i).build_id"/>'
						<s:if test="execute_selectTeachBuilding == execute_teachBuildingList.get(#i).build_id">
								selected="selected"
						</s:if> 
						>
											
						
						<s:property value = "execute_teachBuildingList.get(#i).build_name"/>
					</option>
				</s:iterator>
			</select>
			
		</div>
	</div>

	<br/>

			<div class="row">
			
			<div class="col-lg-12" id="classrooms">
					<table class="table table-bordered table-striped" id="classroom_table" >
						<s:iterator begin="0" end="execute_classroomList.size()-1" var="i" step="6">
							<tr>
								<s:iterator  var="j" begin="0" end="@@min(execute_classroomList.size()-#i-1,5)" step="1">
									<td>
										<button class="btn btn-info" 
												classroomId=<s:property value="execute_classroomList.get(#i+#j).id"/>
												>
											<s:property value="execute_classroomList.get(#i+#j).classroom_num"/>
										</button>
									</td>
								</s:iterator>
							</tr>
						</s:iterator>
					</table>
			</div>
		</div>
		
		
		
		
<!-- 维修记录 -->
<div class="modal fade" id="repairRrecordModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">填写维修记录</h4>
			</div>
			<div class="modal-body">
				<div>
					设备：<span class="control-label" id="deviceTypeShow"></span>
					&nbsp;&nbsp;&nbsp;&nbsp;
					资产编号：<span
						class="control-label" id="deviceNumberShow"></span>
				</div>
				<textarea class="form-control" rows="5" id="repairdetail"></textarea>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary"
					id="submit_repair_record">提交</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
		
	
		<div id="devicesAndRepairRecordsTableDiv">
		
			<%@ include file="/jsp/student/widgets/devicesAndRepairRecordsTable.jsp" %>
		</div>


	
	
	
	
	




</div>

<script>



$(document).on("change", "#selectTeachBuilding", function(){
	selectTeachBuilding = $("#selectTeachBuilding").val();
	
	window.location.href = "repairRecordManage?execute_selectTeachBuilding=" + selectTeachBuilding;
	
})

//用户点击时的相关信息：
var classroomId;
var deviceId;
$(document).on("click", "[classroomId]", function(){
// 	alert($(this).attr("classroomId"));
	classroomId = $(this).attr("classroomId");
	
	$.ajax({
		url : 'repairRecordManage_obtain',
		type : 'post',
		dataType : 'json',
		data : {"obtain_classroomId" : classroomId}, 
		success : function(data){
			$("#devicesAndRepairRecordsTableDiv").html(data.obtain_devicesAndrepairRecordsJsp)
		}
	})
})

$(document).on("click", ".repairRecordInput", function(){
	deviceId = $(this).attr("deviceId");
	
	var deviceType;
	var deviceNumber;
	deviceType = $(this).attr("deviceType");
	deviceNumber = $(this).attr("deviceNumber");
	
	$("#deviceTypeShow").html(deviceType);
	$("#deviceNumberShow").html(deviceNumber);


	$("#repairdetail").val("")
// 	alert(deviceId);
	$("#repairRrecordModal").modal("show");
	
})





$(document).on("click", "#submit_repair_record", function(){
// 	alert("FFF")
	$.ajax({
		url : 'repairRecordManage_save',
		type : 'post',
		dataType : 'json',
		data : {
				"save_classroomId" : classroomId,
				"save_deviceId" : deviceId,
				"save_detail" : $("#repairdetail").val(),
				}, 
		success : function(data){
			$("#devicesAndRepairRecordsTableDiv").html(data.save_devicesAndrepairRecordsJsp)
			
			$("#repairRrecordModal").modal("hide");
		}
	})

})


	
	
	
</script>








</layout:override>
<%@ include file="/jsp/student/base.jsp" %>