package com.tbo.bookapp.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.Serializable;

/**
 * Here we declare common methods that are reusable across all domains
 * and that we provide our own implementations instead of using Spring
 * proxy implementations.
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.custom-implementations">
 *     spring repositories custom implementations </a>
 *
 * @author tai
 * @since 1/27/18.
 */
@NoRepositoryBean
public interface AppRepositoryExt<T, ID extends Serializable>
{
    NamedParameterJdbcTemplate getNamedParameterJdbcTemplate();
}
