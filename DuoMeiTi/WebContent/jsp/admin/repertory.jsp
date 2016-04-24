<%@ include file="/jsp/base/taglib.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="/css/admin/repertory.css"/>
<layout:override name="main_content">

	<div class="mycontent">

		<div>
			<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#rtModal" id="rtInsert" name="rtInsert">添加设备信息</button>
			<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#rtModalImport" id="rtImport" name="rtImport">导入设备xls表</button>
			<button class="btn btn-primary btn-lg"  id="rtExportExcel" name="rtExportExcel">导出设备xls表</button>
			
			
		</div>
		<div class="modal fade" id="rtModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h2 class="modal-title" id="modal-title"></h2>
					</div>
					<div class="modal-body">
					<div class="alert alert-success" role="alert">若想要将此设备的状态设为教室，请在教室管理页面操作！</div>
					
					
					
					
					
					
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
			
			<div class="input-group"  style="display:none" id="rtReplacePeriodDiv">
				<span class="input-group-btn">
					<button type="button" class="btn btn-default" style="width:120px;">更换时间(天数)</button>
				</span>
				<input placeholder="" type="text" class="form-control"
				 name="rtReplacePeriod" 
				 id="rtReplacePeriod"
				 style="width:120px;" 
				 value="<s:property value="rtReplacePeriod"/>">									 
			</div>
			
			<div class="input-group" style="display:none" id="freq">
				<span class="input-group-btn">
					<button type="button" class="btn btn-default" >频点</button>
				</span>
				<input type="text" class="form-control" name="rtFreqPoint" id="rtFreqPoint" value="<s:property value="rtFreqPoint"/>">
			</div>
			<div class="input-group" style="display:none" id="filterclean">
				<span class="input-group-btn">
					<button type="button" class="btn btn-default" style="width:120px">过滤网更换时间</button>
				</span>
				<input type="text" class="form-control" name="rtFilterCleanPeriod" id="rtFilterCleanPeriod" style="width:120px" value="<s:property value="rtFilterCleanPeriod"/>">
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
		
		<div class="text-right" style="color:red">(*为必填项)</div>
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
		
		
		
		<div class="modal fade" id="rtModalImport">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h2 class="modal-title" id="modal-title">设备信息导入</h2>
					</div>
					<div class="modal-body">
					<div class="alert alert-danger" role="alert">导入Excel表格要求：
						<br>1.&nbsp; 从第一列开始每列设备字段分别为:
						
						<!--0-->
						<br>第0列：设备名称（必须与约定设备名称一致）、						
						<!--1-->
						<br>第1列：资产编号、						
						<!--2-->
						<br>第2列：型号、						
						<!--3-->
						<br>第3列：出厂日期（日期格式yyyy-mm-dd）、						
						<!--4-->
						<br>第4列：审批日期、
						<!--5-->
						<br>第5列：出厂号、
						<!--6-->
						<br>第6列：使用状态（备用、教室、维修、报废）、
						<!--7-->
						<br>第7列：教学楼、
						<!--8-->
						<br>第8列：教室号、
						<!--9-->
						<br>第9列：更换时间、
						<!--10-->
						<br>第10列：频点、
						<!--11-->
						<br>第11列：过滤网更换时间。
						
						<br>&nbsp;&nbsp;2.&nbsp;只读取Excel表格中第一个工作簿。第一行为字段名，第二行开始为数据内容。所有数据必须为文本格式，尤其是日期。 <br>&nbsp;&nbsp;3.&nbsp;若表中有空行，则空行之后的数据会被舍弃。</div>
					<form class="form-inline well" id="import_repertory" enctype="multipart/form-data" method="post">
						<div class="row">
							<div class="form-group">
								<input type="file" name="excelFile" id="excelFile">
							</div>
							<div class="form-group">
								<button type="button" class="btn btn-primary" id="rtUpload"><span class="glyphicon glyphicon-upload"></span>上传</button>
							</div>
						</div>
					</form>
					</div>
					<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal" id="rtClose">关闭</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		
		
		
		
		
		
<!-- 		watchStatusHistory modal-->
		<div class="modal fade" id="watchDeviceStatusHistoryModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h2 class="modal-title" id="modal-title">查看设备状态变化记录</h2>
					</div>
					
					<div class="modal-body">
						<div id="watchDeviceStatusHistoryDiv">
						</div>
					</div>


					<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal" id="rtClose">关闭</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
<!-- 		watchStatusHistory modal  END-->
		
		
		<br/>
		



		<div class="row">		
			  <div class="col-lg-4">
					<div class="input-group">			
						<span class="input-group-addon">设备</span>
						<select class="form-control" id="selectDeviceType"   style="width:200px;">			
							<option value=-1>
								所有设备
							</option>
							<s:iterator var = "i" begin="0" end="@util.Util@DeviceList.size() - 1" step="1">
								<option  value= '<s:property value = "#i"/>' >					
									<s:property value = "@util.Util@judgeDeviceType(@util.Util@DeviceList.get(#i))"/>
									-
									<s:property value = "@util.Util@DeviceList.get(#i)"/>
								</option> 
							</s:iterator>
						</select>			
					</div>
			  </div>		  
		  
			<div class="col-lg-4">
				<div class="input-group">			
					<span class="input-group-addon">设备状态</span>
					<select class="form-control" id="selectDeviceStatus"   style="width:200px;">			
						<option value="all">所有状态</option>
						<option >
							<s:property value="@util.Util@DeviceBackupStatus" />
						</option>
						<option  >
							<s:property value="@util.Util@DeviceClassroomStatus" />
						</option>
						<option  >
							<s:property value="@util.Util@DeviceRepairStatus" />
						</option>
						<option  >
							<s:property value="@util.Util@DeviceScrappedStatus" />
						</option>
				
					</select>			
				</div>
			</div>
		  	
		  	
		   <div class="col-lg-4">
				<div class="input-group">			
					<button class="btn btn-primary" id="searchDeviceButton"> 搜索 </button>
							
				</div>
		  </div>
		  	
		  
		  
		</div>   <!-- row -->







		
		
		
		
		

		<div id="repertoryTableDiv">
			<%@ include file="/jsp/admin/widgets/repertoryTable.jsp" %>
		</div>

		
		<div id="noResult"></div>
		
		<script type='text/javascript' src="/js/admin/repertory.js"></script>
		
	</div>
</layout:override>

<%@ include file="/jsp/admin/base.jsp"%>