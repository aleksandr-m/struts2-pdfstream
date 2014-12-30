<h3>Table from FreeMarker template</h3>

<table class="table table-bordered table-striped table-condensed">
	<thead>
		<tr>
			<th>x1</th><th>x2</th><th>x3</th>
		</tr>
	</thead>
	<#list list as l>
		<tr>
			<td>${l}</td>
			<td>${l * 2}</td>
			<td>${l * 3}</td>
		</tr>
	</#list>
</table>
