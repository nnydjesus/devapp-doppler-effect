package ar.edu.unq.dopplereffect.presentation.util;

import org.apache.wicket.Component;

/**
 */
public interface ITable {

    void setResultSection(Component panel);

    void setParentPage(Component panel);

    Component getSortableAjaxWicket();

}
