package com.tbo.bookapp.service;

import com.tbo.bookapp.domain.BookContent;
import com.tbo.bookapp.domain.BookMetadata;
import com.tbo.bookapp.repository.BookContentRepository;
import com.tbo.bookapp.repository.BookMetadataRepository;

/**
 * Define data operations on or related to a {@link BookContent}
 * Delegate CRUD operations to a {@link BookContentRepository}
 * @author tai
 * @since 1/27/18.
 */
public interface BookContentService extends AppService<BookContent, Integer, BookContentRepository>
{
    /**
     * Retrieve book content given the id referencing a {@link BookMetadata}
     * @param bookMetadataId id of the {@link BookMetadata}
     * @return the {@link BookContent}
     */
    BookContent findByBookMetadataId(Integer bookMetadataId );
}
