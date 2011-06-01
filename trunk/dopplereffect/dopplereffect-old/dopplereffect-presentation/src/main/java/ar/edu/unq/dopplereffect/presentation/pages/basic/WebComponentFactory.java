package ar.edu.unq.dopplereffect.presentation.pages.basic;

import java.io.Serializable;

public interface WebComponentFactory<T> extends Serializable {

    T createPage();

}
