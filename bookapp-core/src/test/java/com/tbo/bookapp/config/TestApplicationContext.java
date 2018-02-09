package com.tbo.bookapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * General spring bean configurations for tests.
 * @author tai
 * @since 2/3/18.
 */
@Configuration
@Profile( "test" )
@ComponentScan(basePackages = {"com.tbo.bookapp"})
public class TestApplicationContext
{
}
