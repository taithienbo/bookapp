package com.tbo.bookapp.repository;

import com.tbo.bookapp.domain.BookContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Implementations of extended CRUD operations on or relating to a {@link BookContent}
 * @author tai
 * @since 2/10/18.
 */
public class BookContentRepositoryImpl extends AppRepositoryImpl<BookContent, Integer> implements BookContentRepositoryExt
{
    @Autowired
    public BookContentRepositoryImpl(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate )
    {
        super( namedParameterJdbcTemplate );
    }
}
