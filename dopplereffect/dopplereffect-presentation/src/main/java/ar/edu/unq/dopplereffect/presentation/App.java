package ar.edu.unq.dopplereffect.presentation;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import ar.edu.unq.dopplereffect.presentation.pages.Login;

public class App extends WebApplication {

    @Override
    protected void init() {
        super.init();
        this.getResourceSettings().addResourceFolder("pages");
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return Login.class;
    }
}