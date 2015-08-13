<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">
	<div class="classbuilding">
		<span>教学楼管理</span>
	</div>
	<hr>
	<div class="container-fluid">
		<div class="row col-lg-12">
			<div class="col-lg-4">
				<a href="classroom_manage"><div class="teachbuilding-div">一馆</div></a>
			</div>
			<div class="col-lg-4">
				<div class="teachbuilding-div">一馆</div>
			</div>
			<div class="col-lg-4">
				<div class="teachbuilding-div">一馆</div>
			</div>
		</div>
		<div class="row col-lg-12">
			<div class="col-lg-4">
				<div class="teachbuilding-div">一馆</div>
			</div>
			<div class="col-lg-4">
				<div class="teachbuilding-div">一馆</div>
			</div>
			<div class="col-lg-4">
				<div class="teachbuilding-div">一馆</div>
			</div>
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