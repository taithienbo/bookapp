package com.tbo.bookapp.service;

import com.tbo.bookapp.domain.BookContent;
import com.tbo.bookapp.repository.BookContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tai
 * @since 2/10/18.
 */
@Service
@Transactional
public class BookContentServiceImpl extends AppServiceImpl<BookContent, Integer,BookContentRepository> implements
        BookContentService
{
    @Autowired
    public BookContentServiceImpl( BookContentRepository repository )
    {
        super( repository );
    }

    @Override
    public BookContent findByBookMetadataId( Integer bookMetadataId )
    {

        return getRepository().findByBookMetadata_Id( bookMetadataId );
    }
}
