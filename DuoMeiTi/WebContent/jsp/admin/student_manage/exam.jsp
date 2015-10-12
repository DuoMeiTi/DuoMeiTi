<%@ include file="/jsp/base/taglib.jsp" %>




<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  添加题目
</button>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
		
		题目描述
		<textarea class="form-control" rows="3">		
		</textarea>
		
		
		
		
		
		<div style="display:none" class="option">
			<form class="form-inline">
				  <div class="form-group">
				    <label for="exampleInputName2">选项:</label>
				    <input type="text" class="form-control" id="exampleInputName2" placeholder="Jane Doe">
				    <button  type="button"  class="btn btn-primary"> 移除</button>
				  </div>
			</form>
			


			


		</div>

      </div>
      <div class="modal-footer">
    	<button type="button" class="btn btn-primary" id="addOption">
			添加选项
		</button>
      	
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>


<br/>

<br/>



<table class="table table-bordered">
	<tr>
		<th class="active"></td>
		<th class="success">...</td>
		<th class="warning">...</td>
		<th class="danger">...</td>
		<th class="info">...</td>
	</tr>
	

	<tr>
		<td class="active">...</td>
		<td class="success">...</td>
		<td class="warning">...</td>
		<td class="danger">...</td>
		<td class="info">...</td>
	</tr>
  
</table>


<script>
	var id='A';
	$(document).on("click", "#addOption", function(){
		
		code = id.charCodeAt();
		
		id = String.fromCharCode(++code);

// 		alert(id);
		$(".modal-body").append($(".option").html());
	})
	
	
</script>

