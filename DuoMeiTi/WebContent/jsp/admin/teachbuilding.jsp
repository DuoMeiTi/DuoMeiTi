<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">
	<div class="mycontent">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3 classbuilding">
				<span>教学楼管理</span>
			</div>
		</div>
		<hr>
		<div class="container-fluid">
			<s:iterator value="builds" var="build" status="i">
				<div class="col-lg-4">
					<a href="classroom_manage?build_id=<s:property value="#build.build_id"/>&build_name=<s:property value="#build.build_name"/>">
						<div class="teachbuilding-div"><s:property value="#build.build_name"/></div>
					</a>
				</div>
			</s:iterator>
		</div>
	</div>
	<style>
		.teachbuilding-div {
			margin: 10%;
			height:100px;
			line-height:100px;
			border:1px solid black;
			text-align:center;
		}
	</style>
</layout:override>


<%@ include file="base.jsp" %>