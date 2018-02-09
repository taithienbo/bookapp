package com.tbo.bookapp.service;

import com.tbo.bookapp.AppIntegrationTest;
import com.tbo.bookapp.dod.BookContentDataOnDemand;
import com.tbo.bookapp.domain.BookContent;
import com.tbo.bookapp.repository.BookContentRepository;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

/**
 * Integration tests cover data operations on or relating to a {@link BookContent}
 *
 * @author tai
 * @since 2/11/18.
 */
public class BookContentServiceTest extends AppIntegrationTest<BookContent, Integer,BookContentRepository,
        BookContentService,BookContentDataOnDemand>
{
    @Autowired
    @Override
    public void setRepository( BookContentRepository repository )
    {
        this.repository = repository;
    }

    @Autowired
    @Override
    public void setService( BookContentService service )
    {
        this.service = service;
    }

    @Autowired
    @Override
    public void setDataOnDemand( BookContentDataOnDemand dataOnDemand )
    {
        this.dataOnDemand = dataOnDemand;
    }

    @Autowired
    @Override
    public void setSessionFactory( SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }

    @Test
    public void save() {
        BookContent bookContent = getDataOnDemand().getNewTransient();
        bookContent = getService().save( bookContent );
        Assert.assertNotNull( "Id cannot be null.", bookContent.getId() );
        // clear session to ensure a hit to the database instead of the cache, for verifying entity has been saved
        clearSession();
        BookContent persistedBookContent = getService().findById( bookContent.getId() );
        Assert.assertNotNull( "Failed to persisted entity.", bookContent );
    }

    @Test
    public void delete() {
        BookContent persistedBookContent = getDataOnDemand().getNewPersisted();
        getService().delete( persistedBookContent );
        clearSession();
        // verify entity no longer exist in the database.
        thrown.expect( NoSuchElementException.class);
        getService().findById( persistedBookContent.getId() );
    }
}
