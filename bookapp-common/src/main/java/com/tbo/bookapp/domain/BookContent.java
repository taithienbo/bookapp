package com.tbo.bookapp.domain;

import org.springframework.cglib.core.GeneratorStrategy;

import java.sql.Blob;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * @author tai
 * @since 11/24/17.
 */
@Entity( name = "BOOK_CONTENT")
public class BookContent
{
    @Column( name = "CONTENT")
    private Blob content;
    @OneToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "BOOK_METADATA_ID")
    private BookMetadata bookMetadata;

    @Column( name = "ID" )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    public Blob getContent()
    {
        return content;
    }

    public BookContent setContent( Blob content )
    {
        this.content = content;
        return this;
    }

    public BookMetadata getBookMetadata()
    {
        return bookMetadata;
    }

    public BookContent setBookMetadata( BookMetadata bookMetadata )
    {
        this.bookMetadata = bookMetadata;
        return this;
    }

    public Integer getId()
    {
        return id;
    }

    public BookContent setId( Integer id )
    {
        this.id = id;
        return this;
    }
}
