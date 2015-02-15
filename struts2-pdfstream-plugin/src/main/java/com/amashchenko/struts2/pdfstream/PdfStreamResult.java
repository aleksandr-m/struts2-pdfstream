/*
 * Copyright 2014-2015 Aleksandr Mashchenko.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.amashchenko.struts2.pdfstream;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.StrutsResultSupport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * <!-- START SNIPPET: description -->
 * 
 * This result transforms a view into a PDF stream. Default supported views are:
 * a JSP, a FreeMarker template, an Apache Tiles definition.
 * 
 * <!-- END SNIPPET: description -->
 * <p/>
 * <b>This result type takes the following parameters:</b>
 * 
 * <!-- START SNIPPET: params -->
 * 
 * <ul>
 * 
 * <li><b>contentDisposition</b> - the content disposition header value for
 * specifing the file name (default = <code>inline</code>, values are typically
 * <i>attachment;filename="document.pdf"</i>.</li>
 * 
 * <li><b>allowCaching</b> if set to 'false' it will set the headers 'Pragma'
 * and 'Cache-Control' to 'no-cahce', and prevent client from caching the
 * content. (default = <code>true</code>)
 * 
 * <li><b>renderer</b> - name of the {@link ViewRenderer} bean which will be
 * used to produce pdf stream. If not defined the {@link DefaultRenderer} will
 * be used.</li>
 * 
 * <li><b>cssPaths</b> - comma separated values of CSS files paths.</li>
 * 
 * </ul>
 * 
 * <!-- END SNIPPET: params -->
 * 
 * <b>Example JSP page:</b>
 * 
 * <pre>
 * <!-- START SNIPPET: example -->
 * &lt;result type="pdfstream"&gt;
 *   &lt;param name="location"&gt;/WEB-INF/page.jsp&lt;/param&gt;
 *   &lt;param name="cssPaths"&gt;css/some.css, css/another.css&lt;/param&gt;
 *   &lt;param name="contentDisposition"&gt;attachment;filename="somepdf.pdf"&lt;/param&gt;
 * &lt;/result&gt;
 * <!-- END SNIPPET: example -->
 * </pre>
 * 
 * <b>Example FreeMarker template:</b>
 * 
 * <pre>
 * <!-- START SNIPPET: example -->
 * &lt;result type="pdfstream"&gt;
 *   &lt;param name="location"&gt;/WEB-INF/template.ftl&lt;/param&gt;
 *   &lt;param name="renderer"&gt;freemarker&lt;/param&gt;
 *   &lt;param name="cssPaths"&gt;css/some.css, css/another.css&lt;/param&gt;
 *   &lt;param name="contentDisposition"&gt;attachment;filename="somepdf.pdf"&lt;/param&gt;
 * &lt;/result&gt;
 * <!-- END SNIPPET: example -->
 * </pre>
 * 
 * @author Aleksandr Mashchenko
 * 
 */
public class PdfStreamResult extends StrutsResultSupport {
    private static final long serialVersionUID = -1243295451653518563L;

    /** Logger. */
    private static final Logger LOG = LoggerFactory
                    .getLogger(PdfStreamResult.class);

    private final static String PDF_MIME_TYPE = "application/pdf";

    private final static String FONT_FILE_PATH = "/fonts/DejaVuSans.ttf";

    private final static String FONT_STYLE_TAG = "<style type=\"text/css\">body{font-family:DejaVu Sans;}</style>";

    private String contentDisposition = "inline";

    private boolean allowCaching = true;

    private String renderer;

    private Set<String> cssPathsSet;

    @Inject
    private Container container;

    @Override
    protected void doExecute(String finalLocation, ActionInvocation invocation)
                    throws Exception {
        if (LOG.isDebugEnabled()) {
            LOG.debug("In doExecute. finalLocation: " + finalLocation
                            + ", renderer: " + renderer);
        }

        OutputStream os = null;
        try {
            final ActionContext actionContext = invocation
                            .getInvocationContext();
            final HttpServletRequest request = (HttpServletRequest) actionContext
                            .get(HTTP_REQUEST);
            final HttpServletResponse response = (HttpServletResponse) actionContext
                            .get(HTTP_RESPONSE);
            final SimpleServletResponseWrapper responseWrapper = new SimpleServletResponseWrapper(
                            response);
            final ServletContext servletContext = (ServletContext) actionContext
                            .get(SERVLET_CONTEXT);

            ViewRenderer viewRenderer;
            if (renderer == null) {
                viewRenderer = container.getInstance(ViewRenderer.class);
            } else {
                viewRenderer = container.getInstance(ViewRenderer.class,
                                renderer);
            }

            if (viewRenderer == null) {
                final String err = "Cannot get an instance of ViewRenderer with the name '"
                                + renderer + "'.";
                LOG.error(err);
                throw new AssertionError(err);
            }

            // render view
            viewRenderer.render(finalLocation, request, responseWrapper,
                            servletContext, actionContext.getLocale(),
                            invocation.getStack(), invocation.getAction());

            // Set the content type
            response.setContentType(PDF_MIME_TYPE);

            // Set the content-disposition
            if (contentDisposition != null) {
                response.addHeader(
                                "Content-Disposition",
                                conditionalParse(contentDisposition, invocation));
            }

            // Set the cache control headers if necessary
            if (!allowCaching) {
                response.addHeader("Pragma", "no-cache");
                response.addHeader("Cache-Control", "no-cache");
            }

            if (LOG.isTraceEnabled()) {
                LOG.trace("Content before parsing:\n"
                                + responseWrapper.toString());
            }

            // parse response wrapper
            final Document document = parseContent(responseWrapper.toString());
            final Element head = document.head();

            // add CSS from cssPathsSet parameter
            if (cssPathsSet != null && !cssPathsSet.isEmpty()) {
                for (String css : cssPathsSet) {
                    // remove leading slash
                    if (css.startsWith("\\")) {
                        css = css.substring(1);
                    }
                    head.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""
                                    + css + "\" />");
                }
            }

            // add style for font family that supports unicode
            head.append(FONT_STYLE_TAG);

            final String content = document.html();

            if (LOG.isTraceEnabled()) {
                LOG.trace("Content after parsing:\n" + content);
            }

            // put pdf stream into response
            createPdfStream(content, findBaseUrl(request),
                            response.getOutputStream());
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    Document parseContent(final String content) {
        Document document = Jsoup.parse(content);
        document.outputSettings().escapeMode(EscapeMode.xhtml);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

        // remove script tags, they are not supported in pdf and can lead to
        // not well formed document (e.g. <\/script> - escaped script tag)
        document.select("script").remove();

        return document;
    }

    private void createPdfStream(final String content, final String baseUrl,
                    final OutputStream outputStream) throws DocumentException,
                    IOException, URISyntaxException {
        ITextRenderer renderer = new ITextRenderer();
        // for unicode
        renderer.getFontResolver().addFont(FONT_FILE_PATH, BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED);

        renderer.setDocumentFromString(content, baseUrl);
        renderer.layout();
        renderer.createPDF(outputStream);
    }

    String findBaseUrl(final HttpServletRequest request) {
        final String baseUrl;
        if (request == null) {
            baseUrl = "";
        } else {
            StringBuffer url = request.getRequestURL();
            String uri = request.getRequestURI();
            String ctx = request.getContextPath();
            baseUrl = url.substring(0,
                            url.length() - uri.length() + ctx.length())
                            + "/";
        }
        return baseUrl;
    }

    public void setCssPaths(final String cssPaths) {
        cssPathsSet = stringToSet(cssPaths);
    }

    /**
     * Converts comma separated string to LinkedHashSet.
     * 
     * @param str
     *            Comma separated string.
     * @return LinkedHashSet of strings.
     */
    LinkedHashSet<String> stringToSet(final String str) {
        final LinkedHashSet<String> set;
        if (str == null || str.trim().isEmpty()) {
            set = null;
        } else {
            set = new LinkedHashSet<String>();
            String[] split = str.split(",");
            for (String s : split) {
                String trimmed = s.trim();
                if (!trimmed.isEmpty()) {
                    set.add(trimmed);
                }
            }
        }
        return set;
    }

    /**
     * @param renderer
     *            the renderer to set
     */
    public void setRenderer(String renderer) {
        this.renderer = renderer;
    }

    /**
     * @param contentDisposition
     *            the contentDisposition to set
     */
    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    /**
     * @param allowCaching
     *            the allowCaching to set
     */
    public void setAllowCaching(boolean allowCaching) {
        this.allowCaching = allowCaching;
    }
}
