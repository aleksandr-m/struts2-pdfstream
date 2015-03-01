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

import java.util.Locale;

import org.apache.struts2.StrutsJUnit4TestCase;
import org.junit.Assert;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;

/**
 * FreemarkerRenderer tests.
 * 
 * @author Aleksandr Mashchenko
 * 
 */
public class FreemarkerRendererTest extends
                StrutsJUnit4TestCase<PdfStreamResult> {
    /**
     * Tests render method.
     * 
     * @throws Exception
     */
    @Test
    public void testRender() throws Exception {
        FreemarkerRenderer freemarkerRenderer = new FreemarkerRenderer();
        Assert.assertNotNull(freemarkerRenderer);

        // inject dependencies
        injectStrutsDependencies(freemarkerRenderer);

        ActionContext actionContext = ActionContext.getContext();
        actionContext.setLocale(Locale.ENGLISH);

        response.setCharacterEncoding("UTF-8");

        // call render method
        freemarkerRenderer.render("/test.ftl", request, response,
                        servletContext, actionContext.getLocale(),
                        actionContext.getValueStack(), null);

        final String expected = "FreeMarker renderer test. А а Б б В в Г г Д д. Current language - en.";
        final String resp = response.getContentAsString();
        Assert.assertEquals(expected, resp.trim());
    }
}
