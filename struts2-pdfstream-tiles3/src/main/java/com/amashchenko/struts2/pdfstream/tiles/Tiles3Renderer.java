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
package com.amashchenko.struts2.pdfstream.tiles;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.servlet.ServletRequest;
import org.apache.tiles.request.servlet.ServletUtil;

import com.amashchenko.struts2.pdfstream.ViewRenderer;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * Apache Tiles 3.x renderer.
 * 
 * @author Aleksandr Mashchenko
 * 
 */
public class Tiles3Renderer implements ViewRenderer {

    /** {@inheritDoc} */
    @Override
    public void render(final String location, final HttpServletRequest request,
                    final HttpServletResponse response,
                    final ServletContext servletContext, final Locale locale,
                    final ValueStack valueStack, final Object action) {
        ApplicationContext applicationContext = ServletUtil
                        .getApplicationContext(servletContext);
        TilesContainer container = TilesAccess.getContainer(applicationContext);
        ServletRequest servletRequest = new ServletRequest(applicationContext,
                        request, response);
        container.render(location, servletRequest);
    }
}
