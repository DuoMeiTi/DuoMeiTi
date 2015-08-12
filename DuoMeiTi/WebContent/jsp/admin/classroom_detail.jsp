<%@ include file="/jsp/base/taglib.jsp" %>

<style type="text/css">
ul {
	list-style:none;
}

.roomname-div {
	margin:0 auto;
	margin-top:30px;
	width:40%;
	text-align:center;
	height:50px;
    line-height:50px;  
    overflow:hidden;   
	border:1px solid black;
}

.btn-div {
	margin: 20px 200px 20px 65%;
}

.detail-div {
	margin: 10px 10px 10px 10px;
}

.device {
	position:relative;
	width:70%;
	float:left
}

.record {
	width:30%;
}

</style>

<layout:override name="main_content">
	<div class="roomname-div">
		<span>301</span>&nbsp;&nbsp;&nbsp;&nbsp;
		<span>负责人:</span>
		<span class="director-span">楼主</span>
	</div>
	<div class="btn-div">
		<button type="button" class="btn btn-primary btn-sm">查看课表</button>&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" class="btn btn-primary btn-sm">填写周检查记录</button>
	</div>
	<div class="detail-div">
		<div class="device">
			<ul>
				<li>
					<table></table>
				</li>
				<li>
				</li>
			</ul>
		</div>
		<div class="record">
			<ul>
				<li></li>
				<li></li>
			</ul>
		</div>
	</div>
</layout:override>

<%@ include file="base.jsp" %>