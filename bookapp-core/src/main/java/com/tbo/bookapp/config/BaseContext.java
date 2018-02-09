package com.tbo.bookapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Define base configurations, let spring scan our packages for other bean configurations.
 * @author tai
 * @since 1/27/18.
 */
@Configuration
@ComponentScan(basePackages = {"com.tbo.bookapp"})
@PropertySource( value = {"classpath:db.properties"} )
@Profile( "!test" )
public class BaseContext
{
}
