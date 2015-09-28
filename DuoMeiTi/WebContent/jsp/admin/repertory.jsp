<%@ include file="/jsp/base/taglib.jsp"%>

<layout:override name="main_content">
	<div class="mycontent">

		<div><button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#rtModal" id="rtInsert" name="rtInsert">添加设备信息</button></div>
		<div class="modal fade" id="rtModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h2 class="modal-title" id="modal-title">添加设备信息</h2>
					</div>
					<div class="modal-body">
					<form class="form-inline well" id="repertory_form" method="post">
						<div class="modal-body">
							<div class="row">
							<div class="col-lg-6">
								<div class="input-group">
									<span class="input-group-btn">
										<button type="button" class="btn btn-default">设备类别<span style="color:red">*</span></button>
									</span>
									<s:select list="device" class="form-control" name="rtDevice" id="rtDevice"></s:select>
								</div>
								<div class="input-group">
									<span class="input-group-btn">
										<button type="button" class="btn btn-default">资产编号</button>
									</span>
									<input type="text" class="form-control" name="rtNumber" id="rtNumber" value="<s:property value="rtNumber"/>">
								</div>
								<div class="input-group">
									<span class="input-group-btn">
										<button type="button" class="btn btn-default">出厂日期</button>
									</span>
									<input type="date" class="form-control" name="rtProdDate" id="rtProdDate" value="<s:property value="rtProdDate"/>">
								</div>
								<div class="input-group">
									<span class="input-group-btn">
										<button type="button" class="btn btn-default">出厂号</button>
									</span>
									<input type="text" class="form-control" name="rtFactorynum" id="rtFactorynum" value="<s:property value="rtFactorynum"/>">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="input-group">
									<span class="input-group-btn">
										<button type="button" class="btn btn-default">设备名称<span style="color:red">*</span></button>
									</span>
									<s:select list="mainDevice" class="form-control" name="rtType" id="rtType1" style="display:none"></s:select>
									<s:select list="costDevice" class="form-control" name="rtType" id="rtType2" style="display:none"></s:select>
								</div>
								<div class="input-group">
									<span class="input-group-btn">
										<button type="button" class="btn btn-default">型号</button>
									</span>
									<input type="text" class="form-control" name="rtVersion" id="rtVersion" value="<s:property value="rtVersion"/>">
								</div>
								<div class="input-group">
									<span class="input-group-btn">
										<button type="button" class="btn btn-default">审批日期</button>
									</span>
									<input type="date" class="form-control" name="rtApprDate" id="rtApprDate" value="<s:property value="rtApprDate"/>">
								</div>
								<div class="input-group">
									<span class="input-group-btn">
										<button type="button" class="btn btn-default">使用状态<span style="color:red">*</span></button>
									</span>
									<s:select list="deviceStatus" class="form-control" name="rtDeviceStatus" id="rtDeviceStatus"></s:select>
								</div>
							</div>
							
							</div>
						</div>
					</form>
					</div>
					<div class="modal-footer">
							<button type="button" class="btn btn-primary" id="rtSave" mark="">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal" id="rtClose">关闭</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		<br/>
		
		
		<form class="form-inline" id="repertory_search" name="repertory_search" method="post">
			
			<div class="form-group">
				<label for="sDevice">设备类别</label>
				<s:select list="Device" class="form-control" name="sDevice" id="sDevice"></s:select>
			</div>
			<!-- <input type="text" class="" name="rtMainDevice" id="rtMainDevice" placeholder="设备"> -->
			<div class="form-group" style="display:none" id="main">
				<label for="sMainDevice">设备名称</label>
				<s:select list="mainDevice" class="form-control" name="sMainDevice" id="sMainDevice"></s:select>
			</div>
			<div class="form-group" style="display:none" id="cost">
				<label for="sCostDevice">设备名称</label>
				<s:select list="costDevice" class="form-control" name="sCostDevice" id="sCostDevice"></s:select>
			</div>
			
			<div class="form-group">
				<label for="sDeviceStatus">使用状态</label>
				<s:select list="deviceStatus" class="form-control" name="sDeviceStatus" id="sDeviceStatus"></s:select>
			</div>
		</form>
		<br/>

		<table class="table table-bordered table-hover" id="repertory_table">
			<tr class="active">
				<th>设备类型</th>
				<th>资产编号</th>
				<th>型号</th>
				<th>出厂日期</th>
				<th>审批日期</th>
				<th>出厂号</th>
				<th>使用状态</th>
				<th>删除</th>
			</tr>
			
			<s:iterator value="repertory_list" var="i" status="index">
				<tr class="success" rt_id="<s:property value="#i.rtId"/>" rt_device="<s:property value="#i.rtDevice"/>">
					<td class="click_me"> <s:property value="#i.rtType"/> </td>
					<td class="click_me"> <s:property value="#i.rtNumber"/> </td>
					<td class="click_me"> <s:property value="#i.rtVersion"/> </td>
					<td class="click_me"> <s:property value="#i.rtProdDate.toString()"/> </td>
					<td class="click_me"> <s:property value="#i.rtApprDate.toString()"/> </td>
					<td class="click_me"> <s:property value="#i.rtFactorynum"/> </td>
					<td class="click_me"> <s:property value="#i.rtDeviceStatus"/> </td>
					<td> <button type="button" class="btn btn-danger delete" >删除</button> </td>
				</tr>
			</s:iterator>
			
			
		</table>
		<div id="noResult"></div>
		
		<script type='text/javascript' src="/js/admin/repertory.js"></script>
		
	</div>
</layout:override>

<%@ include file="/jsp/admin/base.jsp"%>