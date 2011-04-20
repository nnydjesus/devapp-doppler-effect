package ar.edu.unq.dopplereffect.calendar;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections15.map.LinkedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Matrix
 */
public class Matrix<X, Y, V> {

    /* ************************ INSTANCE VARIABLES ************************ */

    private static final Logger LOGGER = LoggerFactory.getLogger(Matrix.class);

    private LinkedMap<X, Map<Y, V>> matrix = new LinkedMap<X, Map<Y, V>>();

    /* *************************** CONSTRUCTORS *************************** */

    public Matrix() {
    }

    /* **************************** OPERATIONS **************************** */

    public void put(final X x, final Y y, final V v) {
        if (!matrix.containsKey(x)) {
            matrix.put(x, new LinkedMap<Y, V>());
        }
        matrix.get(x).put(y, v);
    }

    public V get(final X x, final Y y) {
        return matrix.get(x).get(y);
    }

    public Map<Y, V> getX(final X x) {
        return matrix.get(x);
    }

    public V getXY(final X x, final Y y) {
        return matrix.get(x).get(y);
    }

    public Set<X> keySet() {
        return matrix.keySet();
    }

    public Collection<Map<Y, V>> values() {
        return matrix.values();
    }

    public boolean conteinsX(final X x) {
        return matrix.containsKey(x);
    }

    public boolean conteinsXY(final X x, final Y y) {
        return matrix.containsKey(x) && matrix.get(x).containsKey(y);
    }

    public boolean isEmpty() {
        return matrix.isEmpty();
    }

    public void loggerMatrix() {
        final StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("\n            ");
        for (Object map : this.values().iterator().next().keySet()) {
            sBuilder.append(map + "       ");
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
        this.matrix = matrix;
    }

    public LinkedMap<X, Map<Y, V>> getMatrix() {
        return matrix;
    }

}
