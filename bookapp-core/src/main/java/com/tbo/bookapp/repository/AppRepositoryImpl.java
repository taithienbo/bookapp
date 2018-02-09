package com.tbo.bookapp.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.Serializable;

/**
 * Implementations for common CRUD operations by custom queries.
 * @author tai
 * @since 1/27/18.
 */
public class AppRepositoryImpl<T, ID extends Serializable> implements AppRepositoryExt<T, ID>
{
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AppRepositoryImpl(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate )
    {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public AppRepositoryImpl setNamedParameterJdbcTemplate(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate )
    {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        return this;
    }

    @Override
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate()
    {
        return namedParameterJdbcTemplate;
    }
}
