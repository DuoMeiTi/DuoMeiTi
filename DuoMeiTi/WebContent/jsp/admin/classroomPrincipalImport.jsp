<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="mycontent">


<div class="mycontent">


<form method="post" action="" enctype="multipart/form-data">
		
		
		<br/>
		
		<div class="alert alert-success" role="alert" style="border-radius:5px;"  >
			<p>选择相应的教室， 批量设置一些教室的教室负责人</p>			
		</div>	

		<div class="row">
			
			<div class="col-lg-4">
				<div class="input-group">					
					<span class="input-group-addon">请输入学生的学号：</span>						
					<input type="text" class="form-control" id="studentNumberInput"/>
				</div> 
			</div>
			
			<div class="col-lg-4">
				<button class="btn btn-primary" type="button" id="determineSetting">确定设置</button>
			</div>
			
		</div>
		
		<br/>
		
		
		
		
		<div class="row">
		
			<div class="col-lg-4">
				<div class="input-group">					
					<span class="input-group-addon">教学楼</span>
					<select class="form-control" id="selectTeachBuilding"   >			
						<option value=-1>	
							所有教学楼
						</option>
									
						<s:set name="teachBuildingList" value="@util.Util@getAllTeachBuildingList()" > 
						</s:set>
				
						<s:iterator var = "i" begin="0" end="#teachBuildingList.size() - 1" step="1">							
							
							<option  value= '<s:property value = "#teachBuildingList.get(#i).build_id"/>'
							
								<s:if test="selectTeachBuilding == #teachBuildingList.get(#i).build_id">
									selected="selected"
								</s:if>
							 >					
								<s:property value = "#teachBuildingList.get(#i).build_name"/>
							</option> 				
							
							
						</s:iterator>				
					</select>
					
					<script>
// 						$("#selectTeachBuilding").find("[value="+ <sproselectTeachBuilding+ "]").attr("")
					</script>
					
				</div>
			</div>
		
		</div>
		<div class="row">
			
			<div class="col-lg-12" id="classrooms">
				<%@ include file="/jsp/admin/widgets/classroomPrincipalCheckbox.jsp" %>
			</div>
			
			
		</div>
		
		
		
</form>	
	
<script>

$(document).on("change","#selectTeachBuilding",function(){
	var selectTeachBuilding = $("#selectTeachBuilding").val();
	
	selectTeachBuilding = parseInt(selectTeachBuilding);
	
	window.location.href = "classroomPrincipalImport?selectTeachBuilding=" + selectTeachBuilding;
	
// // 	alert("COCOOOO");
// // 	selectTeachBuilding
// 	$.ajax({  
//         url:'/admin/batchImport/batchImport_classroomPrincipalChangeBuilding' ,  
//         type: "POST",  
//         data: {
//         	"selectTeachBuilding":selectTeachBuilding,
//         },           
//         success: function(data) {
//         	$("#classrooms").html(data.classroomcheckbox);
//         }
//    });  
});




//全选
$(document).on("click","#checkAll",function(){
	$("input[name='checkOne']").each(function(){
		$(this).prop("checked",$("#checkAll").prop("checked"));
	})
});

//单选
$(document).on("click","input[name='checkOne']",function(){
	var checkOnes = $("input[name='checkOne']");
	var count = 0;
	for(var i=0;i<checkOnes.size();i++){
		if($(checkOnes[i]).prop("checked") == false){
			$("#checkAll").prop("checked",false);
			break;
		}
		else{
			count++;
		}
	}
	if(count == checkOnes.size()){
		$("#checkAll").prop("checked",true);
	}
});




$(document).on("click","#determineSetting",function(){

// 	alert("FFF");
	classroomList = new Array();
	var checkOnes = $("input[name='checkOne']");
	for(var i=0;i<checkOnes.size();i++)
	{
		if($(checkOnes[i]).prop("checked") == true)
		{
			classroomList.push( parseInt($(checkOnes[i]).attr("id")) );
		}
	}
	console.log(classroomList);
	
	
	
	
	$.ajax({  
        url:'/admin/batchImport/batchImport_classroomPrincipalDetermineSetting' ,  
        type: "POST",  
        traditional: true,
        data: {
        	"studentNumber":$("#studentNumberInput").val(),
        	"classroomIdList": classroomList,
        },           
        success: function(data) {
//         	alert(data.status)
        	var cmd = data.status.substr (0, 1);
        	var info = data.status.substr(1);
        	alert(info);
        	if(cmd != "0")
        	{
        		return ;
        	}
      	
        	
        	window.location.reload();

        }
   });  
	


	
	
})

	





	
	

</script>



		
		
		
		
		
</div>
</layout:override>



<%@ include file="/jsp/admin/batchImport.jsp" %>