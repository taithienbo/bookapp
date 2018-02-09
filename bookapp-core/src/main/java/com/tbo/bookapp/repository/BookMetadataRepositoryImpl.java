package com.tbo.bookapp.repository;

import com.tbo.bookapp.domain.BookMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Implementations of extended CRUD operations on or related to a {@link BookMetadata}
 * by custom queries
 * @author tai
 * @since 1/27/18.
 */
public class BookMetadataRepositoryImpl extends AppRepositoryImpl<BookMetadata, Integer> implements
        BookMetadataRepositoryExt
{
    @Autowired
    public BookMetadataRepositoryImpl(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate )
    {
        super( namedParameterJdbcTemplate );
    }
}
