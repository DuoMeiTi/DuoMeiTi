<%@ include file="/jsp/base/taglib.jsp" %>

<style type="text/css">
	.modal-header{
		width:70%;
		margin:0 auto;
	}
	h2{
		background:rgb(0,114,227);
		border-radius:5px;
		color:#fff;
		height:45px;
		display:block;
	}
	.modal-body{
		width:100%;
		background:rgb(238,238,238);
		border-radius:5px;
		padding:10px 5%;
	}
	.modal-footer{
		width:100%;
		background:rgb(238,238,238);
		border-top:none;
	}
	.form-group{
		margin-bottom:0px;
	}
</style>


<!-- Button trigger modal -->
<button type="button" id="addExam" class="btn btn-primary btn-lg">添加题目</button>

<!-- 文本编辑框 -->
<div id="emModal" style="display:none;">
	<div class="modal-header">
    	<h2 class="modal-title" id="myModalLabel">添加题目</h2>
	</div>
	
	<div class="modal-body">
		<b>题目描述 </b>
		<form id="exam_form" titleId>
			<div id="titleInput">
				<%@ include file="/jsp/admin/HomepageModify/UEditor/uediter.jsp"%>
				<!-- %@ include file="/bootstrap-wysiwyg/editor.jsp" %-->				
			</div>
			
			<br/>
			
			<span style="color:red">请勾选正确选项</span>
			
			<div style="display:none" class="option">
			 	<div class="form-inline form-group toc optionContent" id="optionLine">
			  		<input type="checkbox" class="optionCheck">
			    	<label for="optionInput">选项:</label>
			    	<textarea class="form-control optionInput" id="optionInput" rows="2" cols="100" placeholder="选项内容"></textarea>
			    	<button type="button" class="btn btn-primary" id="optionRemove"> 移除</button>
			 	</div>
			 </div>
			<br>
		</form>
	</div>

	<div class="modal-footer">
		<button type="button" class="btn btn-primary" id="addOption">添加选项</button>
    	<button type="button" class="btn btn-default" id="close" data-dismiss="modal">关闭</button>
    	<button type="button" class="btn btn-primary" id="examInsert" >保存</button>
	</div>
</div>




<br/>

<br/>

<div id="examTableDiv">
	<%@ include file="/jsp/admin/widgets/examTable.jsp" %>
</div>


<script type='text/javascript' src="/js/base/bootstrap-wysiwyg.js"></script>

<script>
	


	var optionHtml = $(".option").html();
	$(".option").remove();
	
	// clear modal
	function clearModal(){
// 		$("#titleInput").val("");
		//$(".editor").html("");
		UE.getEditor('editor').setContent("", '');
		$(".optionContent").each(function(){
			$(this).remove();
		})
	}
	var id='A';
	function addOption(){
		code = id.charCodeAt();
		id = String.fromCharCode(++code);
		$("#exam_form").append(optionHtml);
	}

	//remove
	$(document).on("click", "#optionRemove", function(){
		$(this).parents("#optionLine").remove();
	})
	
	//exam insert
	function insertTitle(){
		
		var params = getParams();
		$.ajax({
			url : 'training_examInsert',
			type : 'post',
			dataType : 'json',
			data : params,
			traditional : true,
			success : emInsertCallback
		});
	}
	
	function emInsertCallback(data){
		if(data.trStatus == "1") {
			$(document).find("#examTableDiv").html(data.exam_table);
			$('#emModal').css("display","none");
			alert("插入成功！ ");
		}
	}
	
	//delete
	var delete_emId
	$(document).on("click",".delete",function(){
		var temp = confirm("删除不可恢复！");
		if(temp == true){
			var temp = $(this).parents("tr");
			console.log(temp);
			delete_emId = temp.children().eq(0).text();
			console.log(delete_emId);
			$(temp).attr("emid", delete_emId);
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
		//alert("ok!!!!");
		if (data.trStatus == "0") {
			alert("删除数据不存在！ ");
		} else if (data.trStatus == "1") {
			$(document).find("tr[emid=" + delete_emId + "]").remove();
		}
	}
	
	//close
	$(document).on("click","#close",function(){
		clearModal();
		$('#emModal').css("display","none");
	})
	
	// open edit title
	$(document).on("click", ".edit", function(){
		clearModal();
		$('#emModal').find(".modal-title").html("编辑题目");
		var tr = $(this).parent().parent();
		var optionList = $(tr).find(".optionList")[0];

		optionList = $(optionList).find("div");
// 		$("#titleInput").val($(tr).find(".titleContent").html());
		//$("#titleInput .editor").html($(tr).find(".titleContent").html());
		UE.getEditor('editor').setContent($(tr).find(".titleContent").html(), '');
		$("#exam_form").attr("titleId", $(tr).attr("titleId"));
		$(optionList).each(function(){
			
			var isRight = $(this).attr("isRight");
			addOption();
			var cntOption = $(".optionContent:last");
			$(cntOption).find(".optionInput").val($(this).attr("optionText"));			
			if(isRight == "true")
			{				
				
				$(cntOption).find(".optionCheck").attr("checked",true);
			}
			else
			{
				$(cntOption).find(".optionCheck").attr("checked",false);
			}
		})
		$('#emModal').css("display","block");
		
	})
	
	
	
	
	// save edit Title
	function editTitle() {
		$('#emModal').css("display","none");
		var titleId = $("#exam_form").attr("titleId");		
		
		var params = getParams();
		params = $.extend(params, {"emId": titleId});
		$.ajax({
			url : 'training_examEdit',
			type : 'post',
			dataType : 'json',
			data : params,
			traditional : true,
			success : editTitleCallBack
		});
	}
	
	function editTitleCallBack(data){
		if(data.trStatus == "1") {
			$(document).find("#examTableDiv").html(data.exam_table);
			alert("更改成功！ ");
		}
	}
	
	
	
	
	
	// add option	
	$(document).on("click", "#addOption",addOption );
	
	$(document).on("click", "#testButton", function(){
		var checkList = document.getElementsByClassName("optionContent");
		for(var i = 0; i < checkList.length; ++ i)
		{
			alert(checkList[i].getElementsByClassName("optionCheck")[0].checked);
		}
	})
	
	
	
	
	$(document).on("click","#addExam",function(){
		$('#emModal').css('display','block');
		$('#emModal').find(".modal-title").html("新增题目");
		clearModal();
	})
	
	
	$(document).on("click", "#examInsert", function(){
		var judge = $('#emModal').find(".modal-title").html();
		if(judge == "编辑题目")
		{
			editTitle();
		}
		else insertTitle();
	})
	
	// from modal  get params
	function getParams()
	{
		//var emTextarea = $("#titleInput .editor").html();
		var emTextarea = UE.getEditor('editor').getContent();
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
		
		var params = {
				"emTitle" : emTextarea,
				"optionList" : optionList,
				"checkList" : checkList
		};
		return params;

	}
	
	
</script>

