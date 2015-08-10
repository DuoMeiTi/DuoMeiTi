
 
<%@ include file="jsp/base.jsp"%>



	<div id="new">
		<s:if test='ab123==null'> ab123 is null </s:if>
	</div>
	
<script>
 document.getElementById("demo").innerHTML=document.getElementById("new").innerHTML;
 
 document.getElementById("new").innerHTML="";
 
</script>




