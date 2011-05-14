package ar.edu.unq.dopplereffect.persistence.util;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class CustomHibernateRepositorySupport extends HibernateDaoSupport {
    @Autowired
    public void anyMethodName(final SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
}