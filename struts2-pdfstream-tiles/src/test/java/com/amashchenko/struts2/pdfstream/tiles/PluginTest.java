/*
 * Copyright 2014-2017 Aleksandr Mashchenko.
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
package com.amashchenko.struts2.pdfstream.tiles;

import org.apache.struts2.StrutsJUnit4TestCase;
import org.junit.Assert;
import org.junit.Test;

import com.amashchenko.struts2.pdfstream.ViewRenderer;

/**
 * Plugin tests.
 * 
 * @author Aleksandr Mashchenko
 * 
 */
public class PluginTest extends StrutsJUnit4TestCase<TilesRenderer> {

    /**
     * Tests tiles renderer bean.
     * 
     * @throws Exception
     */
    @Test
    public void testTilesRendererBean() throws Exception {
        Assert.assertNotNull(container);

        ViewRenderer viewRenderer = container.getInstance(ViewRenderer.class,
                        "tiles");

        Assert.assertNotNull(viewRenderer);
        Assert.assertTrue(viewRenderer instanceof TilesRenderer);
    }
}
