package com.tbo.bookapp.service;

import com.tbo.bookapp.domain.BookMetadata;
import com.tbo.bookapp.repository.BookMetadataRepository;

import java.util.List;

/**
 * Define data operations on or related to a {@link BookMetadata}
 * Delegate CRUD operations to a {@link BookMetadataRepository}
 * @author tai
 * @since 1/27/18.
 */
public interface BookMetadataService extends AppService<BookMetadata, Integer, BookMetadataRepository>
{
    BookMetadata findById( Integer id );
    BookMetadata findByIsbn( String isbn );
    List<BookMetadata> findByTitle( String title );
    List<BookMetadata> findByAuthor( String author );
    List<BookMetadata> findByGenre( String genre );
}
