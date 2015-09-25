<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">

 <h2 style="margin-left:20px;" > 修改密码</h2>
 <br/>
<form role="form" style="margin-left:20px;" action="" method="POST"  >
    
    <div class="form-group">
        <label for="input_new_password">输入新密码</label>
        <input type="password" class="form-control" id="input_new_password" name="input_new_password" placeholder="Enter password" style="width:170px;">
    </div>

    <div class="form-group ">
        <label for="repeat_new_password">确认新密码</label>
        <input type="password" class="form-control" id="repeat_new_password" name="repeat_new_password" placeholder="Enter password" style="width:170px;">
    </div>

    <button type="submit" class="btn btn-primary">确认</button>
</form>



</layout:override>

<%@ include file="/jsp/admin/base.jsp" %>