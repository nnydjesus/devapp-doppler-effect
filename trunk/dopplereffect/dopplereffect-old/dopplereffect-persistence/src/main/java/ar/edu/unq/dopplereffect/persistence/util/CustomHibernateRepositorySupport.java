package ar.edu.unq.dopplereffect.persistence.util;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class CustomHibernateRepositorySupport extends HibernateDaoSupport implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    public void anyMethodName(final SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
}