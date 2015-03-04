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

	<div class="panel panel-default">
		<div class="panel-heading">Latin Extended-A</div>
			<div class="panel-body">
				Ā ā Ă ă Ą ą Ć ć Ĉ ĉ Ċ ċ Č č Ď ď Đ đ Ē ē Ĕ ĕ Ė ė Ę ę Ě ě Ĝ ĝ Ğ ğ Ġ ġ Ģ ģ Ĥ ĥ Ħ ħ Ĩ ĩ Ī ī Ĭ ĭ Į į İ ı Ĳ ĳ Ĵ ĵ Ķ ķ ĸ Ĺ ĺ Ļ ļ Ľ ľ Ŀ ŀ Ł ł Ń ń Ņ ņ Ň ň ŉ ŉ Ŋ ŋ Ō ō Ŏ ŏ Ő ő Œ œ Ŕ ŕ Ŗ ŗ Ř ř Ś ś Ŝ ŝ Ş ş Š š Ţ ţ Ť ť Ŧ ŧ Ũ ũ Ū ū Ŭ ŭ Ů ů Ű ű Ų ų Ŵ ŵ Ŷ ŷ Ÿ Ź ź Ż ż Ž ž ſ
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">Cyrillic</div>
			<div class="panel-body">
				А а Б б В в Г г Д д Е е Ё ё Ж ж З з И и Й й К к Л л М м Н н О о П п Р р С с Т т У у Ф ф Х х Ц ц Ч ч Ш ш Щ щ Ъ ъ Ы ы Ь ь Э э Ю ю Я я
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">Currency Symbols</div>
			<div class="panel-body">
				₠ ₡ ₢ ₣ ₤ ₥ ₦ ₧ ₨ ₩ ₪ ₫ € ₭ ₮ ₯ ₰ ₱ ₲ ₳ ₴ ₵
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">Domino Tiles</div>
			<div class="panel-body">
				&#x1F030;&#x1F031;&#x1F032;&#x1F033;&#x1F034;&#x1F035;&#x1F036;&#x1F037;&#x1F038;&#x1F039;&#x1F03A;&#x1F03B;&#x1F03C;&#x1F03D;&#x1F03E;&#x1F03F;
		</div>
	</div>

	<s:a id="jspbutton" action="jspToPdf" cssClass="btn btn-primary btn-md">jsp to pdf stream</s:a>

</div>
