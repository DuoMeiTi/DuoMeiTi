<%@ include file="/jsp/base/taglib.jsp" %>


	<script src="/js/admin/tabIndent.js"></script>

<!-- 	<div class="mycontent"> -->
	<form class="form-horizontal traincss" id="train_form">
		<div class="row">
			<label for="trainContent" class="col-sm-3 control-label">培训内容</label>
			<textarea class="tabIndent col-sm-6" id="trainContent" rows="20" cols="20" value=""><s:property value="trContent"/></textarea>
			<script>tabIndent.renderAll();</script>
		</div>
		<div class="row">
			<label class="col-sm-3"></label>
			<div class="col-sm-6 trainbtn"><button type="button" class="btn btn-success" id="trUpdate">提交</button></div>
		</div>
	</form>
	
	<script>
	$(document).on("click","#trUpdate", function() {
		var trTextarea = $("#trainContent").val();
		var params = {
				"trContent" : trTextarea
		};
		//alert(trTextarea)
		$.ajax({
			url : 'training_infoUpdate',
			type : 'post',
			dataType : 'json',
			data : params,
			success : trUpdateCallback
		});
	})
	function trUpdateCallback(data){
		if(data.trStatus == "1"){
			alert("保存成功！ ");
		}
		else {
			alert("保存失败！ ");
		}
	}
	</script>
