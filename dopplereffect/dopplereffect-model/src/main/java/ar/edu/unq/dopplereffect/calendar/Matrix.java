package ar.edu.unq.dopplereffect.calendar;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections15.map.LinkedMap;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Matrix
 */
public class Matrix<X, Y, V> {

    /* ************************ INSTANCE VARIABLES ************************ */

    private static final Logger LOGGER = LoggerFactory.getLogger(Matrix.class);

    private LinkedMap<X, Map<Y, V>> map = new LinkedMap<X, Map<Y, V>>();

    /* *************************** CONSTRUCTORS *************************** */

    /* **************************** OPERATIONS **************************** */

    public void put(final X coordinateX, final Y coordinateY, final V value) {
        if (!this.getMap().containsKey(coordinateX)) {
            this.getMap().put(coordinateX, new LinkedMap<Y, V>());
        }
        this.getMap().get(coordinateX).put(coordinateY, value);
    }

    public V get(final X coordinateX, final Y coordinateY) {
        return this.getMap().get(coordinateX).get(coordinateY);
    }

    public Map<Y, V> getX(final X coordinateX) {
        return this.getMap().get(coordinateX);
    }

    public V getXY(final X coordinateX, final Y coordinateY) {
        return this.getMap().get(coordinateX).get(coordinateY);
    }

    public Set<X> keySet() {
        return this.getMap().keySet();
    }

    public Collection<Map<Y, V>> values() {
        return this.getMap().values();
    }

    public Set<Entry<X, Map<Y, V>>> getEntrySet() {
        return this.getMap().entrySet();
    }

    public boolean conteinsX(final X coordinateX) {
        return this.getMap().containsKey(coordinateX);
    }

    public boolean conteinsXY(final X coordinateX, final Y coordinateY) {
        return this.getMap().containsKey(coordinateX) && this.getMap().get(coordinateX).containsKey(coordinateY);
    }

    public boolean isEmpty() {
        return this.getMap().isEmpty();
    }

    /**
     * Metodo que no es del modelo!!! Solo esta para poder ver el calendario.
     * Una vez termidado el modelo este metodo sera eliminado
     */
    public void loggerMatrixCalendar() {
        final StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("\n            ");
        for (Object object : this.values().iterator().next().keySet()) {
            sBuilder.append(PrintDay.printDay((DateTime) object) + "       ");
        }
        sBuilder.append("\n");
        for (X x : this.keySet()) {
            sBuilder.append(x);
            for (V v : this.getX(x).values()) {
                sBuilder.append("        " + v + "       ");
            }
            sBuilder.append("\n");
        }
        LOGGER.info(sBuilder.toString());
    }

    /* **************************** ACCESSORS ***************************** */

    public void setMatrix(final LinkedMap<X, Map<Y, V>> matrix) {
        this.setMap(matrix);
    }

    public LinkedMap<X, Map<Y, V>> getMatrix() {
        return this.getMap();
    }

    public void setMap(final LinkedMap<X, Map<Y, V>> map) {
        this.map = map;
    }

    public LinkedMap<X, Map<Y, V>> getMap() {
        return map;
    }

}
