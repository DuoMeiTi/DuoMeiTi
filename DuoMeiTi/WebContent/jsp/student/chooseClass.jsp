<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">

<link rel="stylesheet" type="text/css" media="screen" href="/css/student/chooseclass.css"/> 

<div class="mycontent">
	<h3>选择值班时间地点</h3>
	<div class="teachbuilding-droplist">
		<select class="form-control buildingSelect">
			<option value=0></option>
			<s:iterator value="teahBuildings" var="i" status="index" >
  				<option value=<s:property value="#i.buildingId"/>><s:property value="#i.buildingName"/></option>
  			</s:iterator>
  		</select>
	</div>
	<!-- 
	<h3>时间列表(还可选择<span class="self-duty-left"><s:property value='3'/></span>个班)</h3>
	-->
	<div class="time-table hide" iid=<s:property value="#session.student_id"/>>
		<h3>时间列表</h3>
		<table class="table table-bordered">
			<thead>
				<tr class="row">
					<th class="col-md-2">值班时间</th>
					<s:iterator value="{'一','二','三','四','五','六','七'}" var='week'>
						<th class="col-md-1.5"><s:property value='week'/></th>
					</s:iterator>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="{'7:40 ~~ 9:20','9:50 ~~ 11:30','13:15 ~~ 14:50','15:20 ~~ 17:00','17:45 ~~ 19:20'}" var="time" status="row">
					<tr class="row" row=<s:property value="#row.index+1"/>>
						<td class="col-md-2"><s:property value="time"/></td>
						<s:iterator value="{'0','0','0','0','0','0','0'}" var="num" status="col">
							<td class="col-md-1.5 dutyleft" col=<s:property value="#col.index+1"/>><s:property value="num"/></td>
						</s:iterator>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<button type="button" class="btn btn-default" id="duty-choose-submit">提交</button>
	</div>
</div>

<script type='text/javascript' src="/js/student/chooseclass.js"></script>
</layout:override>

<%@ include file="/jsp/student/base.jsp" %>
