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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

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

    private final ByteArrayOutputStream stream;
    private final PrintWriter printWriter;
    private final SimpleServletOutputStream simpleServletOutputStream;

    public SimpleServletResponseWrapper(HttpServletResponse response) {
        super(response);
        this.stream = new ByteArrayOutputStream();
        this.printWriter = new PrintWriter(this.stream);
        this.simpleServletOutputStream = new SimpleServletOutputStream(
                        this.stream);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return printWriter;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return simpleServletOutputStream;
    }

    @Override
    public String toString() {
        // flush printWriter before calling toString() on underlying stream
        printWriter.flush();
        return stream.toString();
    }
}
