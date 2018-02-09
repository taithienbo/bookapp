package com.tbo.bookapp.service;

import com.tbo.bookapp.repository.AppRepository;

import java.util.Collection;

/**
 * Define common data operations across all domains.
 * @author tai
 * @since 1/27/18.
 */
public interface AppService<T, ID, R extends AppRepository>
{
    R getRepository();
    T findById( ID id );
    T save(T entity );
    Collection<T> saveAll( Collection<T> entities );
    void delete( T entity );
}
