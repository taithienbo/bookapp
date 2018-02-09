package com.tbo.bookapp.config;

import com.tbo.bookapp.config.DataSourceContext;
import org.h2.tools.Server;
import org.hsqldb.util.DatabaseManagerSwing;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Configurations for integration tests using embedded database.
 *
 * @see DataSourceContext
 * @author tai
 * @since 12/16/17.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.tbo.bookapp" )
@EnableTransactionManagement
@Profile( "test" )
public class TestDatasourceContext
{

    /**
     * Set up an embedded database which mimics an Oracle database
     * @return
     */
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder(  );
        DataSource dataSource = embeddedDatabaseBuilder.setType( EmbeddedDatabaseType.H2 ).setName( "testdb;" +
                "mode=Oracle" )
                .build();
        return dataSource;
    }


    /**
     * GUI for interacting with the embedded database, helpful for debugging.
     * @return
     * @throws SQLException
     */
    @Bean
    public Server startDbManager() throws SQLException {
        DatabaseManagerSwing.main(new String[]
                {
                        "--url", "jdbc:h2:mem:testdb"
                });
        Server server =  Server.createWebServer(  );
        server.start();
        return server;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl( false );

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter( vendorAdapter );
        factory.setPackagesToScan( "com.tbo.bookapp.domain" );
        factory.setDataSource( dataSource() );

        Properties properties = new Properties(  );
        properties.put( Environment.CURRENT_SESSION_CONTEXT_CLASS, org.hibernate.context.internal
                .ThreadLocalSessionContext.class.getName());
        // have Hibernate create the schema automatically
        properties.put( Environment.HBM2DDL_AUTO, "create");
        factory.setJpaProperties( properties );
        return factory;
    }

}
