<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">
<div class="mycontent">








<div class="row">
    <div class="col-lg-6 col-lg-offset-3 classbuilding ">
      <span id="build_name"><s:property value="build_name"/></span>教学楼
    </div>
</div>


<hr>





<%@ include file="/jsp/classroom/classroomManage.jsp" %>

<%-- <%@ include file="/jsp/classroom/classroomManage.jsp" %> --%>

<script type="text/javascript" src="/js/classroom/classroomManage.js" charset="UTF-8"></script>
  
		
</div>

</layout:override>

<%@ include file="/jsp/admin/base.jsp" %>