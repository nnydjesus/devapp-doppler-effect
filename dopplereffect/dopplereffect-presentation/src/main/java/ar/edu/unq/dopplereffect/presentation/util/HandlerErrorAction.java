package ar.edu.unq.dopplereffect.presentation.util;

import java.io.Serializable;

import ar.edu.unq.dopplereffect.presentation.pages.AbstractWebPage;

public abstract class HandlerErrorAction implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract void onExecute();

    public void execute() {
        AbstractWebPage.getManagerException().handlerAction(this);
    }
}