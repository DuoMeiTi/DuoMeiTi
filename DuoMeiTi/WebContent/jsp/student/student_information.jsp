<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">
	<div class="mycontent">
	
		<form class="form-inline well" method="POST" id="student_information_form">
			<br>
			
			<div class="row">
				<div class="col-lg-6 ">
					<label>用户头像</label>
					<img src="<s:property value="execute_currentStudentProfile.user.profilePhotoPath"/>" width="100" height="100">
				</div>
				<div class="col-lg-6">
					<br/>
					<input type="file" name="profilePhotoPath" id="file_upload" placeholder="图片地址">
					<br/>
					<label for="profilePhotoPath">更改头像</label>
				</div>
			</div>
			<br/>
			
			<div class="row">
				<div class="col-lg-6 ">	
					<div class="input-group">
						<div class="input-group-addon">用 户名</div>
						<input type="text" class="form-control"  name="user.username" placeholder="<s:property value="#session.username"/>" readonly
							value="<s:property value="execute_currentStudentProfile.user.username"/>" >
					</div>
				</div>
				<div class="col-lg-6">
				
					<div class="input-group">
						<div class="input-group-addon">姓名</div>
						<input type="text" class="form-control"   name="user.fullName" 
							value="<s:property value="execute_currentStudentProfile.user.fullName"/>">
					</div>
				</div>
			</div>
			
			<br/>
			
			<div class="row">
				<div class="col-lg-6">
					<div class="input-group">
						<div class="input-group-addon">学 号</div>
						<input type="text" class="form-control"  name="studentId" 
							value="<s:property value="execute_currentStudentProfile.studentId"/>">
							
					</div>
				</div>
				<div class="col-lg-6">
					<div class="input-group">
						<div class="input-group-addon">性 别</div>
						<s:select list="@util.Const@sexSelect" class="form-control" name="user.sex" value="execute_currentStudentProfile.user.sex">
						</s:select>
						
					</div>
				</div>
			</div>
			
			<br/>
			
			<div class="row">
				<div class="col-lg-6"> <div class="input-group">
					
					<div class="input-group-addon">身份证号</div>
					<input type="text" class="form-control"   name="idCard" 
						value="<s:property value="execute_currentStudentProfile.idCard"/>">
				</div> </div>
				<div class="col-lg-6"> <div class="input-group">
					<div class="input-group-addon">银行卡号</div>
					<input type="text" class="form-control" name="bankCard" 
						value="<s:property value="execute_currentStudentProfile.bankCard"/>">
				</div> </div>
			</div>
			
			<br/>
			
			<div class="row">
				<div class="col-lg-6">	<div class="input-group">			 
					<div class="input-group-addon">联系方式</div>
					<input type="text" class="form-control"   name="user.phoneNumber" 
						value="<s:property value="execute_currentStudentProfile.user.phoneNumber"/>">
					
				</div> </div>
				<div class="col-lg-6"> <div class="input-group">			 
					<div class="input-group-addon">院系信息</div>
						<s:select list="@util.Const@collegeSelect" class="form-control"
						
						 name="college" 
						 value="execute_currentStudentProfile.college"
						
		
						>
						</s:select>
					
				</div> </div>
			</div>
			
			<br/>
			
			<div class="row">
				<div class="col-lg-6">  <div class="input-group">		
				
					<div class="input-group-addon">备 注</div>
					<input type="text" class="form-control"  name="user.remark" 
						value="<s:property value="execute_currentStudentProfile.user.remark"/>">
				
				</div> </div>
				<div class="col-lg-6">  <div class="input-group">		
						
					<div class="input-group-addon">入职时间</div>
					<input type="date" class="form-control"  name="entryTime" 
						value="<s:property value='@util.Util@formatSqlDateToOnlyDate(execute_currentStudentProfile.entryTime)'/>" />
						
				</div> </div>
			</div>
			
			<br/>
			
			<div class="form-group col-lg-offset-4">
				<button type="button" class="btn btn-success  " id="change">确认修改</button>
			</div>
			
		</form>
	
		<script>
			
			$(document).on('click',"#change",function(){
				
				 var f_id = $("#file_upload").val();
			     var fd = new FormData();
			     
			     
			     
			     if(f_id.length != 0){
			        fd.append("file", document.getElementById('file_upload').files[0]);
			     }
			     
			     var studentInfo = $("#student_information_form").serializeArray();			     
			     
			     var perfix = "modify_newStudentProfile.";
			     for(var i = 0; i < studentInfo.length; ++ i)
			     {			    	 
			    	 
			    	 var pair = studentInfo[i];
		    		 fd.append(perfix + pair.name, pair.value);
			     }
			     
			     
			     $.ajax({
			    	 url:'student_information_modify',
			    	 type:'post',
			    	 datatype:'json',
			    	 data:fd,
			    	 async:true,
			    	 contentType:false,
			    	 processData:false,
			    	 success:studentChangeCallBack
			     });
			     
			})
			
			function studentChangeCallBack(data)
			{
// 				alert("修改成功");
				location.reload();
			}
		</script>
	
	
	
	
	
	
	</div>
</layout:override>



<%@ include file="/jsp/student/base.jsp" %>