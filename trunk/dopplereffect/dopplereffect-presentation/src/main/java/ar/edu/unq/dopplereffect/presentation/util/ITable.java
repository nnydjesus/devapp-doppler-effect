package ar.edu.unq.dopplereffect.presentation.util;

import org.apache.wicket.Component;

import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;

public interface ITable {

    void setParentPage(AbstractSearchPanel<?> panel);

    Component getSortableAjaxWicket();

}
