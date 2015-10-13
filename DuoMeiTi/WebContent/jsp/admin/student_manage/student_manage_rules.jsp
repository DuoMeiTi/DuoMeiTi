<%@ include file="/jsp/base/taglib.jsp" %>



<layout:override name="mycontent">



	<div class="mycontent">
	
	
	
	<br>
	<br>
	<center>
	<form  class="form-horizontal" method="post">
	<textarea rows="20" cols="150" id="text" name="text"><s:property value="textShow"/></textarea>
	<br>
	
	<button class="btn  btn-primary" onclick="rules_edit()" >确定</button>
	</form>
	
	<label for="name_id">还没做：</label>
	</center>
	<br>
	<br>
	
		
		
		
		
		
		
	</div>
	
	
</layout:override>

<script >
function rules_edit(){
	var text = $("#text").val();
	alert(text); 
	$.ajax({
		url : '/admin/student_manage/rule_edit',
		type : 'post',
		dataType : 'json',
		data : {//发送到服务器的数据
			"text" : text
		},
		success : addRulesCallback
	});
	 
}

function addRulesCallback(data){
	alert("ok");
	
}
</script>
<%@ include file="base.jsp" %>