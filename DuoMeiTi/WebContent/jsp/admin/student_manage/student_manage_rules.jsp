<%@ include file="/jsp/base/taglib.jsp" %>



<layout:override name="mycontent">



	<div class="mycontent">
	
	
	
	<br>
	<br>
	<center>
	<form  class="form-horizontal" method="post">
	<textarea rows="20" cols="150" id="ruleText" name="ruleText"><s:property value="textShow"/></textarea>
	<br>
	
	<button id="ruleEditButton"class="btn  btn-primary"  type="button">确定</button>
	</form>
	
	
	</center>
	<br>
	<br>
	
		
		
		
		
		
		
	</div>


	
	<script >

$(document).on("click", "#ruleEditButton", function(){
	var ruleText = $("#ruleText").val();
	alert(ruleText); 
	$.ajax({
		url : '/admin/student_manage/ruleEdit',
		type : 'post',
		dataType : 'json',
		data : 
		{
			"ruleText" : ruleText,
		},
		success : addRulesCallback
	});
	 
});
function addRulesCallback(data){
	alert("ok");
	
}
</script>
	
	
	
	
</layout:override>

<%@ include file="base.jsp" %>