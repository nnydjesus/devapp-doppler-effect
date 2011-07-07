package ar.edu.unq.dopplereffect.presentation.util;

import java.io.Serializable;

public interface CallBackObject<T> extends Serializable {

    void execute(T object);
}