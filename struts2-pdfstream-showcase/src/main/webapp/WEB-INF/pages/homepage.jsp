<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="jumbotron">
    <h2>Struts2 PDF Stream Plugin Showcase</h2>

    <p>Struts2 PDF Stream plugin allows to transform a view into a PDF stream and return it as a result from Action.</p>

    <s:url var="m1Url" action="table" namespace="/"/>
    <s:a href="%{m1Url}" cssClass="btn btn-primary btn-lg">example</s:a>
</div>
