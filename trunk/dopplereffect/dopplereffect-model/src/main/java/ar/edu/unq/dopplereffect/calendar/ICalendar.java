package ar.edu.unq.dopplereffect.calendar;

import java.io.Serializable;
import java.util.List;

public interface ICalendar<T> extends Serializable {

    Matrix<?, ?, ?> getCalendar(final List<T> list);
}
