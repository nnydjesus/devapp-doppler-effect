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
package ar.edu.unq.dopplereffect.presentation.panel.upload;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.file.Folder;
import org.odlabs.wiquery.core.events.Event;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.events.WiQueryEventBehavior;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.ui.button.ButtonBehavior;

import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.util.Model;

/**
 * 
 */
public class UploadPanel<T> extends Panel {
    private static final long serialVersionUID = 1L;

    // }

    private SearchModel<T> searchModel;

    private Folder uploadFolder = new Folder("C:/dev-app/download/", "DopplerEffect Export");

    private FeedbackPanel uploadFeedback;

    private DialogSelectedTypeToExport dialogSelectedTypeToExport;

    public UploadPanel(final String id, final SearchModel<T> searchModel) {
        super(id, new Model<String>(""));
        this.searchModel = searchModel;

        // crea el directorio si no existe
        uploadFolder.mkdirs();

        uploadFeedback = new FeedbackPanel("uploadFeedback");
        uploadFeedback.setOutputMarkupId(true);

        this.add(uploadFeedback);
        setDialogSelectedTypeToExport(new DialogSelectedTypeToExport("selectType", this));
        getDialogSelectedTypeToExport().setTitle(new StringResourceModel("selectType.title", new Model<String>()));
        this.add(getDialogSelectedTypeToExport());
        this.addButtons();

    }

    protected void addButtons() {
        WebMarkupContainer ajaxButton = new WebMarkupContainer("aceptar");
        ajaxButton.add(new WiQueryEventBehavior(new Event(MouseEvent.CLICK) {
            private static final long serialVersionUID = 1L;

            @Override
            public JsScope callback() {
                return JsScope.quickScope(getDialogSelectedTypeToExport().open().render());
            }

        }));
        ajaxButton.add(new ButtonBehavior());
        this.add(ajaxButton);

    }

    public void onExport(final AjaxRequestTarget target, final String type) {
        String pathFile = uploadFolder.getAbsolutePath() + "/" + searchModel.getEntityType().getSimpleName() + type;
        try {
            searchModel.export(pathFile);
            UploadPanel.this.info("el archivo " + pathFile + " se a exportado con exito");
        } catch (Exception e) {
            UploadPanel.this.error("No se pudo exportar el archivo");
        } finally {
            target.addComponent(uploadFeedback);
        }
    }

    public void setDialogSelectedTypeToExport(DialogSelectedTypeToExport dialogSelectedTypeToExport) {
        this.dialogSelectedTypeToExport = dialogSelectedTypeToExport;
    }

    public DialogSelectedTypeToExport getDialogSelectedTypeToExport() {
        return dialogSelectedTypeToExport;
    }

}