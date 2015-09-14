<table class="table table-bordered table-striped" id="classroom_table">
      <thead>
        <tr>
            <th>教室号</th>
            <th>设备</th>
            <th>教室大小</th>
            <th>负责人</th>
            <th>管理</th>
        </tr>
      </thead>
      <!-- <tr>
        <td>201</td>
        <td>打印机</td>
        <td>20*40</td>
        <td>张三</td>
        <td><a href="classroom_detail" class="btn btn-info active">详&nbsp;&nbsp;细</a></td>
      </tr> -->
      <s:iterator value="classroom_list" var="i" status="index" >  
			<tr class="success">
				<td>   <s:property value="#i.classroom_num"/>    </td>
				<td>   <s:property value="#i.equipment"/>    </td>
				<td>   <s:property value="#i.size"/>    </td>
				<td>   <s:property value="#i.principal"/>    </td>
				<td>   <a href="classroom_detail" class="btn btn-info active">详&nbsp;&nbsp;细</a>    </td>
			</tr>
		</s:iterator>
      
      
    </table>