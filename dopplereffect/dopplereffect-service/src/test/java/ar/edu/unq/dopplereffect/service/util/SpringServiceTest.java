package ar.edu.unq.dopplereffect.service.util;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Redefine lo que Spring necesita para poder levantarse y correr los tests.
 * Todos los tests de servicios deberian extender de esta clase.
 */
public abstract class SpringServiceTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override
    protected String[] getConfigLocations() {
        return new String[] { "/spring/services.xml", "/spring/repositories.xml", "/spring/data-source.xml",
                "/spring/transactions.xml", "/spring/reporting.xml" };
    }
}