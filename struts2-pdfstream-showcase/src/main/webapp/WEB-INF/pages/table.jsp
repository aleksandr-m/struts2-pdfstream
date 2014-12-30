<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="jsp">
	<h3>JSP file</h3>
	
	<table class="table table-bordered table-striped table-condensed">
		<thead>
			<tr>
				<th>x1</th><th>x2</th><th>x3</th>
			</tr>
		</thead>
		<s:iterator value="list">
			<tr>
				<td><s:property/></td>
				<td><s:property value="top * 2"/></td>
				<td><s:property value="top * 3"/></td>
			</tr>
		</s:iterator>
	</table>
	
	<s:a id="jspbutton" action="jspToPdf" cssClass="btn btn-primary btn-md">jsp to pdf stream</s:a>
	
</div>


