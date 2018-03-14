package com.tbo.bookapp.dod;

import com.tbo.bookapp.domain.BookContent;
import com.tbo.bookapp.domain.BookMetadata;
import com.tbo.bookapp.repository.BookContentRepository;
import com.tbo.bookapp.service.BookContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

/**
 * Factual entity generator for a {@link BookContent}
 * @author tai
 * @since 2/10/18.
 */
@Service
public class BookContentDataOnDemand extends AppDataOnDemandImpl<BookContent, Integer,BookContentRepository,
        BookContentService>
{
    @Autowired
    private BookMetadataDataOnDemand bookMetadataDataOnDemand;

    private static final Logger LOGGER = LoggerFactory.getLogger( BookContentDataOnDemand.class );

    @Autowired
    private ApplicationContext context;

    @Autowired
    public BookContentDataOnDemand( BookContentRepository repository, BookContentService service )
    {
        super( repository, service, BookContent.class );
    }

    @Override
    public BookContent getNewTransient()
    {
        BookContent bookContent = new BookContent();
        bookContent.setBookMetadata( bookMetadataDataOnDemand.getNewTransient() );
        Blob content = getContentFromResource();
        bookContent.setContent( content );

        return bookContent;
    }

    /**
     * Helper method to read test pdf file from classpath and create blob data for test.
     * @return content from resource test file.
     */
    private Blob getContentFromResource()
    {
        try
        {
            InputStream inputStream = context.getResource( "pdf-sample.pdf" ).getInputStream();

            byte[] content = new byte[inputStream.available()];
            inputStream.read( content );
            return new SerialBlob( content );
        }
        catch ( IOException e )
        {
            LOGGER.error( "Failed to read pdf test file from classpath resource.", e);
            return null;
        }
        catch ( SQLException e )
        {
            LOGGER.error( "Failed to instantiate " + SerialBlob.class.getName() + " object.", e );
            return null;
        }
    }
}
