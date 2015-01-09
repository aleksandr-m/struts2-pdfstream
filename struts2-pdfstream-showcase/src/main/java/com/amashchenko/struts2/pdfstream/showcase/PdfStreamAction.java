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
package com.amashchenko.struts2.pdfstream.showcase;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Simple action to demonstrate use of PDF Stream plugin.
 * 
 * @author Aleksandr Mashchenko
 * 
 */
public class PdfStreamAction extends ActionSupport {
    private static final long serialVersionUID = -2508030433084720118L;

    private List<Integer> list;

    public String createList() {
        list = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        return SUCCESS;
    }

    /**
     * @return the list
     */
    public List<Integer> getList() {
        return list;
    }

    /**
     * @param list
     *            the list to set
     */
    public void setList(List<Integer> list) {
        this.list = list;
    }
}
