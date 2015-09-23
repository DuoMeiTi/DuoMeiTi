<%@ include file="/jsp/base/taglib.jsp"%>



<div class="modal fade" id="noticeAddModal" tabindex="-1" role="dialog"
	aria-labelledby="noticeAddModalLabel"><!--aria-labelledby什么意思？？？？？  -->
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">添加公告</h4>
			</div>
			<div class="modal-body">
			
			
			<form class="form-horizontal">
				<input style="visibility:hidden" id="submit_type" value="add" />
				<div class="form-group">
					<label class="control-label col-sm-3" for="notice_title">标题</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="notice_title">
						<!-- <p class="help-block">字母，数字，汉字皆可</p> -->
					</div>
					<!-- <div style="text-align:center" class="col-sm-4 control-label">
						<span id="exist"></span>
					</div> -->
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="input_principal_student_id">内容</label>
					<div class="col-sm-5">
						<textarea class="form-control"  id="notice_content" rows="3" cols="1"></textarea>
						<!-- <input type="" class="form-control" id="input_principal_student_id" oninput="disable_add_btn()"> -->
					</div>
					<!-- <div style="text-align:center" class="col-sm-4 control-label">
						<span id="input_principal_student_name">lz</span>
					</div> -->
					<!-- <div class="col-sm-3">
						<button class="btn btn-primary" onclick="query_stu_name()">查询姓名</button>
					</div> -->
				</div>
			</form>
			
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary">提交</button>
			</div>
		</div>
	</div>
</div>


<layout:override name="main_content">
	<br />

	<form class="form-inline" action="notice_add" method="POST "
		id="notice_form">

		<button type="button" class="btn btn-primary " data-toggle="modal"
			data-target="#noticeAddModal" id="notice_add">增加新公告</button>
	</form>
	<br />




	<div class="alert alert-info" role="alert" id="alert_register_info"
		style="display: none">
		<br />
	</div>




	<table class="table table-bordered" id="notice_table">

		<tr class="active">
			<th>公告标题</th>
			<th>时间</th>
			<th>操作</th>
		</tr>


		<s:iterator value="notice_list" var="i" status="index">
			<tr class="success" notice_id=<s:property value="#i.id"/>>
				<td><s:property value="#i.title" /></td>
				<td><s:property value="#i.time" /></td>
				<td>
					<button type="button" class="btn btn_primary " id="edit">查看/编辑</button>
				</td>
				<td>
					<button type="button" class="btn btn_danger delete">删除</button>
				</td>
			</tr>
		</s:iterator>




	</table>






	<script>
		var notice_id;
		//处理删除操作
		$(document).on("click", ".delete", function() {
			notice_id = $(this).parents("tr").attr("notice_id");
			// 	    alert(deleted_user_id);
			// 	    alert(typeof user_id);

			$.ajax({
				url : 'notice_delete',
				type : 'post',
				dataType : 'json',
				data : {
					"notice_id" : notice_id,
				},
				success : deleteCallback
			});

		});

		function deleteCallback(data) {

			if (data.status == "0") {
				animatedShow("删除成功");
				$(document).find("tr[notice_id=" + notice_id + "]").remove();
			} else if (data.status == "1") {
				animatedShow("你所删除的数据可能不存在");
			} else {
				alert("error");
			}

		}
		//处理查看编辑操作
		$(document).on("click", "#edit", function() {
			notice_id = $(this).parents("tr").attr("notice_id");
			// 	    alert(deleted_user_id);
			// 	    alert(typeof user_id);

			$.ajax({
				url : 'notice_edit',
				type : 'post',
				dataType : 'json',
				data : {
					"notice_id" : notice_id,
				},
				success : editCallback
			});

		});

		function editCallback(data) {

			if (data.status == "0") {
				animatedShow("修改成功");
				//			$(document).find("tr[notice_id=" + notice_id + "]").remove();
			} else if (data.status == "1") {
				animatedShow("操作失败");
			} else {
				alert("error");
			}

		}
		$(document).on("click", "#notice_add", function() {
			var params = $('#notice_form').serialize(); //利用jquery将表单序列化

			$.ajax({
				url : 'notice_save',
				type : 'post',
				dataType : 'json',
				data : params,
				success : noticeAddCallback
			});

		});

		function animatedShow(text) {
			$("#alert_register_info").hide();
			$("#alert_register_info").text(text);
			$("#alert_register_info").show(500);
		}
		function noticeAddCallback(data) {
			if (data.status == "0") {
				$("#notice_table tr:first").after(data.added_notice_html);

				var cnt = $(document).find("#notice_table tr:eq(1)");
				$(cnt).children().eq(0).text(data.title);
				$(cnt).children().eq(1).text(data.time);

				//         	alert(data.user_id);

				$(cnt).attr("notice_id", data.notice_id);

				animatedShow("添加成功");
			} else if (data.status == "1") {
				animatedShow("æ³¨åç¨æ·åæèå¯ç ä¸ºç©º");
			} else if (data.status == "2") {
				animatedShow("æ³¨åç¨æ·åéå¤");
			} else {
				alert("error with status" + data.status);
			}

		}

		$('#myModal').on('shown.bs.modal', function() {
			$('#myInput').focus()
		})
	</script>

</layout:override>










<%@ include file="/jsp/homepage/base.jsp"%>

