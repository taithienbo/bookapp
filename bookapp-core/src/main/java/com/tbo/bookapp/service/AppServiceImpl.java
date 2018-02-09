package com.tbo.bookapp.service;

import com.tbo.bookapp.repository.AppRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.transaction.Transactional;

/**
 * Base implementations of common data operations across all domains.
 * Mostly delegate the work to a {@link AppRepository}
 * @author tai
 * @since 1/27/18.
 */
public class AppServiceImpl<T, ID extends Serializable, R extends AppRepository<T, ID>> implements AppService<T, ID, R>
{
    private R repository;

    public AppServiceImpl( R repository ) {
        this.repository = repository;
    }

    @Override
    public R getRepository()
    {
        return repository;
    }

    @Override
    public T findById( ID id )
    {
        if ( id == null ) {
            throw new IllegalArgumentException("ID cannot be null.");
        }
        Optional<T> entityOptional = repository.findById( id );
        if ( ! entityOptional.isPresent() ) {
            throw new NoSuchElementException( "Entity with given id does not exist." );
        }
        return entityOptional.get();
    }

    @Override
    public T save( T entity )
    {
        if ( entity == null ) {
            throw new IllegalArgumentException( "Entity cannot be null." );
        }
        return getRepository().save( entity );
    }

    @Override
    public Collection<T> saveAll( Collection<T> entities )
    {
        if ( entities == null ) {
            throw new IllegalArgumentException( "List of entities cannot be null." );
        }
        return getRepository().saveAll( entities );
    }

    @Override
    public void delete( T entity )
    {
        if ( entity == null ) {
            throw new IllegalArgumentException( "Entity cannot be null." );
        }
        getRepository().delete( entity );
    }

}
