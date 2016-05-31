<%@ include file="/jsp/base/taglib.jsp"%>

<layout:override name="mycontent">



<div class="alert alert-success" role="alert" style="border-radius:0">

设置本学期第一周时，只需选择那一周的七天中任意一天即可
</div>

<div class="row">

	<div class="col-lg-6">
		<div class="input-group">			
			<span class="input-group-addon">设置本学期第一周的日期</span>
			<input class="form-control" type="date" id="firstWeekDate" 
				value="<s:property value='@util.Util@formatUtilDateToOnlyDate(date)'/>"
			 	>			
		</div>
	</div>
	<div class="col-lg-6">
		<button type="button" class="btn btn-primary" id="setFirstWeekDate">
			确定设置
		</button>
		<button type="button" class="btn btn-danger" id="setFirstWeekDateEmtpy">
			现在是假期
		</button>
		
	</div>
</div>





<script>


$(document).on( "click","#setFirstWeekDate", function(){
// 	alert("GG");
// 	alert("GG");
	$.ajax({
		url : 'globalSetting_save',
		type : 'post',
		dataType : 'json',
		data : 
		{
			"date":$("#firstWeekDate").val(),
		},
		success : function(data)
		{
			window.location.reload();	
		}
	});
})

$(document).on( "click","#setFirstWeekDateEmtpy", function(){

	$.ajax({
		url : 'globalSetting_save',
		type : 'post',
		dataType : 'json',
		data : 
		{
			"date":"",
		},
		success : function(data)
		{
			window.location.reload();	
		}
	});
})




</script>














</layout:override>










<%@ include file="/jsp/admin/HomepageModify/Base.jsp"%>

