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
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Simple servlet response wrapper.
 * 
 * @author Aleksandr Mashchenko
 * 
 */
public class SimpleServletResponseWrapper extends HttpServletResponseWrapper {

    private StringWriter sw = new StringWriter();

    public SimpleServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(sw);
    }

    public ServletOutputStream getOutputStream() throws IOException {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return sw.toString();
    }
}
