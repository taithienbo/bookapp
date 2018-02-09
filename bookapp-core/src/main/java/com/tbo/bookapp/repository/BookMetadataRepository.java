package com.tbo.bookapp.repository;

import com.tbo.bookapp.domain.BookMetadata;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CRUD operations on or related to a {@link BookMetadata}, utilizing
 * Spring @Query and query derivation.
 * @author tai
 * @since 1/27/18.
 */
@Repository
public interface BookMetadataRepository extends AppRepository<BookMetadata, Integer>, BookMetadataRepositoryExt
{
    List<BookMetadata> findByAuthor( String author );
    BookMetadata findByIsbn( String isbn );
    List<BookMetadata> findByTitle( String title );
    List<BookMetadata> findByGenre( String genre );
}
