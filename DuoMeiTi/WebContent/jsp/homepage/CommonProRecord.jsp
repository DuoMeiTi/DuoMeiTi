<%@ include file="/jsp/base/taglib.jsp" %>
<layout:override name="main_content">       
<div class="row news_frame">
  <div class="col-lg-6" style="width:98%; margin:10px">
    <!--news base template-->
		<div class="">
		  <div class="front-wrap" style="height:800px">
		    <div class="home-news">
		      <div class="usoft-listview-header">
		        <h3>常见问题          <small>COMMONPROBLEM </small> </h3>

		      </div>
		      
		      <div class="news-list">
		      
		      
		        <div class="news-documents documents-list " >
		         	
		         	<s:iterator value="cmpShow" var="i" status="index" >
		         	 	
	         	 		<div class="usoft-listview-basic">
						  <ul>
						    <li>
						      <span>
						      		<span class="glyphicon glyphicon-star"></span></span><b><s:property value="#i.title"/> </b>
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
