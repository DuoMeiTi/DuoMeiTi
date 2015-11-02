<%@ include file="/jsp/base/taglib.jsp"%>


<layout:override name="main_content">

<link rel="stylesheet" type="text/css" media="screen" href="/css/student/train.css" />

	<div class="mycontent">
		
		<div class="panel panel-primary">
			<div class="panel-heading center"><h2 style="font-weight:bold;">考试系统</h2></div>
  			<div class="panel-body">
  				<div class="item-info row">
  						<p class="col-sm-4">考试总时间： 30分钟</p><p class="col-sm-4">考试总分： 100分</p><button type="button" class="btn btn-primary col-sm-4" id="beginExam">开始答题</button>
  						<p class="col-sm-4">考试及格分： 60分</p><p class="col-sm-4">考试说明： 面向多媒体学生助教</p><p class="col-sm-4"></p>
  				</div>
  				
  			</div>
  			
		</div>
		
		<div class="alert alert-info" role="alert" id="alertInfo" style="display:none">
		</div>
		
		
		<div id="examCont"  style="display:none;">
			<div id="exCont" class="panel panel-default">
			<div class="panel-body ">
  			<s:iterator var="i" begin="0" end="qtitle.size()-1" step="1" status="index">
				<ul class="exam_margin" titleId=<s:property value="qtitle.get(#i).emId" />>
					<li class="margin_liTitle"><span><s:property value="#index.index+1"/>.&nbsp;</span><s:property value="qtitle.get(#i).emTitle" escape="false"/></li>
					<s:iterator value='{"A","B","C","D","E","F","G"}' var="j" begin="0" end="qoption.get(#i).size() - 1" step="1" status ="L">
					<li>
						<div opId=<s:property value="qoption.get(#i).get(#L.index).emId" /> class="opToback">
						<input type="checkbox" class="checks checkOption"><span class="">&nbsp;<s:property value="#j" />&nbsp;</span><s:property value="qoption.get(#i).get(#L.index).emOption" />
						</div>
					</li>
					</s:iterator>
				</ul>
			</s:iterator>
			<div class="center-block" style="max-width:300px;"><button type="button" class="btn btn-primary btn-lg btn-block center" id="examSubmit">我要交卷</button></div>
			</div>
<%-- 			<s:iterator var="i" begin="0" end="qtitle.size()-1" step="1" status="index"> --%>
<!-- 				<ul class="exam_margin" titleId=<s:property value="qtitle.get(#i).emId" />> -->
<%-- 					<li class="margin_liTitle"><s:property value="#index.index+1"/><span><s:property value="numsTitle[#i]" />.&nbsp;</span><s:property value="qtitle.get(#i).emTitle" /></li> --%>
					
<%-- 					<s:iterator var="j" begin="0" end="qoption.get(#i).size() - 1" step="1" > --%>
<!-- 					<li> -->
<!-- 						<div opId=<s:property value="qoption.get(#i).get(#j).emId" /> class="opToback"> -->
<%-- 						<input type="checkbox" class="checks checkOption"><span class="">&nbsp;<s:property value="letOption[#j]" />&nbsp;</span><s:property value="qoption.get(#i).get(#j).emOption" /> --%>
<!-- 						</div> -->
<!-- 						<div isRight=<s:property value="qoption.get(#i).get(#j).emCheck" /> style="display:none"></div> -->
<!-- 					</li> -->
<%-- 					</s:iterator> --%>
<!-- 			</ul> -->
<%-- 			</s:iterator> --%>
			
			</div>
		</div>
		<div id="showexamCont"></div>
		<script>
		
		var examHtml = $("#examCont").html();
		$("#examCont").remove();
		//begin exam
		$(document).on("click","#beginExam", function(){
			$("#beginExam").attr("disabled","disabled");
			$("#showexamCont").append(examHtml);
		})
		
		$(document).on("click","#test", function(){
			alert("lalala");
			var checkList = document.getElementsByClassName("checkOption");
			for(var i = 0; i < checkList.length; ++ i)
			{
				alert(checkList[i].checked);
			}
		})
		
		//exam submit
		
		$(document).on("click","#examSubmit", function(){
			var mergeList = new Array();
			$("ul.exam_margin").each(function(i) {
// 				alert($(this).attr("titleId"));
				mergeList.push($(this).attr("titleId"));
				var opToBackList = $(this).find(".opToback");
// 				alert(opToBackList.length);
				var count = 0;
				$(opToBackList).each(function(i){
					if($(this).children("input")[0].checked==true){
						count = count + 1;
					}
				})
// 				alert(count);
				mergeList.push(count);
				$(opToBackList).each(function(i){
					if($(this).children("input")[0].checked==true){
						mergeList.push($(this).attr("opId"));
					}
				});
			})
// 			alert(mergeList);
// 			optionIdList = [];
// 			var opTobackList = $(".opToback");
// 			$(opTobackList).each(function(i){
// 				if($(this).children("input")[0].checked==true){
// 					optionIdList.push($(this).attr("opId"));
					
// 				}
// 			});
			
			var params = {
					"mergeList" : mergeList,
			}
			$.ajax({
				url : 'exam_insert',
				type : 'post',
				dataType : 'json',
				data : params,
				traditional : true,
				success : InsertCallback
			});
			
		});
		function InsertCallback(data){
			if(data.status == "1"){
				//alert(data.score);
// 				$("#exCont").remove();
// 				alert("提交成功！  ");
				$("#examSubmit").attr("disabled","disabled");
				$("#alertInfo").hide();
				$("#alertInfo").html("考试分数为 " + data.score + " .");
				$("#alertInfo").show(500);
// 				$("#showexamCont").html("考试分数为 " + data.score + " .");
			}
		}
		
// 		$(document).on("click","#examSusbmit", function(){
// 			var opTobackList = document.getElementsByClassName("opToback");
// 			for(var i = 0; i < opTobackList.length; i++){
// 				alert(opTobackList[i].getElementsByClassName("checkOption")[0].checked);
// 				if(opTobackList[i].getElementsByClassName("checkOption")[0].checked == true){
// 					optionIdList.push($(opTobackList).attr("opId"));
// 					alert(optionIdList[0]);
// 				}
// 			}
// 			alert(optionIdList);

// 		})
		</script>
	</div>
</layout:override>

<%@ include file="base.jsp"%>