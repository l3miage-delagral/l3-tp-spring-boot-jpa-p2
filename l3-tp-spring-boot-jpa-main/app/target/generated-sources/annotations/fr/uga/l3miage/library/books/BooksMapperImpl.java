package fr.uga.l3miage.library.books;

import fr.uga.l3miage.library.authors.AuthorDTO;
import fr.uga.l3miage.library.data.domain.Author;
import fr.uga.l3miage.library.data.domain.Book;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2023-03-06T16:48:04+0100",
=======
    date = "2023-03-06T16:49:26+0100",
>>>>>>> 8e437ab00e61b08eb11e87185820b1da9514612c
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230213-1046, environment: Java 17.0.6 (Eclipse Adoptium)"
)
@Component
public class BooksMapperImpl implements BooksMapper {

    @Override
    public BookDTO entityToDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        long isbn = 0L;
        String publisher = null;
        short year = 0;
        String language = null;
        Collection<AuthorDTO> authors = null;

        id = book.getId();
        title = book.getTitle();
        isbn = book.getIsbn();
        publisher = book.getPublisher();
        year = book.getYear();
        language = enumToString( book.getLanguage() );
        authors = authorSetToAuthorDTOCollection( book.getAuthors() );

        BookDTO bookDTO = new BookDTO( id, title, isbn, publisher, year, language, authors );

        return bookDTO;
    }

    @Override
    public Collection<BookDTO> entityToDTO(Iterable<Book> books) {
        if ( books == null ) {
            return null;
        }

        Collection<BookDTO> collection = new ArrayList<BookDTO>();
        for ( Book book : books ) {
            collection.add( entityToDTO( book ) );
        }

        return collection;
    }

    @Override
    public Book dtoToEntity(BookDTO book) {
        if ( book == null ) {
            return null;
        }

        Book book1 = new Book();

        book1.setAuthors( authorDTOCollectionToAuthorSet( book.authors() ) );
        book1.setId( book.id() );
        book1.setIsbn( book.isbn() );
        book1.setLanguage( stringToEnum( book.language() ) );
        book1.setPublisher( book.publisher() );
        book1.setTitle( book.title() );
        book1.setYear( book.year() );

        return book1;
    }

    @Override
    public Collection<Book> dtoToEntity(Iterable<BookDTO> books) {
        if ( books == null ) {
            return null;
        }

        Collection<Book> collection = new ArrayList<Book>();
        for ( BookDTO bookDTO : books ) {
            collection.add( dtoToEntity( bookDTO ) );
        }

        return collection;
    }

    @Override
    public String enumToString(Book.Language language) {
        if ( language == null ) {
            return null;
        }

        String string;

        switch ( language ) {
            case ENGLISH: string = "english";
            break;
            case FRENCH: string = "french";
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + language );
        }

        return string;
    }

    @Override
    public Book.Language stringToEnum(String language) {
        if ( language == null ) {
            return Book.Language.FRENCH;
        }

        Book.Language language1;

        switch ( language ) {
            case "": language1 = Book.Language.FRENCH;
            break;
            case "english": language1 = Book.Language.ENGLISH;
            break;
            case "french": language1 = Book.Language.FRENCH;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + language );
        }

        return language1;
    }

    protected AuthorDTO authorToAuthorDTO(Author author) {
        if ( author == null ) {
            return null;
        }

        Long id = null;
        String fullName = null;

        id = author.getId();
        fullName = author.getFullName();

        AuthorDTO authorDTO = new AuthorDTO( id, fullName );

        return authorDTO;
    }

    protected Collection<AuthorDTO> authorSetToAuthorDTOCollection(Set<Author> set) {
        if ( set == null ) {
            return null;
        }

        Collection<AuthorDTO> collection = new ArrayList<AuthorDTO>( set.size() );
        for ( Author author : set ) {
            collection.add( authorToAuthorDTO( author ) );
        }

        return collection;
    }

    protected Author authorDTOToAuthor(AuthorDTO authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        Author author = new Author();

        author.setFullName( authorDTO.fullName() );
        author.setId( authorDTO.id() );

        return author;
    }

    protected Set<Author> authorDTOCollectionToAuthorSet(Collection<AuthorDTO> collection) {
        if ( collection == null ) {
            return null;
        }

        Set<Author> set = new LinkedHashSet<Author>( Math.max( (int) ( collection.size() / .75f ) + 1, 16 ) );
        for ( AuthorDTO authorDTO : collection ) {
            set.add( authorDTOToAuthor( authorDTO ) );
        }

        return set;
    }
}
