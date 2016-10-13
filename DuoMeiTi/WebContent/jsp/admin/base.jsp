<%@ include file="/jsp/base/taglib.jsp" %>



<layout:override name="menu_list">

	<div class="menu-list">
	    <a href="/admin/classroom/" class="menu-button-middle"><span class="glyphicon glyphicon-blackboard">&nbsp;教室管理</span></a>
	    <a href="/admin/repertory" class="menu-button-middle"><span class="glyphicon glyphicon-th-large">&nbsp;设备管理</span></a>
	    <a href="/admin/repairRecord" class="menu-button-middle"><span class="glyphicon glyphicon-th">&nbsp;维修记录管理</span></a>
	    <a href="/admin/student_manage/" class="menu-button-middle"><span class="glyphicon glyphicon-list-alt">&nbsp;在职学生管理</span></a>
	    <a href="/admin/user_request/" class="menu-button-middle"><span class="glyphicon glyphicon-list-alt">&nbsp;用户注册请求</span></a>
	    
	    <a href="/admin/adminaccount" class="menu-button-middle"><span class="glyphicon glyphicon-list-alt">&nbsp;账号生成</span></a>


	    <a href="/admin/HomepageModify/" class="menu-button-middle"><span class="glyphicon glyphicon-list-alt">&nbsp;主页管理</span></a>
	    <a href="/admin/batchImport/" class="menu-button-middle"><span class="glyphicon glyphicon-list-alt">&nbsp;批量导入</span></a>
	    
	    <a href="liexplorer://C:\exe_chrome\remoteControl.lnk" class="menu-button-middle">
	    	<span class="glyphicon glyphicon-list-alt">&nbsp;远程控制</span>
	    </a>
	    
	    
	    <a href="/admin/checkinManage" class="menu-button-middle"><span class="glyphicon glyphicon-list-alt">&nbsp;签到管理</span></a>
	    
	    <s:if test="!#session.role.equals(@util.Const@StudentToAdminRole)">
		    <a href="/admin/modifypassword" class="menu-button-middle"><span class="glyphicon glyphicon-lock">&nbsp;修改密码</span></a>
		    <a href="/admin/user_information" class="menu-button-middle"><span class="glyphicon glyphicon-list-alt">&nbsp;个人信息</span></a>
		    <a href="/logout" class="menu-button-middle"><span class="glyphicon glyphicon-list-alt">&nbsp;安全退出</span></a>
	    </s:if>
	    
	    <s:if test="#session.role.equals(@util.Const@StudentToAdminRole)">
            <a href="/student/" class="menu-button-middle"><span class="glyphicon glyphicon-share-alt">&nbsp;跳回在职学生</span></a>
        </s:if>

	    
	    <div class="menu-blank"></div>
	</div>
	
	
	
	 
 </layout:override>








<%@ include file="/jsp/base/user_base.jsp" %>
