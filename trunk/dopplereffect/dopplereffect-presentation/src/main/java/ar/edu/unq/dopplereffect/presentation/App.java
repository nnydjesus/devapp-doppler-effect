package ar.edu.unq.dopplereffect.presentation;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import ar.edu.unq.dopplereffect.presentation.pages.MainPage;

public class App extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return MainPage.class;
    }
}