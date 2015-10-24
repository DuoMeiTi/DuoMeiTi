<%@ include file="/jsp/base/taglib.jsp" %>
<layout:override name="main_content">       
<br/>
    
    <center>
	<form class="form-inline" action="" method="POST" id="contacts_form" >
	  <div class="form-group" border-radius="4px">
	    <label for="username">姓&nbsp;名</label>	    
	    <input type="text" class="form-control" size="10"  style="border-radius:10px!important;" id="username" >
	  </div>
	  
	  &nbsp;&nbsp;
	  <div class="form-group">
	    <label for="telnumber">电&nbsp;话</label>
	    <input type="text" class="form-control" style="border-radius:10px!important;" id="telnumber" >
	  </div>
	  &nbsp;&nbsp;
	  <button type="button" class="btn btn-default register" style="border-radius:10px!important;" id="contacts_add_btn" onclick="add_contacts()">添加</button>
	  </center>
	</form>	
	
	<br/>
		
	<div class="alert alert-info" role="alert" id="alert_register_info"  style="display:none">
	<br/>
	</div>	
	
	<table class="table table-bordered" id="contacts_table">
	
		<tr class="active" >
			<th class="success"> 姓名 </th>
			<th class="warning"> 电话 </th>
			<th class="danger"> 删除</th>
		</tr>
		
		
		<s:iterator value="telnumber_list" var="i" status="index" >  
			<tr class="success" contacts_id=<s:property value="#i.id"/> contacts_username=<s:property value="#i.username"/> contacts_telnumber=<s:property value="#i.telnumber"/>>
				<td class="success">   <s:property value="#i.username"/>    </td>
				<td class="warning">   <s:property value="#i.telnumber"/>   </td>
				<td class="danger"> <button type="button" class="btn btn-danger delete" onclick="delete_contacts(<s:property value="#index.index"/>)">删除</button> 
				 </td>
			</tr>
		</s:iterator>  
		
	</table>
	
	<script>
    function add_contacts(){
		var username = $("#username").val();
		var telnumber = $("#telnumber").val();
// 		alert(telnumber);
		$.ajax({
			url : '/contacts/contacts_add',
			type : 'post',
			dataType : 'json',
			data : {//发送到服务器的数据
				"username" : username,
				"telnumber" : telnumber
			},
			success : addContactsCallback
		});

	}
	
    function addContactsCallback(data) {
		
		if(data.status == "ok") {
			alert("ok");
			window.location.href=window.location.href;  
			window.location.reload;
		}
	}
	
    function delete_contacts(index){
		var contacts_id;
		var select_contacts_id = $("#contacts_table").find("tr:eq("+(index+1) +")").attr("contacts_id");
			    alert(select_contacts_id);

		$.ajax({
			url : 'contacts_delete',
			type : 'post',
			dataType : 'json',
			data : {
				"id" : select_contacts_id
			},
			success : deleteContactsCallback
		});
	}
	
    function deleteContactsCallback(){
    	if (data.status == "0"){
    		alert("ok");
    		window.location.href=window.location.href;
    		window.location.reload;
    	}
    	else if (data.status == "1"){
    		alert("你删除的数据可能不存在")
    	}
    	else {
    		alert("error");
    	}
    	
    }
	
	
	
	</script>
















</layout:override>
<%@ include file="/jsp/homepage/new_home_base.jsp" %>
