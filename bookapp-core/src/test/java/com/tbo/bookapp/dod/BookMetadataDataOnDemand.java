package com.tbo.bookapp.dod;

import com.tbo.bookapp.domain.BookMetadata;
import com.tbo.bookapp.repository.BookMetadataRepository;
import com.tbo.bookapp.service.BookMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Factual entity generator for {@link BookMetadata}
 * @author tai
 * @since 2/9/18.
 */
@Service
public class BookMetadataDataOnDemand extends AppDataOnDemandImpl<BookMetadata, Integer,BookMetadataRepository,
        BookMetadataService>
{
    @Autowired
    public BookMetadataDataOnDemand( BookMetadataRepository repository, BookMetadataService service )
    {
        super( repository, service, BookMetadata.class );
    }

    @Override
    public String[] excludeFields()
    {
        return new String[]{"bookContent"};
    }
}
