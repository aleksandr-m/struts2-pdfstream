<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Struts2 PDF Stream Showcase</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/css/bootstrap.min.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/css/style.css"/>"/>
    </head>

    <body>
        <a href="https://github.com/aleksandr-m/struts2-pdfstream" target="_blank">
        	<img style="position: absolute; top: 0; right: 0; border: 0; z-index: 10;" src="<s:url value='/images/forkme_right_red_aa0000.png'/>" alt="Fork me on GitHub">
        </a>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <tiles:insertAttribute name="body" />
                </div>
            </div>
        </div>
    </body>
</html>
