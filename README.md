# Struts2 PDF Stream Plugin

The PDF Stream plugin allows to transform a view into a PDF stream and return it as a result from Action.

Default supported views:

- HTML
- JSP
- FreeMarker template
- Apache Tiles 2.x definition
- Apache Tiles 3.x definition


## Features Overview

- Direct transformation of HTML, JSP, FreeMarker template and Apache Tiles definition to PDF via Struts2 result
- PDF content styling using CSS
- Support of wide range of characters in PDF thanks to the [DejaVu fonts](http://dejavu-fonts.org/)
- Handling of the malformed HTML thanks to the [jsoup](http://jsoup.org/)


## Showcase

Showcase application can be downloaded from the Maven Central Repository.

[Download struts2-pdfstream-showcase](http://search.maven.org/remotecontent?filepath=com/amashchenko/struts2/pdfstream/struts2-pdfstream-showcase/1.3.0/struts2-pdfstream-showcase-1.3.0.war)


## Contributing

Found a bug or have a feature request? [Create a new issue](https://github.com/aleksandr-m/struts2-pdfstream/issues/new) or submit a [Pull Request](https://github.com/aleksandr-m/struts2-pdfstream/pulls).

## Questions

If you have questions about how to use `struts2-pdfstream-plugin` [create a new issue](https://github.com/aleksandr-m/struts2-pdfstream/issues/new) or ask a question on [Stack Overflow](http://stackoverflow.com/questions/tagged/struts2-pdfstream-plugin).


## Installation

Copy struts2-pdfstream-plugin-x.x.x.jar into your classpath (WEB-INF/lib). No other files need to be copied or created.

If you are using Maven, add this to your project POM:

    <dependencies>
        ...
        <dependency>
            <groupId>com.amashchenko.struts2.pdfstream</groupId>
            <artifactId>struts2-pdfstream-plugin</artifactId>
            <version>1.3.0</version>
        </dependency>
        ...
    </dependencies>

If you intend to transform Apache Tiles definition additional jar must be included.

For the Apache Tiles 2.x support add the `struts2-pdfstream-tiles`.

    <dependency>
        <groupId>com.amashchenko.struts2.pdfstream</groupId>
        <artifactId>struts2-pdfstream-tiles</artifactId>
        <version>1.3.0</version>
    </dependency>
        
For the Apache Tiles 3.x support add the `struts2-pdfstream-tiles3`.

    <dependency>
        <groupId>com.amashchenko.struts2.pdfstream</groupId>
        <artifactId>struts2-pdfstream-tiles3</artifactId>
        <version>1.3.0</version>
    </dependency>


## Usage

1. Install this plugin by adding dependency to your POM or by copying jar into /WEB-INF/lib directory.
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
