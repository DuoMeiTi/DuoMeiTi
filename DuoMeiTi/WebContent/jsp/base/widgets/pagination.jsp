<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>



<div id="paginationTableDiv">

<%@ include file="/jsp/base/widgets/paginationTable.jsp" %>

</div>
<script> 


// var pageAddtionalData = {}
$(document).on("click","[requestPageNum]",  function(){
	
	var data = {"currentPageNum": $(this).attr("requestPageNum") , "isAjaxTransmission":true, };
	
	if(typeof(pageAddtionalData)!="undefined")
		data = $.extend({}, data, pageAddtionalData);

    $.ajax({
        url: '',
        type: 'post',
        dataType: 'json',
        data: data,
        success: _requestPageCallback
      });	
})

function _requestPageCallback(data) {
	$("#paginationTableDiv").html(data.paginationHtml);
	requestPageCallback(data);
}

</script>





