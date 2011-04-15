package ar.edu.unq.dopplereffect.bean;

import static junit.framework.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.junit.Test;

import ar.edu.unq.dopplereffect.calendar.Matrix;

public class MatrixTest {

    private static final String x = "x", y = "y";

    private static final Integer v = 1;

    protected Matrix<String, String, Integer> getMatrix() {
        return new Matrix<String, String, Integer>();
    }

    @Test
    public void testPutAndGet() {
    	final Matrix<String, String, Integer> matrix = this.getMatrix();
        matrix.put(x, y, v);
        assertEquals("La matriz deveria devolver el valor v", v, matrix.get(x, y));
    }

    @Test
    public void testConteinsX() {
        final Matrix<String, String, Integer> matrix = this.getMatrix();
        matrix.put(x, y, v);
        assertTrue("Ma matriz deberia tener como clave al objeto X", matrix.conteinsX(x));
    }

    @Test
    public void testConteinsXY() {
    	final Matrix<String, String, Integer> matrix = this.getMatrix();
        matrix.put(x, y, v);
        assertTrue("Ma matriz deberia tener como clave a X  dentro de X commo clave a Y ", matrix.conteinsXY(x, y));
    }
}
