<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">
<div class="mycontent">


<div class="row">

  <div class="col-lg-3">
		<div class="input-group">			
			<span class="input-group-addon">设备</span>
			<select class="form-control" id="selectDevice"   >
			
				<option value=-1>
					所有设备
				</option>
				
				<s:iterator var = "i" begin="0" end="@util.Util@DeviceList.size() - 1" step="1">
				
					<option  value= '<s:property value = "#i"/>' >
					
						<s:property value = "@util.Util@judgeDeviceType(@util.Util@DeviceList.get(#i))"/>
						-
						<s:property value = "@util.Util@DeviceList.get(#i)"/>
					</option> 
				
				</s:iterator>
			</select>			
		</div>
		
  </div>
 


  <div class="col-lg-3">
		<div class="input-group">			
			<span class="input-group-addon">维修人</span>
			<input class="form-control" type='text' placeholder="填入维修人姓名" id="inputRepairman">
		</div>
		
  </div>


  <div class="col-lg-3">
  
		<div class="input-group">
					
			<span class="input-group-addon">教学楼</span>
			<select class="form-control" id="selectTeachingBuildingName"   >
			
				<option value=-1>	
					所有教学楼
				</option>
				
				
				
				<s:set name="teachBuildingList" value="@util.Util@getAllTeachBuildingList()" > 
				</s:set>
				
				<s:iterator var = "i" begin="0" end="#teachBuildingList.size() - 1" step="1">
				
					<option  value= '<s:property value = "#teachBuildingList.get(#i).build_name"/>' >
						<s:property value = "#teachBuildingList.get(#i).build_name"/>
					</option> 
				
				</s:iterator>
				
			</select>
		</div>
		
  </div>
	
	<div class="col-lg-3">
		<div class="input-group">
			<span class="input-group-addon">教室</span>
			<input class="form-control" type='text' palceholder="填写教室号" id="inputClassroom" >
		</div>
  </div>

</div>   <!-- row -->


<br/>
<div class="row">

  <div class="col-lg-4">
		<div class="input-group">			
			<span class="input-group-addon">记录填写起始时间</span>
			<input class="form-control" type='date' id="inputBeginDate" >
		</div>
  </div>
 


  <div class="col-lg-4">
		<div class="input-group">
					
			<span class="input-group-addon">记录填写终止时间</span>
			<input class="form-control" type='date' id="inputEndDate">
		</div>
		
  </div>


  <div class="col-lg-4">
  		
  		<button class="btn btn-primary" id="searchButton">
  			检索维修记录
  		</button>
  		<button class="btn btn-primary" id="exportButton">
  			导出xls表格
  		</button>
  </div>
	


</div>   <!-- row -->


<br/>

	<div id="repairRecordTableDiv">
		<%@ include file="/jsp/admin/widgets/repairRecordTable.jsp" %>
	</div>


</div>

<script>

	$(document).on("click", "#searchButton", function() {

// 		alert($("#selectTeachingBuildingName").val());
		
		var selectTeachingBuildingName = $("#selectTeachingBuildingName").val();
		if (selectTeachingBuildingName == "-1") selectTeachingBuildingName =""; 
		
		
		$.ajax({  
	        url:'/admin/repairRecord_search' ,  
	        type: "POST",  
	        data: {
	        	"selectDevice":$("#selectDevice").val(),
	        	"inputRepairman":$("#inputRepairman").val(),
	        	"selectTeachingBuildingName":selectTeachingBuildingName,
	        	"inputClassroom":$("#inputClassroom").val(),
	        	"inputBeginDate":$("#inputBeginDate").val(),
	        	"inputEndDate":$("#inputEndDate").val(),
	        },  
	          
	        success: function(data) {
	        	
// 	        	alert(data.repairRecordTable)
	        	$("#repairRecordTableDiv").html(data.repairRecordTable);
	        	

	        }

	   });  
	}) 
	
	$(document).on("click", "#exportButton",function(){
		
		$.ajax({
			url:'/admin/repairRecord_export',
			type: "POST",
			data: {
				"selectDevice":$("#selectDevice").val(),
	        	"inputRepairman":$("#inputRepairman").val(),
	        	"selectTeachBuilding":$("#selectTeachBuilding").val(),
	        	"inputClassroom":$("#inputClassroom").val(),
	        	"inputBeginDate":$("#inputBeginDate").val(),
	        	"inputEndDate":$("#inputEndDate").val(),
			},
			
			success: function(data){
				$("#repairRecordTableDiv").html(data.repairRecordTable);
				window.open(data.exportPath);
				
			}
			
		});
	})

</script>



</layout:override>

<%@ include file="/jsp/admin/base.jsp"%>






