<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="jumbotron">
    <h2>Struts2 PDF Stream Plugin Showcase</h2>

    <p>Struts2 plugin that provides result which transforms a view into PDF stream.</p>

    <s:url var="m1Url" action="table" namespace="/"/>
    <s:a href="%{m1Url}" cssClass="btn btn-primary btn-lg">example</s:a>
</div>
