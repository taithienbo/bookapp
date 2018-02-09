package com.tbo.bookapp.dod;

import com.tbo.bookapp.repository.AppRepository;
import com.tbo.bookapp.service.AppService;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.FieldDefinitionBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Id;

/**
 * Base implementation for generating factual entity data.
 * @author tai
 * @since 2/9/18.
 */
public class AppDataOnDemandImpl<T, ID extends Serializable, R extends AppRepository<T, ID>, S extends AppService<T,
        ID, R>>
{
    private R repository;

    private S service;

    private Class<T> entityType;

    private EnhancedRandom enhancedRandom;

    public AppDataOnDemandImpl(R repository, S service, Class<T> entityType )
    {
        this.repository = repository;
        this.service = service;
        // ignore id field since id gets generated on persisting to the database.
        // ignore collection types since that create opportunities for bad codes that could lead to Stack Overflow
        enhancedRandom = new EnhancedRandomBuilder().exclude( new FieldDefinitionBuilder()
                .isAnnotatedWith( Id.class ).get()).exclude( new FieldDefinitionBuilder().ofType( Collection.class )
                .get() )
                // the library has trouble generating Blob type.
                .exclude( new FieldDefinitionBuilder().ofType( Blob.class ).get() ).build();
        this.entityType = entityType;

    }

    /**
     * Generate a factual un-persisted entity.
     * @return the transient entity.
     */
    public T getNewTransient()
    {
        T entity = enhancedRandom.nextObject( entityType, excludeFields() );
        return entity;
    }

    /**
     * Generate and persist a factual entity into the database.
     * @see #getNewTransient()
     * @return the persisted entity.
     */
    public T getNewPersisted()
    {
        return getService().save( getNewTransient() );
    }

    /**
     * Generate a collection of factual, un-persisted entities.
     * @return collection of entities.
     */
    public Collection<T> getManyNewTransient()
    {
        Integer randomSize = Math.max(2, enhancedRandom.nextInt( 5 ) );
        Collection<T> entities = new ArrayList<>(  );
        for ( int i = 0; i < randomSize; i++ ) {
            entities.add( getNewTransient() );
        }
        return entities;
    }

    /**
     * Generate a collection of factual entities and persist into the datbase.
     * @return the collection of entities.
     */
    public Collection<T> getManyNewPersisted()
    {
        return getService().saveAll( getManyNewTransient() );
    }

    /**
     * Specify the fields to ignore when generating the entity.
     * @return the fields to ignore.
     */
    public String[] excludeFields()
    {
        return new String[]{};
    }

    public R getRepository()
    {
        return repository;
    }

    public S getService()
    {
        return service;
    }

    public EnhancedRandom getEnhancedRandom()
    {
        return enhancedRandom;
    }

    public AppDataOnDemandImpl setEnhancedRandom( EnhancedRandom enhancedRandom )
    {
        this.enhancedRandom = enhancedRandom;
        return this;
    }
}
