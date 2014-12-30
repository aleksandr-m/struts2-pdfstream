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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An interface for Tiles renderer.
 * 
 * @author Aleksandr Mashchenko
 * 
 */
public interface TilesRenderer {
    /**
     * Renders Tiles definition.
     * 
     * @param definition
     *            Tiles definition to render.
     * @param request
     *            Servlet request.
     * @param response
     *            Servlet response.
     * @param servletContext
     *            Servlet context.
     * @throws Exception
     */
    void renderTiles(String definition, HttpServletRequest request,
                    HttpServletResponse response, ServletContext servletContext)
                    throws Exception;
}
