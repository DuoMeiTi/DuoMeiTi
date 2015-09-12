<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">
<div class="mycontent">
  <div class="row">
    <div class="col-lg-6 col-lg-offset-3 classbuilding ">
      <s:property value="build_name"/>教学楼
    </div>
  </div>
  <hr>
  <form class="form-horizontal" id="classroom_manage" method="POST" action="classroom_manage">
    <div class="form-horizontal" id="search">
        <div class="form-group">
            <label class="col-lg-2 control-label">教室查询：</label>
            
            <div class="col-lg-2 btn-group">
              <button class="btn btn-default btn-lg dropdown-toggle" style="height:40px" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                教室号
                <span class="caret"></span>
              </button>
              <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                <li><a href="#">教室号</a></li>
                <li><a href="#">负责人</a></li>
              </ul>
            </div>

			<div class="col-lg-3">
				<input type="text" class="form-control" style="margin:3px;" aria-describedby="basic-addon1">
			</div>

			<div class="col-lg-1">
                <button class="btn btn-primary" style="margin:2px;" id="sc_button">查&nbsp;&nbsp;询</button>
            </div>
        </div>
    </div>
  </form>
  <div>
    <table class="table table-bordered table-striped">
      <thead>
        <tr>
            <th>教室号</th>
            <th>设备</th>
            <th>教室大小</th>
            <th>负责人</th>
            <th>管理</th>
        </tr>
      </thead>
      <tr>
        <td>201</td>
        <td>打印机</td>
        <td>20*40</td>
        <td>张三</td>
        <td><a href="classroom_detail" class="btn btn-info active">详&nbsp;&nbsp;细</a></td>
      </tr>
    </table>
  </div>
</div>

</layout:override>

<%@ include file="/jsp/admin/base.jsp" %>