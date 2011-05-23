/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package ar.edu.unq.dopplereffect.presentation.pages;

import org.apache.wicket.markup.html.WebPage;

import ar.edu.unq.dopplereffect.presentation.component.PageLink;
import ar.edu.unq.dopplereffect.presentation.pages.employee.EmployeeSearchPage;
import ar.edu.unq.dopplereffect.presentation.pages.project.ProjectSearchPage;
import ar.edu.unq.dopplereffect.presentation.pages.project.SkillSearchPage;

/**
 * Simple home page.
 * 
 * @author Jonathan Locke
 */
public class Home extends WebPage {

    public Home() {
        this.add(new PageLink("projects", new ProjectSearchPage(this)));
        this.add(new PageLink("skills", new SkillSearchPage(this)));
        this.add(new PageLink("employee", new EmployeeSearchPage(this)));
    }
    // Nothing in here.
}
