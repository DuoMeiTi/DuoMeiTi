<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>



<div id="paginationTableDiv">

<%@ include file="/jsp/base/widgets/paginationTable.jsp" %>

</div>
<script> 


// var pageAddtionalData = {}
$(document).on("click","#firstPage",  function(){

    sendRequestPage(1);
})
$(document).on("click","#lastPage",  function(){

    sendRequestPage($(this).attr("totalPageNum"));
})

$(document).on("click","[requestPageNum]",  function(){

    sendRequestPage($(this).attr("requestPageNum"));
})
function sendRequestPage(currentPageNum) {
	var data = {"currentPageNum": currentPageNum , "isAjaxTransmission":true, };
	
	if(typeof(pageAddtionalData)!="undefined")
		data = $.extend({}, data, pageAddtionalData);

    $.ajax({
        url: '',
        type: 'post',
        dataType: 'json',
        data: data,
        success: _requestPageCallback
      });	
}

function _requestPageCallback(data) {
	$("#paginationTableDiv").html(data.paginationHtml);
	requestPageCallback(data);
}

</script>





