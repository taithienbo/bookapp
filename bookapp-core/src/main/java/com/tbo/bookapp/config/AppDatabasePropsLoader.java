package com.tbo.bookapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tai
 * @since 1/27/18.
 */
@Component
public class AppDatabasePropsLoader
{
    @Value("jndi.datasource.name")
    private String jndiDatasourceName;

    public String getJndiDatasourceName() {
        return jndiDatasourceName;
    }

    public AppDatabasePropsLoader setJndiDatasourceName( String jndiDatasourceName )
    {
        this.jndiDatasourceName = jndiDatasourceName;
        return this;
    }
}
