package com.tbo.bookapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author tai
 * @since 11/24/17.
 */
@Entity( name = "BOOK_METADATA")
public class BookMetadata
{
    @Column( name = "ID")
    private Integer id;
    @Column( name = "ISBN" )
    private String isbn;
    @Column( name = "TITLE")
    private String title;
    @Column( name = "AUTHOR")
    private String author;
    @Column( name = "PRICE")
    private double price;
    @Column( name = "PUBLISHER")
    private String publisher;
    @Column( name = "PUBLISH_YEAR")
    private int publishYear;
    @Column( name = "PAGE_COUNT")
    private int pageCount;
    @Column( name = "GENRE")
    private String genre;
    
    public Integer getId()
    {
        return id;
    }

    public BookMetadata setId( Integer id )
    {
        this.id = id;
        return this;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public BookMetadata setIsbn( String isbn )
    {
        this.isbn = isbn;
        return this;
    }

    public String getTitle()
    {
        return title;
    }

    public BookMetadata setTitle( String title )
    {
        this.title = title;
        return this;
    }

    public String getAuthor()
    {
        return author;
    }

    public BookMetadata setAuthor( String author )
    {
        this.author = author;
        return this;
    }

    public double getPrice()
    {
        return price;
    }

    public BookMetadata setPrice( double price )
    {
        this.price = price;
        return this;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public BookMetadata setPublisher( String publisher )
    {
        this.publisher = publisher;
        return this;
    }

    public int getPublishYear()
    {
        return publishYear;
    }

    public BookMetadata setPublishYear( int publishYear )
    {
        this.publishYear = publishYear;
        return this;
    }
}
