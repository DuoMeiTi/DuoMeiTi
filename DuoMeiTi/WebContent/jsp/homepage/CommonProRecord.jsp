<%@ include file="/jsp/base/taglib.jsp" %>
<layout:override name="main_content">
<h3 style="margin-left:20px"> 常见问题</h3>
<style>
#cmpr{
	margin-left:20px;
	"border:red solid;
	border-width:1 1 1 1;
	max-width:1120px;
}
</style>
<textarea name="cmpr" cols="178" rows="30" id="cmpr" ></textarea>
                    <s:iterator value="notice_list" var="i" status="index" >
		         	 	
	         	 		<div class="usoft-listview-basic">
						  <ul>
						    <li>
						      <span class="usoft-listview-item-date">
						      		<s:property value="#i.time.toString()"/> 
							  </span>
						      <span>
						      	<a href="/" > 
						      		<s:property value="#i.title"/> 
						      		:
						      		<s:property value="#i.content"/>
						      	</a>
						      </span>
						    </li>
						  </ul>
						</div>
		         	</s:iterator>	        	








</layout:override>
<%@ include file="/jsp/homepage/new_home_base.jsp" %>
