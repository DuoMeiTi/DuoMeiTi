<%@ include file="/jsp/base/taglib.jsp"%>


<layout:override name="main_content">
<link rel="stylesheet" type="text/css" media="screen" href="/css/student/train.css" />
<div class="mycontent">
		
		<div class="panel panel-primary">
			<div class="panel-heading center"><h2 style="font-weight:bold;">考试系统</h2></div>
  			<div class="panel-body">
  				<div class="item-info row">
  						<p class="col-sm-4">考试及格：需要答对80%及以上的题目</p>
  						<p class="col-sm-4">考试说明： 面向多媒体学生助教</p>  						
  				</div>  				
  			</div>
  			
		</div>
		
		<div class="alert alert-info" role="alert" id="alertInfo" style="display:none">
		</div>		
		
		<div id="examCont"  style="display:none;">
		
		
			<div id="exCont" class="panel panel-default" newNum=<s:property value="newNum" /> >
				<div class="panel-body ">
	  			<s:iterator var="i" begin="0" end="qtitle.size()-1" step="1" status="index">
					<ul class="exam_margin well" titleId=<s:property value="qtitle.get(#i).emId" /> >
						<li class="margin_liTitle"><span><s:property value="#index.index+1"/>.&nbsp;</span><s:property value="qtitle.get(#i).emTitle" escape="false"/></li>
						<s:iterator value='{"A","B","C","D","E","F","G"}' var="j" begin="0" end="qoption.get(#i).size() - 1" step="1" status ="L">
						<li>
							<div opId=<s:property value="qoption.get(#i).get(#L.index).emId" /> class="opToback">
							
								<input type="checkbox" class="checks checkOption "
							 	<s:property value="qstuOption.get(#i).get(#L.index)" />
								>
								
									<span class="">&nbsp;<s:property value="#j" />&nbsp;</span>
									<s:property value="qoption.get(#i).get(#L.index).emOption" />
							</div>
						</li>
						</s:iterator>
					</ul>
				</s:iterator>
				<div class="center-block" style="max-width:300px;">
				<button type="button" class="btn btn-primary btn-lg btn-block center" id="examSubmit">我要交卷</button></div>
				</div>
			
			</div>
		</div>
		<div id="showexamCont"></div>
		<script>
		
		var examHtml = $("#examCont").html();
		$("#examCont").remove();
		$("#showexamCont").append(examHtml);
		
		
// 		alert($("#exCont").attr("newNum"));
		$(document).on("click",".checkOption", function(){
			
			var checked = $(this).parent().children("input")[0].checked;
			
			
			if(checked)
			{
				alert("OK");
			}
			else alert("NO");
			params = {
					"checked":checked,
					"newNum": Number($("#exCont").attr("newNum")),
					"titleId":Number( $(this).parents("[titleId]").attr("titleId")),
					"opId":Number( $(this).parents("[opId]").attr("opId")),
			}
			$.ajax({
				url : 'exam_checkChange',
				type : 'post',
				dataType : 'json',
				data : params,
				traditional : true,
				success : checkChangeCallback
			});
		})
		
		function checkChangeCallback(data)
		{
			alert(data.status);
		}
		

		$(document).on("click","#test", function(){
			alert("lalala");
			var checkList = document.getElementsByClassName("checkOption");
			for(var i = 0; i < checkList.length; ++ i)
			{
				alert(checkList[i].checked);
			}
		})
		$(document).on("click","#examSubmit", function(){
			var params = {
						
			}
			$.ajax({
				url : 'exam_submit',
				type : 'post',
				dataType : 'json',
				data : params,
				traditional : true,
				success : submitCallback
			});
		})
		function submitCallback(data)
		{
			
		}
		
		
// 		//exam submit		
// 		$(document).on("click","#examSubmit", function(){
// 			var mergeList = new Array();
			
// 			$("ul.exam_margin").each(function(i) {
// 				mergeList.push($(this).attr("titleId"));
// 				var opToBackList = $(this).find(".opToback");
// 				var count = 0;
// 				$(opToBackList).each(function(i){
// 					if($(this).children("input")[0].checked==true){
// 						count = count + 1;
// 					}
// 				})
// 				mergeList.push(count);
// 				$(opToBackList).each(function(i){
// 					if($(this).children("input")[0].checked==true){
// 						mergeList.push($(this).attr("opId"));
// 					}
// 				});
// 			})

			
// 			var params = {
// 					"mergeList" : mergeList,
// 			}
// 			$.ajax({
// 				url : 'exam_insert',
// 				type : 'post',
// 				dataType : 'json',
// 				data : params,
// 				traditional : true,
// 				success : InsertCallback
// 			});
			
// 		});	
		
// 		function InsertCallback(data){
// 			if(data.status == "1"){

// 				$("#examSubmit").attr("disabled","disabled");
// 				$("#alertInfo").hide();
// 				$("#alertInfo").html("考试分数为 " + data.score + " .");
// 				$("#alertInfo").show(500);
// 			}			
// 		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		</script>
		
		
		
		
		
</div>
</layout:override>

<%@ include file="base.jsp"%>