package ar.edu.unq.dopplereffect.presentation.util;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 */
public interface AjaxCallBack<T> extends Serializable {

    void execute(final AjaxRequestTarget ajaxTarget, final T component);
}
