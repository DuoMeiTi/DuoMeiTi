<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>



<div id="paginationTableDiv">

<%@ include file="/jsp/base/widgets/paginationTable.jsp" %>

</div>

<script> 






$(document).on("click","[requestPageNum]",  function(){

    $.ajax({
        url: '',
        type: 'post',
        dataType: 'json',
        data: {"currentPageNum": $(this).attr("requestPageNum") , "isAjaxTransmission":true, },
        success: _requestPageNumCallback
      });	
})

function _requestPageNumCallback(data) {
//	$("#ResourceFileTableDiv").html(data.file_path_html);
	$("#paginationTableDiv").html(data.paginationHtml);
	
	requestPageNumCallback(data);
}

</script>