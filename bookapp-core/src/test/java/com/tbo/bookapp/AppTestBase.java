package com.tbo.bookapp;

import com.tbo.bookapp.config.TestApplicationContext;
import com.tbo.bookapp.config.TestDatasourceContext;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Common configurations for integration tests utilizing Spring.
 */
@RunWith( SpringRunner.class )
@ActiveProfiles("test")
@ContextConfiguration(classes = {TestApplicationContext.class})
public class AppTestBase
{

}
