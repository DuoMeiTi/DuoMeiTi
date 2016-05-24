<%@ include file="/jsp/base/taglib.jsp"%>


<!-- <div class="modal fade" id="schedule-modal" tabindex="-1" role="dialog" -->
<!-- 	aria-labelledby="myModalLabel"> -->
<!-- 	<div class="modal-dialog" role="document"> -->
<!-- 		<div class="modal-content"> -->
<!-- 			<div class="modal-header"> -->
<!-- 				<button type="button" class="close" data-dismiss="modal" -->
<!-- 					aria-label="Close"> -->
<!-- 					<span aria-hidden="true">&times;</span> -->
<!-- 				</button> -->
<!-- 				<h4 class="modal-title">课表</h4> -->
<!-- 			</div> -->
<!-- 			<div class="modal-body"> -->

<!-- 				<iframe name="myFrame" frameborder="0" scrolling="no" -->
<!-- 					style="width: 100%;" height="200px" -->
<!-- 					src="/schedule/校部第一教学馆1-101(10301).html"></iframe> -->
<!-- 			</div> -->
<!-- 			<div class="modal-footer"> -->
<!-- 				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->

<!-- 检查记录 -->
<div class="modal fade" id="check-record-modal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h3 class="modal-title">填写周检查记录</h3>
			</div>
			<div class="modal-body">
			
				<h4>这是否是一个无问题的检查记录？<input type="checkbox" id="noProblem"/> </h4>				
				<textarea class="form-control" rows="4" id="checkdetail"></textarea>
				
				
				
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary"
					onclick="checkrecord_submit()">提交</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<script>
// 		$("#check-record-modal").on("show.bs.modal", function(e){
// 			$("#checkdetail").val("");
// 		})
</script>








<!-- 维修记录 -->
<div class="modal fade" id="repair-record-modal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">填写维修记录</h4>
			</div>
			<div class="modal-body">
				<div>
					设备：<span class="control-label" id="selectType"></span>&nbsp;&nbsp;&nbsp;&nbsp;资产编号：<span
						class="control-label" id="selectNum"></span>
				</div>
				<textarea class="form-control" rows="3" id="repairdetail"></textarea>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary"
					id="submit_repair_record">提交</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<script>
		$("#repair-record-modal").on("show.bs.modal", function(e){
			$("#repairdetail").val("");
		})
</script>
