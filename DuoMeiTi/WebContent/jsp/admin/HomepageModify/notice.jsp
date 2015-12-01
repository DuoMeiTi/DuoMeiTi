<%@ include file="/jsp/base/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>




<!-- <div class="modal fade" id="emModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> -->

<!--   <div class="modal-dialog modal-lg" role="document"> -->
<!--     <div class="modal-content"> -->
<!--       <div class="modal-header"> -->
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
<!--         <h2 class="modal-title" id="myModalLabel">添加题目</h2> -->
<!--       </div> -->
<!--       <div class="modal-body"> -->
<!-- 		<b>题目描述 </b> -->
<!-- 		<form id="exam_form" titleId> -->
			

<!-- 		</form> -->
		
<!--       </div> -->
<!--       <div class="modal-footer"> -->
		
<!--     	<button type="button" class="btn btn-primary" id="addOption">添加选项</button> -->
      	
<!--         <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> -->
<!--         <button type="button" class="btn btn-primary" id="examInsert" >保存</button> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->



<div class="modal fade" id="notice-modal" tabindex="-1" role="dialog" aria-labelledby="noticeAddModalLabel">


<!-- 	<div class="modal fade" id="notice-modal" tabindex="-1" role="dialog" -->
<!-- 	aria-labelledby="noticeAddModalLabel">aria-labelledby什么意思？？？？？  -->

	<div class="modal-dialog modal-lg" role="document" >
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h2 class="modal-title" id="myModalLabel">添加公告</h2>
			</div>
			<div class="modal-body">
			
			
			<form >
				<input style="visibility:hidden" id="submit_type" value="add" />
				
				<div class="form-inline form-group">
					<label class="control-label" for="notice_title">标题</label>
					
					<input type="text" class="form-control" id="notice_title" >			</div>

				<div class="form-inline form-group">
					<label class="control-label " for="notice_content">内容</label>
					
					
					
<!-- 						<textarea class="form-control"  id="notice_content" rows="9" cols="2" style="width: 400px"></textarea> -->
						
						
						
						
						
						<div id="notice_content">
							<%@ include file="/bootstrap-wysiwyg/editor.jsp" %>				
						</div>
						
						
						
						

					<span  hidden="true" id="hidden_id"></span>

				</div>
			</form>
			
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary" id="notice_add_btn" onclick="notice_add_submit()">提交</button>
			</div>
		</div>
	</div>
</div>
	
	<br />

	<form class="form-inline" action="notice_add" method="POST" id="notice_form">



	<button type="button" class="btn btn-primary" style="margin:2px;" id="add_button" onclick="notice_add()">增加新公告</button>
	</form>
	<br />




	<div class="alert alert-info" role="alert" id="alert_register_info"
		style="display: none">
		<br />
	</div>






<div id="notice_search_table">
	<table class="table table-bordered" id="notice_table">

		<tr class="active">
			<th>公告标题</th>
			<th>时间</th>
			<th>编辑</th>
			<th>删除</th>
		</tr>


		<s:iterator value="notice" var="n" status="i">
		
			<tr class="success" 
			notice_id="<s:property value="#n.id"/>"
			notice_content="<s:property value="#n.content"/>" 
			
			notice_title="<s:property value="#n.title"/>"
			notice_time="<s:property value="#n.time"/>" >
			
				<td><s:property value="#n.title" /></td>
				<td><s:property value="#n.time" /></td>
				<td>
					<button type="button" class="btn btn-primary " id="edit" 
					onclick="edit_notice(<s:property value="#i.index"/>)">编辑</button>
				</td>
				
				
				<td>
				
				
				<button type="button" class="btn btn-danger delete" 
				onclick="delete_notice(<s:property value="#i.index"/>)" >删除</button>
					
				</td>
			</tr>
		</s:iterator>




	</table>
</div>





	<script>
	
	
	function notice_add(){
		
		
		$(".editor").html("");
		
		
		
		$("#notice_title").val("");

// 		$("#notice_content").val("");
		
		$("#submit_type").attr("value", "add");
		$("#notice_add_btn").text("确定添加");
		$('#notice-modal').modal('show');
		
		
	}	
	
	function notice_add_submit(){
		
		var submit_type = $("#submit_type").attr("value");//新增教室的时候是add

		var title = $("#notice_title").val();

// 		var content = $("#notice_content").val();
		var content = $(".editor").html();
		
		var id =  $("#hidden_id").val();

		
		$.ajax({
			url : '/admin/HomepageModify/notice/notice_add',
			type : 'post',
			dataType : 'json',
			data : {//发送到服务器的数据
				//"notice_id" : noticeId,
				"title" : title,
				"content" : content,
				"submit_type" : submit_type,
				"id" : id
			},
			success : addNoticeCallback
		});
		
	}
	
	function addNoticeCallback(data) {
		
		if(data.status == "ok") {
			alert("添加成功");
			$('#notice-modal').modal('hide');
			window.location.href=window.location.href;  
			window.location.reload;
		}
	}
	//编辑
	function edit_notice(index) {

		var select_notice_title = $("#notice_search_table").find("tr:eq(" + (index + 1) + ") td:eq(0)").text();

		var select_notice_time = $("#notice_search_table").find("tr:eq(" + (index + 1) + ")").attr("notice_time");
		var select_notice_content = $("#notice_search_table").find("tr:eq("+(index+1) +")").attr("notice_content");
		var select_notice_id = $("#notice_search_table").find("tr:eq("+(index+1) +")").attr("notice_id");
		
		$("#notice_title").val(select_notice_title);
		
// 		$("#notice_content").val(select_notice_content);
		alert(select_notice_content);
		$(".editor").html(select_notice_content);
		
		$("#hidden_id").val(select_notice_id);
		
		$("#submit_type").attr("value", "update");
		$("#notice_add_btn").text("确定更新");
		$('#notice-modal').modal('show');
		
		dismiss();
	}
	
	//删除
	function delete_notice(index){
		var notice_id;
		var select_notice_id = $("#notice_search_table").find("tr:eq("+(index+1) +")").attr("notice_id");


		$.ajax({
			url : 'notice_delete',
			type : 'post',
			dataType : 'json',
			data : {
				"id" : select_notice_id
			},
			success : deleteCallback
		});
	}
	
		/* //处理删除操作
		 */

		function deleteCallback(data) {

			if (data.status == "0") {
				alert("删除成功");
				$('#notice-modal').modal('hide');
// 				window.location.href=window.location.href;  
				window.location.reload();
			} else if (data.status == "1") {
				animatedShow("你所删除的数据可能不存在");
			} else {
				alert("error");
			}

		}
	</script>

