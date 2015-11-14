<%@ include file="/jsp/base/taglib.jsp" %>
<layout:override name="main_content">
<div class="mycontent">
	<div >
	<h3>查看学生签到记录</h3>
	</div>
	<div class="form-group" >
	    			<label for="startTime">开始时间</label>
					<input id="startTime" type="date" value="2015-09-30"></input>
					<label for="endTime">结束时间</label>
					<input id="endTime" type="date" value="2015-10-01"></input>
					<button type="button" class="btn btn-default"  id="addbutton" onclick="query()">查询</button>
					<label for="changecheckinrule">&nbsp;&nbsp;修改签到时间</label>
					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#changetime" id="addbutton">修&nbsp;&nbsp;改</button>
	</div>
	<div >
		<div class="student_table" id="recordstable">
		<%@ include file="/jsp/admin/widgets/checkinrecordtable.jsp" %>
		</div>
		<div>
		<%@ include file="/jsp/base/widgets/pagination.jsp" %>
		</div>
	</div>
	<!-- 模态框（Modal） -->
<div class="modal fade" id="changetime" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
       <div class="modal-header">
		            <button type="button" class="close" 
		               data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
			            <h4 class="modal-title" id="myModalLabel">
			               		修改签到时间
			            </h4>
         		</div>
		        <form class="bs-example bs-example-form" id="change_time_form" role="form">
		         <div class="input-group">
			         <span class="input-group-addon">开始时间</span>
			          <input type="number" name="starthour" class="form-control" placeholder="小时&nbsp;&nbsp;1-24" id="starthour">
			      </div>
			      <br>
			      
			      <div class="input-group">
			         <span class="input-group-addon">开始时间</span>
			          <input type="number" name="startminute" class="form-control" placeholder="分钟&nbsp;&nbsp;0-59" id="startminute">
			      </div>
			      <br>
			      
			      <div class="input-group">
			         <span class="input-group-addon">结束时间</span>
			         <input type="number" name="endhour" class="form-control" placeholder="小时&nbsp;&nbsp;1-24" id="endhour">
			      </div>
			      <br>
			       <div class="input-group">
			         <span class="input-group-addon">结束时间</span>
			          <input type="number" name="ednminute" class="form-control" placeholder="分钟&nbsp;&nbsp;0-59" id="ednminute">
			      </div>
			      <br>
			        <div class="checkbox">
				          <label>
				            <input type="checkbox" name="isAM" id="isAM" value="true"> 上午
				          </label>
				          <label>
				          	<input type="checkbox" name="isPM" id="isPM" value="true"> 下午
				          </label>
		        	</div>
			   </form>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" id="savechange">
              		 保存
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
</div>
<script type='text/javascript' src="/js/admin/checkinrecord.js"></script>
<script>
	paginationURL='/student/checkinrecord_name';
// 你可以定义pageAddtionalData变量，这个变量应该是json变量，这个变量可以直接通过ajax 在选择页码的时候传到后台
// 你应该重写下面这个函数，使其在回调的时候可以做你自己做的事情
function requestPageCallback(data){
	document.getElementById("recordstable").innerHTML=data;
}
</script>
</layout:override>

<%@ include file="/jsp/admin/base.jsp" %>