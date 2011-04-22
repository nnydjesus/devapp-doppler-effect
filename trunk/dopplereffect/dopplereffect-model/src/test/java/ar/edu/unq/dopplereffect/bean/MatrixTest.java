package ar.edu.unq.dopplereffect.bean;

import junit.framework.Assert;

import org.junit.Test;

import ar.edu.unq.dopplereffect.calendar.Matrix;

public class MatrixTest {

    private static final String CORRDINATE_X = "x", CORRDINATE_Y = "y";

    private static final Integer VALUE = 1;

    protected Matrix<String, String, Integer> getMatrix() {
        return new Matrix<String, String, Integer>();
    }

    @Test
    public void testPutAndGet() {
        final Matrix<String, String, Integer> matrix = this.getMatrix();
        matrix.put(CORRDINATE_X, CORRDINATE_Y, VALUE);
        Assert.assertEquals("La matriz deveria devolver el valor v", VALUE, matrix.get(CORRDINATE_X, CORRDINATE_Y));
    }

    @Test
    public void testConteinsX() {
        final Matrix<String, String, Integer> matrix = this.getMatrix();
        matrix.put(CORRDINATE_X, CORRDINATE_Y, VALUE);
        Assert.assertTrue("Ma matriz deberia tener como clave al objeto X", matrix.conteinsX(CORRDINATE_X));
    }

    @Test
    public void testConteinsXY() {
        final Matrix<String, String, Integer> matrix = this.getMatrix();
        matrix.put(CORRDINATE_X, CORRDINATE_Y, VALUE);
        Assert.assertTrue("Ma matriz deberia tener como clave a X  dentro de X commo clave a Y ", matrix.conteinsXY(CORRDINATE_X, CORRDINATE_Y));
    }
}
