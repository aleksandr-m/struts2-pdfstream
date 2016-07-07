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

import org.apache.struts2.StrutsJUnit4TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Plugin tests.
 * 
 * @author Aleksandr Mashchenko
 * 
 */
public class PluginTest extends StrutsJUnit4TestCase<PdfStreamResult> {

    /**
     * Tests default renderer bean.
     * 
     * @throws Exception
     */
    @Test
    public void testDefaultRendererBean() throws Exception {
        Assert.assertNotNull(container);

        ViewRenderer viewRenderer = container.getInstance(ViewRenderer.class);

        Assert.assertNotNull(viewRenderer);
        Assert.assertTrue(viewRenderer instanceof DefaultRenderer);
    }

    /**
     * Tests FreeMarker renderer bean.
     * 
     * @throws Exception
     */
    @Test
    public void testFreemarkerRendererBean() throws Exception {
        Assert.assertNotNull(container);

        ViewRenderer viewRenderer = container.getInstance(ViewRenderer.class,
                        "freemarker");

        Assert.assertNotNull(viewRenderer);
        Assert.assertTrue(viewRenderer instanceof FreemarkerRenderer);
    }
}
