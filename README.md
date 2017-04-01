# Struts2 PDF Stream Plugin

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.amashchenko.struts2.pdfstream/struts2-pdfstream-plugin/badge.svg?subject=Maven%20Central)](https://maven-badges.herokuapp.com/maven-central/com.amashchenko.struts2.pdfstream/struts2-pdfstream-plugin/)
[![License](https://img.shields.io/badge/License-Apache%20License%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

The PDF Stream plugin allows to transform a view into a PDF stream and return it as a result from Action.

Default supported views:

- HTML
- JSP
- FreeMarker template
- Apache Tiles definition


## Struts2 PDF Stream v2

#### Apache PDFBox 2

Starting from 2.0.0, HTML into PDF rendering is done by the [openhtmltopdf](https://github.com/danfickle/openhtmltopdf) library which uses the [Apache PDFBox 2](https://pdfbox.apache.org/) to create PDF documents.
Apache PDFBox 2 is an open source Java tool for working with PDF documents and it is published under the Apache License v2.0.

#### Apache Struts 2.5

Upgraded to be compatible with Apache Struts 2.5

#### Java 7

Switched to Java 7 



## Features Overview

- Direct transformation of HTML, JSP, FreeMarker template and Apache Tiles definition to PDF via Struts2 result
- PDF content styling using CSS
- Support of wide range of characters in PDF thanks to the [DejaVu fonts](https://dejavu-fonts.github.io/)
- Handling of the malformed HTML thanks to the [jsoup](https://jsoup.org/)


## Showcase

Showcase application can be downloaded from the Maven Central Repository.

[Download struts2-pdfstream-showcase](http://search.maven.org/remotecontent?filepath=com/amashchenko/struts2/pdfstream/struts2-pdfstream-showcase/2.0.0/struts2-pdfstream-showcase-2.0.0.war)


## Contributing

Found a bug or have a feature request? [Create a new issue](https://github.com/aleksandr-m/struts2-pdfstream/issues/new) or submit a [Pull Request](https://github.com/aleksandr-m/struts2-pdfstream/pulls).

## Questions

If you have questions about how to use `struts2-pdfstream-plugin` [create a new issue](https://github.com/aleksandr-m/struts2-pdfstream/issues/new) or ask a question on [Stack Overflow](http://stackoverflow.com/questions/tagged/struts2-pdfstream-plugin).


## Installation

Copy following jars into your classpath (WEB-INF/lib):

- struts2-pdfstream-plugin-2.0.0.jar
- jsoup-1.9.2.jar
- openhtmltopdf-core-0.0.1-RC4.jar
- openhtmltopdf-pdfbox-0.0.1-RC4.jar
- pdfbox-2.0.0.jar
- fontbox-2.0.0.jar

If you are using Maven, add this to your project POM:

    <dependencies>
        ...
        <dependency>
            <groupId>com.amashchenko.struts2.pdfstream</groupId>
            <artifactId>struts2-pdfstream-plugin</artifactId>
            <version>2.0.0</version>
        </dependency>
        ...
    </dependencies>

If you intend to transform Apache Tiles definition additional jar must be included.

For the Apache Tiles support add the `struts2-pdfstream-tiles`.

    <dependency>
        <groupId>com.amashchenko.struts2.pdfstream</groupId>
        <artifactId>struts2-pdfstream-tiles</artifactId>
        <version>2.0.0</version>
    </dependency>


## Usage

1. Install this plugin by adding dependency to your POM or by copying jars into /WEB-INF/lib directory.
2. Make your action package extend `pdfstream-default` package or add `pdfstream` result type.
3. Use `pdfstream` result type.


## Examples

### JSP to PDF stream

    <action name="jspToPdf">
        <result type="pdfstream">
            <param name="location">/WEB-INF/pages/example.jsp</param>
            <param name="cssPaths">css/bootstrap.min.css, css/style.css</param>
            <param name="contentDisposition">attachment;filename=jsppdf.pdf</param>
        </result>
    </action>
    
### HTML to PDF stream

    <action name="htmlToPdf">
        <result type="pdfstream">
            <param name="location">/WEB-INF/pages/example.html</param>
            <param name="cssPaths">css/bootstrap.min.css, css/style.css</param>
            <param name="contentDisposition">attachment;filename=htmlpdf.pdf</param>
        </result>
    </action>

### Tiles definition to PDF stream

    <action name="tilesToPdf">
        <result type="pdfstream">
            <param name="location">example</param>
            <param name="renderer">tiles</param>
            <param name="contentDisposition">attachment;filename=tilespdf.pdf</param>
        </result>
    </action>

### FreeMarker template to PDF stream

    <action name="freemarkerToPdf">
        <result type="pdfstream">
            <param name="location">/WEB-INF/ftl/example.ftl</param>
            <param name="renderer">freemarker</param>
            <param name="cssPaths">css/bootstrap.min.css, css/style.css</param>
            <param name="contentDisposition">attachment;filename=ftlpdf.pdf</param>
        </result>
    </action>



## Extending the plugin
### Adding support for other views

This plugin can be easily extended in order to add support for transforming other views (e.g. Velocity) into PDF.

1. Implement `com.amashchenko.struts2.pdfstream.ViewRenderer` interface.
2. Create bean definition in struts.xml or in struts-plugin.xml with `type="com.amashchenko.struts2.pdfstream.ViewRenderer"` and custom name.

        <bean type="com.amashchenko.struts2.pdfstream.ViewRenderer" 
              class="some.package.CustomRenderer" name="customrenderer" />

3. Use `pdfstream` result with the `renderer` parameter set to the name of the bean you have defined.

        <action name="customToPdf">
            <result type="pdfstream">
                <param name="location">example</param>
                <param name="renderer">customrenderer</param>
            </result>
        </action>
