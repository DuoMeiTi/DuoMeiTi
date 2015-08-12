<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">
  <div>
  </div>
  <div>
    <form class="navbar-form navbar-left" role="search">
                  教室查询：
      <div class="dropdown">
        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                               教室号
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
          <li><a href="#">教室号</a></li>
          <li><a href="#">负责人</a></li>
        </ul>
      </div>
      <button type="submit" class="btn btn-default">查询</button>
    </form>     
  </div>
</layout:override>

<%@ include file="/jsp/admin/base.jsp" %>