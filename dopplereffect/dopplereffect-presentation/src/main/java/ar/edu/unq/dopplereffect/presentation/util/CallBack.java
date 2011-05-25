package ar.edu.unq.dopplereffect.presentation.util;

import java.io.Serializable;

/**
 */
public interface CallBack<T> extends Serializable{

    void execute(T args);
}
