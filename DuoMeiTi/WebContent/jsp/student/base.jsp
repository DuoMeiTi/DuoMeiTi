<%@ include file="/jsp/base/taglib.jsp" %>



<layout:override name="menu_list">

	<div class="menu-list">
	    <a href="/student/dutyManage" class="menu-button-middle"><span class="glyphicon glyphicon-blackboard">&nbsp;选班</span></a>
	    <a href="/student/repairRecordManage" class="menu-button-middle"><span class="glyphicon glyphicon-blackboard">&nbsp;维修记录管理</span></a>
	    
	    <a href="/student/classroom/" class="menu-button-middle"><span class="glyphicon glyphicon-blackboard">&nbsp;教室管理</span></a>
	    <a href="/student/classroomDevice/rules" class="menu-button-middle"><span class="glyphicon glyphicon-blackboard">&nbsp;查看规章制度</span></a>
	    <a href="/student/train" class="menu-button-middle"><span class="glyphicon glyphicon-blackboard">&nbsp;技术培训</span></a>
	    <a href="/student/exam" class="menu-button-middle"><span class="glyphicon glyphicon-pencil">&nbsp;考试系统</span></a>
	    
	    <a href="/student/checkinManage" class="menu-button-middle"><span class="glyphicon glyphicon-blackboard">&nbsp;签到</span></a>
	    
	    <a href="/student/student_information" class="menu-button-middle"><span class="glyphicon glyphicon-blackboard">&nbsp;个人信息</span></a>
	    <a href="/student/modify_password" class="menu-button-middle"><span class="glyphicon glyphicon-blackboard">&nbsp;修改密码</span></a>
	    <a href="/logout" class="menu-button-middle"><span class="glyphicon glyphicon-blackboard">&nbsp;安全退出</span></a>
	    
	     
		<s:if test="#session.role.equals(@util.Const@StudentToAdminRole)">
            <a href="/admin/" class="menu-button-middle"><span class="glyphicon glyphicon-share-alt">&nbsp;跳到管理员</span></a>
        </s:if>
		
	    <div class="menu-blank"></div>
	</div>

 </layout:override>
 








<%@ include file="/jsp/base/user_base.jsp" %>

