<%@ include file="/jsp/base/taglib.jsp" %>




<layout:override name="main_content">
	<link href="/datepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
	<script type='text/javascript' src="/datepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="/datepicker/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript">
	window.onload = function () {
	    $('.form_date').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
	}
	</script>

	<div class="classbuilding">
		<span>301</span>&nbsp;&nbsp;&nbsp;&nbsp;
		<span>负责人:</span>
		<span class="director-span">楼主</span>
	</div>
	<hr>

	<!-- Modal -->
	<div class="modal fade" id="schedule-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">课表</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="dtp_input2" class="col-md-2 control-label">选择日期</label>
						<div style="float:left;position:relative;" class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
							<input class="form-control" size="16" type="text" value="" readonly> 
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-remove"></span>
							</span>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
						<input type="hidden" id="dtp_input2" value="" />
						<button type="button" style="float:right" class="btn btn-primary">查询</button>
						<br /><br /><br />
					</div>
					<table class="table table-bordered">
						<thead>
							<tr><td>1-2节</td><td>3-4节</td><td>5-6节</td><td>7-8节</td><td>9-10节</td></tr>
						</thead>
						<tbody>
							<tr><td>C语言</td><td>Java语言</td><td>PHP语言</td><td>C#语言</td><td>C++语言</td></tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="check-record-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">填写周检查记录</h4>
				</div>
				<div class="modal-body">
					<textarea class="form-control" rows="3"></textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">提交</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="repair-record-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
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
					<textarea class="form-control" rows="3"></textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">提交</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="btn-div">
		<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#schedule-modal">查看课表</button>&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#check-record-modal">填写周检查记录</button>
	</div>
	<div class="detail-div">
		<div class="device">
			<ul>
				<li>
					<div style="margin-bottom:5px"><label class="control-label">计算机&nbsp;</label><span><button type="button" class="btn btn-primary btn-sm"  data-toggle="modal" data-target="#repair-record-modal">维修</button></span></div>
					<table class="table device-table-bordered">
						<thead>
							<tr><td>资产编号</td><td>型号</td><td>名称</td><td>出厂号</td><td>出厂日期</td><td>审批日期</td></tr>
						</thead>
						<tbody>
							<tr><td>资产编号</td><td>型号</td><td>名称</td><td>出厂号</td><td>出厂日期</td><td>审批日期</td></tr>
						</tbody>
					</table>
				</li>
				<li>
					<div style="margin-bottom:5px"><label class="control-label">中央控制器&nbsp;</label><span><button type="button" class="btn btn-primary btn-sm"  data-toggle="modal" data-target="#repair-record-modal">维修</button></span></div>
					<table class="table device-table-bordered">
						<thead>
							<tr><td>资产编号</td><td>型号</td><td>名称</td><td>出厂号</td><td>出厂日期</td><td>审批日期</td></tr>
						</thead>
						<tbody>
							<tr><td>资产编号</td><td>型号</td><td>名称</td><td>出厂号</td><td>出厂日期</td><td>审批日期</td></tr>
						</tbody>
					</table>
				</li>
				<li>
					<div style="margin-bottom:5px"><label class="control-label">投影&nbsp;</label><span><button type="button" class="btn btn-primary btn-sm"  data-toggle="modal" data-target="#repair-record-modal">维修</button></span></div>
					<table class="table device-table-bordered">
						<thead>
							<tr><td>资产编号</td><td>型号</td><td>名称</td><td>出厂号</td><td>出厂日期</td><td>审批日期</td></tr>
						</thead>
						<tbody>
							<tr><td>资产编号</td><td>型号</td><td>名称</td><td>出厂号</td><td>出厂日期</td><td>审批日期</td></tr>
						</tbody>
					</table>
				</li>
			</ul>
		</div>
		<div class="record">
			<ul>
				<li>
					<label class="control-label">周检查记录：</label>
					<table class="table device-table-bordered">
						<thead>
							<tr><td>检查人</td><td>教室状况</td><td>检查时间</td></tr>
						</thead>
						<tbody>
							<tr><td>检查人</td><td>教室状况</td><td>检查时间</td></tr>
							<tr><td>检查人</td><td>教室状况</td><td>检查时间</td></tr>
							<tr><td>检查人</td><td>教室状况</td><td>检查时间</td></tr>
						</tbody>
					</table>
				</li>
				<li>
				<label class="control-label">维修记录：</label>
					<table class="table device-table-bordered">
						<thead>
							<tr><td>维修人</td><td>维修状况</td><td>维修时间</td></tr>
						</thead>
						<tbody>
							<tr><td>维修人</td><td>维修状况</td><td>维修时间</td></tr>
							<tr><td>维修人</td><td>维修状况</td><td>维修时间</td></tr>
							<tr><td>维修人</td><td>维修状况</td><td>维修时间</td></tr>
						</tbody>
					</table>
				</li>
			</ul>
		</div>
	</div>
	<style type="text/css">
		ul {
			padding:10px;
			list-style:none;
		}
		
		.device-table-bordered {
			border: 2px solid black;
		}
		
		.device-table-bordered>tbody>tr>td, .device-table-bordered>tbody>tr>th, .device-table-bordered>thead>tr>td, .device-table-bordered>thead>tr>th {
		    border: 2px solid black;
		}
		
		/* .roomname-div {
			margin:0 auto;
			margin-top:30px;
			width:40%;
			text-align:center;
			height:50px;
		    line-height:50px;  
		    overflow:hidden;   
			border:2px solid black;
		} */
		
		.btn-div {
			margin: 20px 200px 20px 60%;
		}
		
		.detail-div {
			margin: 0;
		}
		
		.device {
			position:relative;
			width:60%;
			float:left;
		}
		
		.record {
			width:40%;
			float:right;
		}
	</style>
</layout:override>

<%@ include file="base.jsp" %>