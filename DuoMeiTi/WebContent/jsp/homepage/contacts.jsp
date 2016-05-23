<%@ include file="/jsp/base/taglib.jsp" %>
<layout:override name="main_content">      

<div class="row news_frame">
	<div class="col-lg-6" style="width:98%; margin:10px">
    <!--news base template-->
		<div class="">
			<div>
				<div class="home-news">
					<div class="usoft-listview-header">
						<h3>
							通讯录
							<!-- <small>Contacts </small> -->
						</h3>
					</div>
					<br />
					
					<div>
					
						<h3>
							在职学生总人数：<s:property value="contacts_list.size()" />人
						</h3>
					</div>
					<div id="contactsDiv">
						<%@ include file="/jsp/homepage/widgets/contactsTable.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	function requestPageCallback(data)
	{
		$("#contactsDiv").html(data.contacts_list_html);
	}
</script>

</layout:override>
<%@ include file = "/jsp/homepage/new_home_base.jsp"%>