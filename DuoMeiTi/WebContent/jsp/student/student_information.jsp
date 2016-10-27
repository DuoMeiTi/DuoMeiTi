<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">
	<div class="mycontent">
	
		<form class="form-inline" method="POST" id="student_information_form">
			<br>
			<div class="form-group col-lg-offset-1">
				<label for="profilePhotoPath">用户头像:</label>
				<img src="<s:property value="execute_currentStudentProfile.user.profilePhotoPath"/>" width="100" height="100">
			</div>
			<div class="form-group col-lg-offset-2">
				<label for="profilePhotoPath">更改头像</label>
				<input type="file" name="profilePhotoPath" id="file_upload" placeholder="图片地址">
			</div>
			
			<br><br><br>
			
			<div class="form-group col-lg-offset-1">
				<label for="username">用&nbsp;&nbsp;户&nbsp;&nbsp;名</label>
				<input type="text" class="form-control"  name="user.username" placeholder="<s:property value="#session.username"/>" readonly
					value="<s:property value="execute_currentStudentProfile.user.username"/>" >
			</div>
			
			
			<div class="form-group col-lg-offset-1">
				<label for="fulName">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
				<input type="text" class="form-control"   name="user.fullName" 
					value="<s:property value="execute_currentStudentProfile.user.fullName"/>">
			</div>
			
			<br><br><br>
			
			<div class="form-group col-lg-offset-1">
				<label for="studentId">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
				<input type="text" class="form-control"  name="studentId" 
					value="<s:property value="execute_currentStudentProfile.studentId"/>">
			</div>
			
			
			<div class="form-group col-lg-offset-1">
				<label for="user.sex">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
				<select name="user.sex"  class="form-control" >				
					<s:iterator value="sexSelect" var="cntSex">
					 		<option value='<s:property value="cntSex"/>'
					 		
					 			<s:if test="#cntSex ==execute_currentStudentProfile.user.sex">
					 				selected = "selected"
					 			</s:if>
					 		> 
					 		<s:property value="cntSex"/>
					 		</option>
					</s:iterator>					
				</select>
			</div>
			
			<br><br><br>
			
			<div class="form-group col-lg-offset-1">
				<label for="idCard">身份证号</label>
				<input type="text" class="form-control"   name="idCard" 
					value="<s:property value="execute_currentStudentProfile.idCard"/>">
			</div>
			<div class="form-group col-lg-offset-1">
				<label for="bankCard">银行卡号</label>
				<input type="text" class="form-control" name="bankCard" 
					value="<s:property value="execute_currentStudentProfile.bankCard"/>">
			</div>
			
			<br><br><br>
			
			<div class="form-group col-lg-offset-1">
				<label for="phoneNumber">联系方式</label>
				<input type="text" class="form-control"   name="user.phoneNumber" 
					value="<s:property value="execute_currentStudentProfile.user.phoneNumber"/>">
			</div>
			<div class="form-group col-lg-offset-1">
				<label for="college">院系信息</label>
				<s:select list="collegeSelect" class="form-control"
				
				 name="college" 
				 value="execute_currentStudentProfile.college"
				

				>
				</s:select>
			</div>
			
			<br><br><br>
			
			<div class="form-group col-lg-offset-1">
				<label for="remark">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label>
				<input type="text" class="form-control"  name="user.remark" 
					value="<s:property value="execute_currentStudentProfile.user.remark"/>">
			</div>
			<div class="form-group col-lg-offset-1">
				<label for="entryTime">入职时间</label>
				<input type="date" class="form-control"  name="entryTime" 
					value="<s:property value='@util.Util@formatSqlDateToOnlyDate(execute_currentStudentProfile.entryTime)'/>" />
			</div>
			
			<br><br><br>
			
			<div class="form-group col-lg-offset-4">
				<button type="button" class="btn btn-success col-lg-offset-4" id="change">确认修改</button>
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