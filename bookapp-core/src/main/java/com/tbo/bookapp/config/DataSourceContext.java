package com.tbo.bookapp.config;

import org.hibernate.SessionFactory;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Bean configurations for services having to do with jdbc and datasource.
 * @author tai
 * @since 1/27/18.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.tbo.bookapp")
@EnableTransactionManagement
public class DataSourceContext
{
    private static final Logger LOGGER = LoggerFactory.getLogger( DataSourceContext.class );

    @Autowired
    @Lazy
    private AppDatabasePropsLoader appDatabasePropsLoader;

    @Bean(destroyMethod = "")
    @Profile("!test")
    public DataSource dataSource() {
        try {
            Context context = new InitialContext(  );
            DataSource dataSource = ( DataSource ) context.lookup( appDatabasePropsLoader.getJndiDatasourceName() );
            return dataSource;
        } catch ( NamingException e ) {
            // unable to resolve data source info
            // log the exception
            LOGGER.error( "Failed to resolve data source.", e);
            return null;
        }
    }


    @Bean
    @Lazy
    @Profile( "!test" )
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl( false );

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter( vendorAdapter );
        factory.setPackagesToScan( "com.tbo.bookapp.domain" );
        factory.setDataSource( dataSource() );

        Properties properties = new Properties(  );
        properties.put( Environment.CURRENT_SESSION_CONTEXT_CLASS, org.hibernate.context.internal
                .ThreadLocalSessionContext.class);
        return factory;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate( dataSource() );
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager platformTransactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager(  );
        txManager.setEntityManagerFactory( entityManagerFactory().getObject());
        txManager.afterPropertiesSet();

        return txManager;
    }

    @Bean
    public SessionFactory sessionFactory() {
        return entityManagerFactory().getNativeEntityManagerFactory().unwrap( SessionFactory.class );
    }

}
