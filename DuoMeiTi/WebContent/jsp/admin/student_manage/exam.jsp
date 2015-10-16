<%@ include file="/jsp/base/taglib.jsp" %>




<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#emModal">添加题目</button>
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
			<div style="display:none" class="option">
			
			  <div class="form-inline form-group" id="optionLine">
			    <label for="optionInput">选项:</label>
			    <textarea class="form-control toc" id="optionInput" rows="1" cols="35" placeholder="选项内容"></textarea>
			    <button type="button" class="btn btn-primary" id="optionRemove"> 移除</button>
			  </div>
			  
			</div>
			<div class="form-inline">
				<label for="optionTrue">正确选项</label>
				<input type="text" class="form-control" id="optionTrue" placeholder="请输入正确选项大写字母">
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



<table class="table table-bordered" id="exam_table">
	<tr class="active">
		<th>编号</th>
		<th>题目</th>
		<th>选项</th>
		<th>操作</th>
	</tr>
	
	<s:iterator var="i"  begin="0" end="qtitle.size()-1" step="1">
		<tr class="danger">
			<td><s:property value="qtitle.get(#i).emId" /></td>
			<td><s:property value="qtitle.get(#i).emTitle" /></td>
			<td>
			<s:iterator var="j" begin="0" end="qoption.get(#i).size() - 1" step="1">
				<s:property value="qoption.get(#i).get(#j).emOption" />;
			</s:iterator>
			</td>
			<td><button type="button" class="btn btn-success edit">编辑</button>&nbsp;<button type="button" class="btn btn-danger delete">删除</button></td>
		</tr>
	</s:iterator>
  	
</table>


<script>
	var id='A';
	$(document).on("click", "#addOption", function(){
		code = id.charCodeAt();
		id = String.fromCharCode(++code);
		$("#exam_form").append($(".option").html());
		$("#emModal .toc:last").addClass("optionContent");
	})
	
	$(document).on("click", "#testButton", function(){
		alert($(".titleContent").val());
		$(".optionContent").each(function(){
			alert($(this).val());
		})
	})
	
	$(document).on("click", "#optionRemove", function(){
		$(this).parents("#optionLine").remove();
	})
	
	$(document).on("click", "#examInsert", function(){
		var emTextarea = $("#titleInput").val();
		var optionList = new Array();
		var optionTrue = $("#optionTrue").val();
		$(".optionContent").each(function(){
			optionList.push($(this).val().toString());
		})
		alert(optionList);
		var params = {
				"emTitle" : emTextarea,
				"optionList[]" : JSON.stringify(optionList),
				"emTrue" : optionTrue
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
			alert("插入成功！ ");
		}
	}
	
</script>


