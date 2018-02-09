package com.tbo.bookapp.service;

import com.tbo.bookapp.domain.BookMetadata;
import com.tbo.bookapp.repository.BookMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tai
 * @since 2/3/18.
 */
@Service
@Transactional
public class BookMetadataServiceImpl extends AppServiceImpl<BookMetadata, Integer, BookMetadataRepository> implements
        BookMetadataService
{
    @Autowired
    public BookMetadataServiceImpl( BookMetadataRepository repository )
    {
        super( repository );
    }


    @Override
    public BookMetadata findByIsbn( String isbn )
    {
        if ( isbn == null ) {
            throw new IllegalArgumentException( "Isbn cannot be null." );
        }
        return getRepository().findByIsbn( isbn );
    }

    @Override
    public List<BookMetadata> findByTitle( String title )
    {
        if ( title == null ) {
            throw new IllegalArgumentException( "Title cannot be null." );
        }
        return getRepository().findByTitle( title );
    }

    @Override
    public List<BookMetadata> findByAuthor( String author )
    {
        if ( author == null ) {
            throw new IllegalArgumentException( "Author cannot be null." );
        }
        return getRepository().findByAuthor( author );
    }

    @Override
    public List<BookMetadata> findByGenre( String genre )
    {
        if ( genre == null ) {
            throw new IllegalArgumentException( "Genre cannot be null." );
        }
        return getRepository().findByGenre( genre );
    }
}
