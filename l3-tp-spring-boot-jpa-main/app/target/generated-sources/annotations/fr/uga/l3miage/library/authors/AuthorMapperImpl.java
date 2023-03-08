package fr.uga.l3miage.library.authors;

import fr.uga.l3miage.library.data.domain.Author;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-08T13:04:09+0100",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230213-1046, environment: Java 17.0.6 (Eclipse Adoptium)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorDTO entityToDTO(Author author) {
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

    @Override
    public Collection<AuthorDTO> entityToDTO(Iterable<Author> authors) {
        if ( authors == null ) {
            return null;
        }

        Collection<AuthorDTO> collection = new ArrayList<AuthorDTO>();
        for ( Author author : authors ) {
            collection.add( entityToDTO( author ) );
        }

        return collection;
    }

    @Override
    public Author dtoToEntity(AuthorDTO author) {
        if ( author == null ) {
            return null;
        }

        Author author1 = new Author();

        author1.setFullName( author.fullName() );
        author1.setId( author.id() );

        return author1;
    }

    @Override
    public Collection<Author> dtoToEntity(Iterable<AuthorDTO> authors) {
        if ( authors == null ) {
            return null;
        }

        Collection<Author> collection = new ArrayList<Author>();
        for ( AuthorDTO authorDTO : authors ) {
            collection.add( dtoToEntity( authorDTO ) );
        }

        return collection;
    }
}
