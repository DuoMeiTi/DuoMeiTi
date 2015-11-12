<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">
<div class="mycontent">

<!-- Modal -->
<div class="modal fade" id="classroom-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="myModalLabel">添加教室</h4>
		</div>
		
		<div class="modal-body">
			<form class="form-horizontal">
				<input style="visibility:hidden" id="submit_type" value="add" />
				<div class="form-group">
					<label class="control-label col-sm-3" for="input_classroom_num">教室号</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="input_classroom_num">
					</div>
					<div style="text-align:center" class="col-sm-4 control-label">
						<span id="exist"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="input_principal_student_id">负责人</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="input_principal_student_id" oninput="disable_add_btn()">
					</div>
					<div style="text-align:center" class="col-sm-4 control-label">
						<span id="input_principal_student_name">lz</span>
					</div>

				</div>
			</form>
		</div>
	      
		<div class="modal-footer">
			<button class="btn btn-success" onclick="query_stu_name()">查询负责人姓名</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			<button type="button" class="btn btn-primary" id="add_classroom_btn" onclick="classroom_submit()">确定添加</button>
		</div>
	</div>
	</div>
</div>






<div class="row">
    <div class="col-lg-6 col-lg-offset-3 classbuilding ">
      <span id="build_name"><s:property value="build_name"/></span>教学楼
    </div>
</div>


<hr>





<%@ include file="/jsp/classroom/classroomManage.jsp" %>

<%-- <%@ include file="/jsp/classroom/classroomManage.jsp" %> --%>

<script type="text/javascript" src="/js/classroom/classroomManage.js" charset="UTF-8"></script>
  
		
</div>

</layout:override>

<%@ include file="/jsp/admin/base.jsp" %>