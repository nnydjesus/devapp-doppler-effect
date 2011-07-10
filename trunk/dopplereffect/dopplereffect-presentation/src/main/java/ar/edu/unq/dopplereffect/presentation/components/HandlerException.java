package ar.edu.unq.dopplereffect.presentation.components;

import org.apache.wicket.Component;

import ar.edu.unq.dopplereffect.exceptions.UserException;
import ar.edu.unq.dopplereffect.presentation.pages.AbstractWebPage;
import ar.edu.unq.dopplereffect.presentation.util.HandlerErrorAction;

/**
 */
public class HandlerException {

    private AbstractWebPage<? extends Component> webPage;

    public HandlerException(final AbstractWebPage<? extends Component> abstractWebPage) {
        webPage = abstractWebPage;
    }

    public void handlerAction(final HandlerErrorAction callBack) {
        try {
            callBack.onExecute();
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            webPage.showError(e);
        }
    }

    public void setWebPage(final AbstractWebPage<? extends Component> webPage) {
        this.webPage = webPage;
    }

    public AbstractWebPage<? extends Component> getWebPage() {
        return webPage;
    }

}
