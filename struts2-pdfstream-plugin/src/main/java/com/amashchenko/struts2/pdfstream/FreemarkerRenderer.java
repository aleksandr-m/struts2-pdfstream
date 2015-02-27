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
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.freemarker.FreemarkerManager;

import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.ValueStack;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * FreeMarker renderer.
 * 
 * @author Aleksandr Mashchenko
 * 
 */
public class FreemarkerRenderer implements ViewRenderer {

    @Inject
    private FreemarkerManager freemarkerManager;

    /** {@inheritDoc} */
    @Override
    public void render(final String location, final HttpServletRequest request,
                    final HttpServletResponse response,
                    final ServletContext servletContext, final Locale locale,
                    final ValueStack valueStack, final Object action)
                    throws IOException, TemplateException {
        Configuration configuration = freemarkerManager
                        .getConfiguration(servletContext);
        Template template = configuration.getTemplate(location, locale);

        TemplateModel model = freemarkerManager.buildTemplateModel(valueStack,
                        action, servletContext, request, response,
                        configuration.getObjectWrapper());

        template.process(model, response.getWriter());
    }
}
