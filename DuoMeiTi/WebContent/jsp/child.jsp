<%@ taglib prefix="layout" uri="/WEB-INF/jsp_layout.tld"%>
<layout:override name="head">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	this child
</layout:override>

<layout:override name="content">
	<div>
		this child content
	</div>
</layout:override>
<%@ include file="base.jsp" %>