<%@ include file="/jsp/base/taglib.jsp" %>
<layout:override name="main_content">


<div class="mycontent">

	<div class="alert alert-success" role="alert">
		只能在选择的班的开始的前一个小时内签到，也就是对于以下值班时间段的对应可签到时间段为：
		
		
		<s:iterator var= "i" begin="0" end="@util.Util@dutyPeriodBeginList.size() - 1" >
			<p>			
				
				
				对于值班时间段
					<s:property value="@util.Util@dutyPeriodBeginList.get(#i)"/>
					~
					<s:property value="@util.Util@dutyPeriodEndList.get(#i)"/>
				 只能在时间段
				 
				 	<s:property value="@util.Util@dutyCheckinPeriodBeginList.get(#i)"/>
					~
					<s:property value="@util.Util@dutyCheckinPeriodEndList.get(#i)"/>
				内签到
				
			</p>
		</s:iterator>
		
	</div>

	
	<div class="well">
		<h3>
		<s:if test="status.charAt(0) == '0' ">
		
			<button type="button" class="btn btn-primary" id="addbutton" onclick="checkin()">
				<s:property value= "status.substring(1)"/>
<%-- 					<s:property value= "@util.Util@dutyPeriodList.get(currentPeriodId)"/> --%>
				
			</button>	
		</s:if>
		
<%-- 		<s:elseif test="status.charAt(0) == '2'"> --%>
<%-- 			<s:property value= "status.substring(1)"/> --%>
<%-- 		</s:elseif> --%>
		<s:elseif test="status.charAt(0) != '0'">
			<s:property value= "status.substring(1)"/>
		</s:elseif>
		
</h3>

	</div>
	


	
		<div class="student_table" id="studnetcheckintable">
			<%@ include file="/jsp/admin/widgets/checkinTable.jsp" %>
		</div>
<!-- 		<div> -->
<%-- 			<%@ include file="/jsp/base/widgets/pagination.jsp" %> --%>
<!-- 		</div> -->
	</div>
	
	
<!-- 	<script type='text/javascript' src="/js/student/checkin.js"></script> -->
<script>

function checkin(){
	
// 	alert("FFF")
	$("#addbutton").attr("disabled", "disabled");
	$.ajax({
        url: '/student/checkinManage_checkin',
        type: 'post',
        dataType: 'json',
        data: { },
		success:function(data)
		{
			window.location.reload();

		}
	
      });


}





	// 你可以定义pageAddtionalData变量，这个变量应该是json变量，这个变量可以直接通过ajax 在选择页码的时候传到后台
	// 你应该重写下面这个函数，使其在回调的时候可以做你自己做的事情
	
// 		function sendRequestPage(currentPageNum) {
// 		var data = {"currentPageNum": currentPageNum , "isAjaxTransmission":true, };
// 		if(typeof(pageAddtionalData)!="undefined")
// 			data = $.extend({}, data, pageAddtionalData);
// 	    $.ajax({
// 	        url: paginationURL,
// 	        type: 'post',
// 	        dataType: 'json',
// 	        data: data,
// 	        success: _requestPageCallback,
// 	        error: requesterror
// 	      });	
// 	}
// 		function requestPageCallback(data){
// 			document.getElementById("studnetcheckintable").innerHTML=data.newtablestring;
// 		}
</script>



</layout:override>
<%@ include file="/jsp/student/base.jsp" %>