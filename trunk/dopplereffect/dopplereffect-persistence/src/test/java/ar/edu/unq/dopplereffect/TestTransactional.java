package ar.edu.unq.dopplereffect;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations =
// "classpath:spring/config/aspects-context.xml")
// @ContextConfiguration(locations = "classpath:application-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public interface TestTransactional {

}
