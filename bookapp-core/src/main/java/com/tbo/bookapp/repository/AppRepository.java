package com.tbo.bookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Base contract for CRUD operations.
 * Common crud operations which apply across all domains and which
 * can take advantage of Spring data query derivation such as named query
 * are placed here.
 * @author tai
 * @since 1/27/18.
 */
@NoRepositoryBean
public interface AppRepository<T, ID extends Serializable> extends JpaRepository<T, ID>
{
}
