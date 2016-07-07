/*
 * Copyright 2014-2016 Aleksandr Mashchenko.
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

import java.util.LinkedHashSet;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * Tests for PdfStreamResult.
 * 
 * @author Aleksandr Mashchenko
 * 
 */
public class PdfStreamResultTest {
    private PdfStreamResult pdfStreamResult;

    @Before
    public void init() {
        pdfStreamResult = new PdfStreamResult();
    }

    @Test
    public void testFindBaseUrl() throws Exception {
        Assert.assertNotNull(pdfStreamResult);

        MockHttpServletRequest request = new MockHttpServletRequest();

        request.setLocalName("localhost");
        request.setRequestURI("/contextPath/requestURI");
        request.setQueryString("queryString");
        request.setContextPath("/contextPath");

        final String baseUrl = pdfStreamResult.findBaseUrl(request);

        Assert.assertEquals("http://localhost/contextPath/", baseUrl);
    }

    @Test
    public void testFindBaseUrlNull() throws Exception {
        Assert.assertNotNull(pdfStreamResult);

        Assert.assertEquals("", pdfStreamResult.findBaseUrl(null));
    }

    @Test
    public void testStringToSet() throws Exception {
        Assert.assertNotNull(pdfStreamResult);

        final String paths = "/somepath/style.css";
        final LinkedHashSet<String> set = pdfStreamResult.stringToSet(paths);
        Assert.assertNotNull(set);
        Assert.assertEquals(1, set.size());
        Assert.assertEquals("/somepath/style.css", set.toArray()[0]);
    }

    @Test
    public void testStringToSet2() throws Exception {
        Assert.assertNotNull(pdfStreamResult);

        final String paths = "somepath/style.css,    /another/style2.css, ";
        LinkedHashSet<String> set = pdfStreamResult.stringToSet(paths);
        Assert.assertNotNull(set);
        Assert.assertEquals(2, set.size());
        Assert.assertEquals("somepath/style.css", set.toArray()[0]);
        Assert.assertEquals("/another/style2.css", set.toArray()[1]);
    }

    @Test
    public void testStringToSetNull() throws Exception {
        Assert.assertNotNull(pdfStreamResult);

        final String paths = null;
        LinkedHashSet<String> set = pdfStreamResult.stringToSet(paths);
        Assert.assertNull(set);
    }

    @Test
    public void testStringToSetEmpty() throws Exception {
        Assert.assertNotNull(pdfStreamResult);

        final String paths = "";
        LinkedHashSet<String> set = pdfStreamResult.stringToSet(paths);
        Assert.assertNull(set);
    }

    @Test
    public void testParseContent() throws Exception {
        Assert.assertNotNull(pdfStreamResult);

        final Document doc = pdfStreamResult.parseContent("<div>text</div>");
        Assert.assertNotNull(doc);

        Assert.assertEquals(
                        "<html><head></head><body><div>text</div></body></html>",
                        StringUtils.deleteWhitespace(doc.html()));
    }

    @Test
    public void testParseContentInput() throws Exception {
        Assert.assertNotNull(pdfStreamResult);

        final Document doc = pdfStreamResult
                        .parseContent("<form><input type='text' name='name'></form>");
        Assert.assertNotNull(doc);

        Assert.assertEquals(
                        "<html><head></head><body><form><inputtype=\"text\"name=\"name\"/></form></body></html>",
                        StringUtils.deleteWhitespace(doc.html()));
    }

    @Test
    public void testParseContentScript() throws Exception {
        Assert.assertNotNull(pdfStreamResult);

        final Document doc = pdfStreamResult
                        .parseContent("<head><script>alert('alert 1');<\\/script></head><script>alert('alert 2');</script><div>text</div>");
        Assert.assertNotNull(doc);

        Assert.assertEquals(
                        "<html><head></head><body><div>text</div></body></html>",
                        StringUtils.deleteWhitespace(doc.html()));
    }
}
