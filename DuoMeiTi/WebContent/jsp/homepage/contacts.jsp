<%@ include file="/jsp/base/taglib.jsp" %>
<layout:override name="main_content">      

<div class="mycontent">
<!-- 	<div class="col-lg-6" style="width:98%; margin:10px"> -->
    <!--news base template-->
<!-- 		<div class=""> -->
			<div>
<!-- 				<div class="home-news"> -->
<!-- 					<div class="usoft-listview-header"> -->
						<h3>
							通讯录
						</h3>
<!-- 					</div> -->
					<div>
					
						<h4>
							在职学生总人数：<s:property value="contacts_list.size()" />人							
						</h4>
						<h5>
							注：学生按照学号长度排序，先是研究生，然后是本科生
						</h5>
					</div>
					<div id="contactsDiv">
						<%@ include file="/jsp/homepage/widgets/contactsTable.jsp"%>
					</div>
					
					
			</div>
<!-- 		</div> -->
<!-- 	</div> -->
</div>

<script>
// 	function requestPageCallback(data)
// 	{
// 		$("#contactsDiv").html(data.contacts_list_html);
// 	}
</script>

</layout:override>
<%@ include file = "/jsp/homepage/new_home_base.jsp"%>