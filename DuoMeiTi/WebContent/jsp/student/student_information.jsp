<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">
	<div class="mycontent">
	
		<form class="form-inline" method="POST" id="student_information_form">
			<br>
			<div class="form-group col-lg-offset-1">
				<label for="profilePhotoPath">用户头像:</label>
				<img src="<s:property value="profilePhotoPath"/>" width="100" height="100">
			</div>
			<div class="form-group col-lg-offset-2">
				<label for="profilePhotoPath">更改头像</label>
				<input type="file" name="profilePhotoPath" id="file_upload" placeholder="图片地址">
			</div>
			
			<br><br><br>
			
			<div class="form-group col-lg-offset-1">
				<label for="username">用&nbsp;&nbsp;户&nbsp;&nbsp;名</label>
				<input type="text" class="form-control" id="username" name="username" value="<s:property value="#session.username"/>" placeholder="<s:property value="#session.username"/>" readonly>
			</div>
			<div class="form-group col-lg-offset-1">
				<label for="fulName">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
				<input type="text" class="form-control" id="fullName" name="fullName" value="<s:property value="fullName"/>">
			</div>
			
			<br><br><br>
			
			<div class="form-group col-lg-offset-1">
				<label for="studentId">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
				<input type="text" class="form-control" id="studentId" name="studentId" value="<s:property value="studentId"/>">
			</div>
			<div class="form-group col-lg-offset-1">
				<label for="sex">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
				<s:select list="sexSelect" class="form-control" name="sex" id="sex"></s:select>
			</div>
			
			<br><br><br>
			
			<div class="form-group col-lg-offset-1">
				<label for="idCard">身份证号</label>
				<input type="text" class="form-control" id="idCard" name="idCard" value="<s:property value="idCard"/>">
			</div>
			<div class="form-group col-lg-offset-1">
				<label for="bankCard">银行卡号</label>
				<input type="text" class="form-control" id="bankCard" name="bankCard" value="<s:property value="bankCard"/>">
			</div>
			
			<br><br><br>
			
			<div class="form-group col-lg-offset-1">
				<label for="phoneNumber">联系方式</label>
				<input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="<s:property value="phoneNumber"/>">
			</div>
			<div class="form-group col-lg-offset-1">
				<label for="college">院系信息</label>
				<s:select list="collegeSelect" class="form-control" name="college" id="college"></s:select>
			</div>
			
			<br><br><br>
			
			<div class="form-group col-lg-offset-1">
				<label for="remark">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label>
				<input type="text" class="form-control" id="remark" name="remark" value="<s:property value="remark"/>">
			</div>
			<div class="form-group col-lg-offset-1">
				<label for="entryTime">入职时间</label>
				<input type="date" class="form-control" id="entryTime" name="entryTime" value="<s:property value="entryTime"/>">
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
			     
			     fd.append("username",$("#username").val());
			     fd.append("fullName",$("#fullName").val());
			     fd.append("studentId",$("#studentId").val());
			     fd.append("sex",$("#sex").val());
			     fd.append("idCard",$("#idCard").val());
			     fd.append("bankCard",$("#bankCard").val());
			     fd.append("college",$("#college").val());
			     fd.append("phoneNumber",$("#phoneNumber").val());
			     fd.append("remark",$("#remark").val());
			     fd.append("entryTime",$("#entryTime").val());
			     
			     $.ajax({
			    	 url:'student_information_change',
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
				alert("changed");
				location.reload();
			}
		</script>
	
	
	
	
	
	
	</div>
</layout:override>



<%@ include file="/jsp/student/base.jsp" %>