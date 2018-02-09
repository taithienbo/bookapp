package com.tbo.bookapp.service;

import com.tbo.bookapp.domain.BookMetadata;
import com.tbo.bookapp.AppIntegrationTest;
import com.tbo.bookapp.dod.BookMetadataDataOnDemand;
import com.tbo.bookapp.repository.BookMetadataRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

/**
 * Integration tests cover data operations on or related to {@link BookMetadata} entities.
 * @author tai
 * @since 2/3/18.
 */
public class BookMetadataServiceTest extends AppIntegrationTest<BookMetadata, Integer,BookMetadataRepository,
        BookMetadataService, BookMetadataDataOnDemand>
{
    @Autowired
    @Override
    public void setRepository( BookMetadataRepository repository )
    {
        this.repository = repository;
    }

    @Autowired
    @Override
    public void setService( BookMetadataService service )
    {
        this.service = service;
    }

    @Override
    @Autowired
    public void setDataOnDemand( BookMetadataDataOnDemand dataOnDemand )
    {
        this.dataOnDemand = dataOnDemand;
    }

    @Autowired
    @Override
    public void setSessionFactory( SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Test persisting a {@link BookMetadata}
     */
    @Test
    public void save() {
        BookMetadata bookMetadata = getDataOnDemand().getNewTransient();
        bookMetadata = getService().save( bookMetadata );
        Assert.assertNotNull("Id should not be null.", bookMetadata.getId());
        // clear session to ensure entity gets fetched from the database.
        clearSession();
        BookMetadata persistedBookMetadata = getService().findById( bookMetadata.getId() );
        Assert.assertNotNull( "Failed to persisted entity.", persistedBookMetadata );
    }

    /**
     * Test deleting a {@link BookMetadata}
     */
    @Test
    public void delete() {
        BookMetadata bookMetadata = getDataOnDemand().getNewPersisted();
        // delete the entity
        getService().delete( bookMetadata );
        clearSession();
        // verify entity no longer exist in the database.
        thrown.expect( NoSuchElementException.class);
        getService().findById( bookMetadata.getId() );
    }



}
