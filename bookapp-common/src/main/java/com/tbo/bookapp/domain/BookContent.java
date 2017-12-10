package com.tbo.bookapp.domain;

import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author tai
 * @since 11/24/17.
 */
@Entity( name = "BOOK_CONTENT")
public class BookContent
{
    @Column( name = "CONTENT")
    private Blob content;
    @Column( name = "BOOK_ID")
    private Integer bookId;
    @Column( name = "ID" )
    private Integer id;


}
