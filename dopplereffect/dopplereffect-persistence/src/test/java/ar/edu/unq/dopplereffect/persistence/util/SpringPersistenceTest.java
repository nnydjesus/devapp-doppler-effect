package ar.edu.unq.dopplereffect.persistence.util;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * 
 * XXX esto es un test ???  que funcion tiene ?
 */
public abstract class SpringPersistenceTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override
    protected String[] getConfigLocations() {
        return new String[] { "/spring/data-source.xml", "/spring/transactions.xml", "/spring/repositories.xml" };
    }
}
