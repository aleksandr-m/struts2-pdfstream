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
            <img style="position: absolute; top: 0; right: 0; border: 0; z-index: 10;" src="<s:url value='/images/forkme_right_red_aa0000.png'/>" alt="Fork me on GitHub"/>
        </a>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12 tiles">
                    <h2>Tiles definition</h2>
                    <tiles:insertAttribute name="body" />

                    <div class="top-space">
                        <s:a id="tilesbutton" action="tilesToPdf" cssClass="btn btn-primary btn-md">tiles to pdf stream</s:a>
                    </div>
                </div>
            </div>

            <div class="row top-space">
                <div class="col-md-12">
                    <s:a action="freemarkerToPdf" cssClass="btn btn-primary btn-md">freemarker to pdf stream</s:a>
                </div>
            </div>
            
            <div class="row top-space">
                <div class="col-md-12">
                    <s:a action="htmlToPdf" cssClass="btn btn-primary btn-md">html to pdf stream</s:a>
                </div>
            </div>

            <div class="row top-space">
                <div class="col-md-12 example-code">
                    <i>Configuration:</i>
                    <pre>
                        <span id="conf-jsp">
&lt;action&gt;
    &lt;result type="pdfstream"&gt;
        &lt;param name="location"&gt;/WEB-INF/pages/example.jsp&lt;/param&gt;
        &lt;param name="cssPaths"&gt;css/bootstrap.min.css, css/style.css&lt;/param&gt;
        &lt;param name="contentDisposition"&gt;attachment;filename=jsppdf.pdf&lt;/param&gt;
    &lt;/result&gt;
&lt;/action&gt;
                        </span>
                        <span id="conf-tiles">
&lt;action&gt;
    &lt;result type="pdfstream"&gt;
        &lt;param name="location"&gt;example&lt;/param&gt;
        &lt;param name="renderer"&gt;tiles&lt;/param&gt;
        &lt;param name="contentDisposition"&gt;attachment;filename=tilespdf.pdf&lt;/param&gt;
    &lt;/result&gt;
&lt;/action&gt;
                        </span>
                        <span id="conf-freemarker">    
&lt;action&gt;
    &lt;result type="pdfstream"&gt;
        &lt;param name="location"&gt;/WEB-INF/ftl/example.ftl&lt;/param&gt;
        &lt;param name="renderer"&gt;freemarker&lt;/param&gt;
        &lt;param name="cssPaths"&gt;css/bootstrap.min.css, css/style.css&lt;/param&gt;
        &lt;param name="contentDisposition"&gt;attachment;filename=ftlpdf.pdf&lt;/param&gt;
    &lt;/result&gt;
&lt;/action&gt;
                        </span>
                        <span id="conf-html">    
&lt;action&gt;
    &lt;result type="pdfstream"&gt;
        &lt;param name="location"&gt;/WEB-INF/pages/example.html&lt;/param&gt;
        &lt;param name="cssPaths"&gt;css/bootstrap.min.css, css/style.css&lt;/param&gt;
        &lt;param name="contentDisposition"&gt;attachment;filename=htmlpdf.pdf&lt;/param&gt;
    &lt;/result&gt;
&lt;/action&gt;
                        </span>
                    </pre>
                </div>
            </div>

        </div>


        <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>

        <script type="text/javascript">
            $(function() {
                $("#jspbutton").mouseenter(function() {
                    $(".jsp").addClass("frame");
                }).mouseleave(function() {
                    $(".jsp").removeClass("frame");
                });
                $("#tilesbutton").mouseenter(function() {
                    $(".tiles").addClass("frame");
                }).mouseleave(function() {
                    $(".tiles").removeClass("frame");
                });
            });
        </script>
    </body>
</html>
