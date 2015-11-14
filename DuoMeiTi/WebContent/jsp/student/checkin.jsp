<%@ include file="/jsp/base/taglib.jsp" %>
<layout:override name="main_content">
<script type='text/javascript' src="/js/student/checkin.js"></script>
<div >
	<h>学生签到</h>
	<button type="button" class="btn btn-default"  style="margin:2px;" id="addbutton" onclick="checkin()">签&nbsp;&nbsp;到</button>
	<div>
	</div>
		<div class="student_table" id="studnetcheckintable">
			<%@ include file="/jsp/student/studentcheckintable.jsp" %>
		</div>
		<div>
			<%@ include file="/jsp/base/widgets/pagination.jsp" %>
		</div>
	</div>
</layout:override>
<script>
	paginationURL='/student/checkinrecord_date';
// 你可以定义pageAddtionalData变量，这个变量应该是json变量，这个变量可以直接通过ajax 在选择页码的时候传到后台
// 你应该重写下面这个函数，使其在回调的时候可以做你自己做的事情
	function requestPageCallback(data){
		document.getElementById("studnetcheckintable").innerHTML=data;
	}
</script>
<%@ include file="/jsp/student/base.jsp" %>