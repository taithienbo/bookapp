package com.tbo.bookapp.repository;

import com.tbo.bookapp.domain.BookContent;
import org.springframework.stereotype.Repository;

/**
 * CRUD operations on or relating to a {@link BookContent}
 * @author tai
 * @since 2/10/18.
 */
@Repository
public interface BookContentRepository extends AppRepository<BookContent, Integer>, BookContentRepositoryExt
{
    BookContent findByBookMetadata_Id( Integer bookId );
}
