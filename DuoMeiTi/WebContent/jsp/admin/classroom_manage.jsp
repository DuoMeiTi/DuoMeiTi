<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">

  <div id="classbuilding">
    XXX教学楼
  </div>
  <hr>
  <div class="form-horizontal">
        <div class="form-group">
            <label class="col-lg-2 control-label">教室查询：</label>
            <div class="dropdown">
                <button class="col-lg-2 btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                                      教室号
                  <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                  <li><a href="#">教室号</a></li>
                  <li><a href="#">负责人</a></li>
                </ul>
            </div>
            <div>
                <button class="col-lg-1 btn btn-primary" id="sc_button">查询</button>
            </div>
        </div>
  </div>
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
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
    </table>
  </div>    

</layout:override>

<%@ include file="/jsp/admin/base.jsp" %>