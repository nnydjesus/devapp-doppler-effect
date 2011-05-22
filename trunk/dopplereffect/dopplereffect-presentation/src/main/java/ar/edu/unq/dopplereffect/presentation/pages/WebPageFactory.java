package ar.edu.unq.dopplereffect.presentation.pages;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;

public interface WebPageFactory extends Serializable {

    WebPage createPage();

}
