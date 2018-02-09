package com.tbo.bookapp;

import com.tbo.bookapp.dod.AppDataOnDemandImpl;
import com.tbo.bookapp.repository.AppRepository;
import com.tbo.bookapp.service.AppService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;

/**
 * Define shared utilities and operations among integration tests.
 * @author tai
 * @since 2/8/18.
 */
public abstract class AppIntegrationTest<T, ID extends Serializable, R extends AppRepository<T, ID>,
        S extends AppService<T, ID, R>, D extends AppDataOnDemandImpl<T, ID, R, S>> extends AppTestBase
{
    protected R repository;
    protected S service;
    protected D dataOnDemand;
    protected SessionFactory sessionFactory;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    public R getRepository()
    {
        return repository;
    }

    public abstract void setRepository( R repository );

    public S getService()
    {
        return service;
    }

    public abstract void setService( S service );

    public D getDataOnDemand() {
        return dataOnDemand;
    }

    public abstract void setDataOnDemand( D dataOnDemand );

    public abstract void setSessionFactory( SessionFactory sessionFactory );

    protected void clearSession() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        transaction.commit();
    }

}
