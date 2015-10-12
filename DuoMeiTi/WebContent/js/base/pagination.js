$(document).on("click","[requestPageNum]",  function(){
//	alert("YES");
	
    $.ajax({
        url: '',
        type: 'post',
        dataType: 'json',
        data: {"currentPageNum": $(this).attr("requestPageNum") ,},
        success: _requestPageNumCallback
      });

	
})
function _requestPageNumCallback(data) {
//	$("#ResourceFileTableDiv").html(data.file_path_html);
	$("#paginationDiv").html(data.paginationHtml);
	
	requestPageNumCallback(data);
}
