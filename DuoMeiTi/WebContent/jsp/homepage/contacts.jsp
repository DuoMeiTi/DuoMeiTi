<%@ include file="/jsp/base/taglib.jsp" %>
<layout:override name="main_content">       
<br/>
    <!-- 建立姓名和电话的输入表单 -->
    <center>
	<form class="form-inline" action="" method="POST" id="contacts_form" >
	  <div class="form-group" border-radius="10px">
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

	<!-- 建立姓名和电话的显示表格 -->
	<table class="table table-bordered" id="contacts_table">
	
		<tr class="active" >
			<th class="success"> 姓名 </th>
			<th class="warning"> 电话 </th>
			<th class="danger"> 删除</th>
		</tr>
		
		
		<s:iterator value="contacts" var="n" status="i" >  
			<tr class="success" contacts_id=<s:property value="#n.id"/> contacts_username=<s:property value="#n.username"/> contacts_telnumber=<s:property value="#n.telnumber"/>>
				<td class="success">   <s:property value="#n.username"/>    </td>
				<td class="warning">   <s:property value="#n.telnumber"/>   </td>
				<td class="danger"> <button type="button" class="btn btn-danger delete" onclick="delete_contacts(<s:property value="#i.index"/>)">删除</button> 
				 </td>
			</tr>
		</s:iterator>  
		
	</table>
	<!-- 增加通讯录和删除通讯录的前台 函数-->
	<script>
    function add_contacts(){
    	if ($("#username").val() == "" || $("#telnumber").val() == ""){
    		alert("姓名或电话不能为空!")
    		return false;
    	}
		var username = $("#username").val();
		var telnumber = $("#telnumber").val();
// 		var id = $("#")
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
			alert("添加成功!");
			window.location.href=window.location.href;  
			window.location.reload;
		}
	}
	
    function delete_contacts(index){
		var contacts_id;
		var select_contacts_id = $("#contacts_table").find("tr:eq("+(index+1) +")").attr("contacts_id");

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
	
    function deleteContactsCallback(data){
    	if (data.status == "0"){
    		alert("删除成功!");
    		window.location.href=window.location.href;
    		window.location.reload;
    	}
    	else if (data.status == "1"){
    		alert("你删除的数据可能不存在")
    	}
    	else {
    		alert("错误!");
    	}
    	
    }
	
	
	
	</script>
















</layout:override>
<%@ include file="/jsp/homepage/new_home_base.jsp" %>
