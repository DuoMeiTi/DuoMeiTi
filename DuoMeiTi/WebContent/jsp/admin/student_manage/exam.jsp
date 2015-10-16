<%@ include file="/jsp/base/taglib.jsp" %>




<!-- Button trigger modal -->
<button type="button" id="addExam" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#emModal">添加题目</button>
<!-- Modal -->
<div class="modal fade" id="emModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加题目</h4>
      </div>
      <div class="modal-body">
		题目描述
		<form id="exam_form">
			<textarea id="titleInput" class="form-control titleContent" rows="3" style="resize: vertical"></textarea>
			<br>
			<span style="color:red">请勾选正确选项</span>
			
			<div style="display:none" class="option">
			
			  <div class="form-inline form-group toc" id="optionLine">
			  	<input type="checkbox" class="optionCheck">
			    <label for="optionInput">选项:</label>
			    <textarea class="form-control" id="optionInput" rows="1" cols="45" placeholder="选项内容"></textarea>
			    <button type="button" class="btn btn-primary" id="optionRemove"> 移除</button>
			  </div>
			  
			</div>
			
			<br>
		</form>
		
      </div>
      <div class="modal-footer">
      	<button type="button" class="btn btn-primary" id="testButton">test</button>
		
    	<button type="button" class="btn btn-primary" id="addOption">添加选项</button>
      	
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="examInsert">保存</button>
      </div>
    </div>
  </div>
</div>


<br/>

<br/>

<div id="examTableDiv">
	<%@ include file="/jsp/admin/widgets/examTable.jsp" %>
</div>




<script>
	var id='A';
	$(document).on("click", "#addOption", function(){
		code = id.charCodeAt();
		id = String.fromCharCode(++code);
		$("#exam_form").append($(".option").html());
		$("#emModal .toc:last").addClass("optionContent");
	})
	
	$(document).on("click", "#testButton", function(){
		alert("lalala");
		var checkList = document.getElementsByClassName("optionContent");
		for(var i = 0; i < checkList.length; ++ i)
		{
			alert(checkList[i].getElementsByClassName("optionCheck")[0].checked);
		}
	})
	
	$(document).on("click","#addExam",function(){
		$("#titleInput").val("");
		
		$(".optionContent").each(function(){
			$(this).remove();
		})
		
	})
	//remove
	$(document).on("click", "#optionRemove", function(){
		$(this).parents("#optionLine").remove();
	})
	//examinsert
	$(document).on("click", "#examInsert", function(){
		var emTextarea = $("#titleInput").val();
		var optionList = new Array();
		$(".optionContent #optionInput").each(function(){
			optionList.push($(this).val());
		})
		
		var findList = document.getElementsByClassName("optionContent");
		var checkList = new Array();
		for(var i = 0; i < findList.length; ++ i)
		{
			var temp = findList[i].getElementsByClassName("optionCheck")[0].checked;
			checkList.push(temp);
		}
// 		alert(optionList);
// 		alert(checkList);
		var params = {
				"emTitle" : emTextarea,
				"optionList" : optionList,
				"checkList" : checkList
		};
		$.ajax({
			url : 'training_examInsert',
			type : 'post',
			dataType : 'json',
			data : params,
			traditional : true,
			success : emInsertCallback
		});
	})
	function emInsertCallback(data){
		if(data.trStatus == "1") {
			$(document).find("#examTableDiv").html(data.exam_table);
			$('#emModal').modal('hide');
			alert("插入成功！ ");
		}
	}
	//delete
	var delete_emId
	$(document).on("click",".delete",function(){
		var temp = confirm("删除不可恢复！");
		if(temp == true){
			var temp = $(this).parents("tr");
			delete_emId = temp.children().eq(0).text();
			$(temp).attr("emid", delete_emId);
			alert(delete_emId);
			$.ajax({
				url : 'training_examDelete',
				type : 'post',
				dataType : 'json',
				data : {"emId" : delete_emId,},// {"后台",""}
				success : emDeleteCallback
			});
		}
	})
	function emDeleteCallback(data){
		alert("ok!!!!");
// 		if (data.trStatus == "0") {
// 			alert("删除数据不存在！ ");
// 		} else if (data.trStatus == "1") {
// 			$(document).find("tr[emid=" + delete_emId + "]").remove();
// 		}
	}
</script>


