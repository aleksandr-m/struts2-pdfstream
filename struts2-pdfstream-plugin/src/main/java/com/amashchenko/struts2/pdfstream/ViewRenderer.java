/*
 * Copyright 2014-2018 Aleksandr Mashchenko.
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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * A view renderer should implement this interface.
 * 
 * @author Aleksandr Mashchenko
 * 
 */
public interface ViewRenderer {
    /**
     * 
     * @param location
     *            Path to the resource to render.
     * @param request
     *            Servlet request.
     * @param response
     *            Servlet response.
     * @param servletContext
     *            Reference to the ServletContext.
     * @param locale
     *            Locale to use.
     * @param valueStack
     *            Reference to the ValueStack.
     * @param action
     *            Reference to the current Action.
     * @throws Exception
     */
    void render(String location, HttpServletRequest request,
                    HttpServletResponse response,
                    ServletContext servletContext, Locale locale,
                    ValueStack valueStack, Object action) throws Exception;
}
