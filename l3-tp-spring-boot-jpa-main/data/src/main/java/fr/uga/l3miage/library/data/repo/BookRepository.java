package fr.uga.l3miage.library.data.repo;

import fr.uga.l3miage.library.data.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository implements CRUDRepository<Long, Book> {

    private final EntityManager entityManager;

    @Autowired
    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Book save(Book author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    public Book get(Long id) {
        return entityManager.find(Book.class, id);
    }

    
    @Override
    public void delete(Book author) {
        entityManager.remove(author);
    }

    /**
     * Renvoie tous les auteurs par ordre alphabétique
     * @return une liste de livres
     */
    public List<Book> all() {
        String jpql = "SELECT b FROM Book b ORDER BY b.title ASC";
        List<Book> res = entityManager.createQuery(jpql, Book.class)
        .getResultList();

        return res;
    }

    /**
     * Trouve les livres dont le titre contient la chaine passée (non sensible à la casse)
     * @param titlePart tout ou partie du titre
     * @return une liste de livres
     */
    public List<Book> findByContainingTitle(String titlePart) {
        // TODO créer les named query

        // String jpql = "SELECT a FROM Book a WHERE a.title ILIKE concat('%',:titlePart,'%')";

        // List<Book> res = entityManager.createQuery(jpql, Book.class)
        // .setParameter("titlePart", titlePart)
        // .getResultList();

        // return res;

        
        String jpql = "select b from Book b where b.title ilike concat('%',:titlePart,'%')";
        List<Book> res = entityManager.createQuery(jpql, Book.class)
                .setParameter("titlePart",titlePart)
                .getResultList();
        return res;
    }

    /**
     * Trouve les livres d'un auteur donnée dont le titre contient la chaine passée (non sensible à la casse)
     * @param authorId id de l'auteur
     * @param titlePart tout ou partie d'un titre de livré
     * @return une liste de livres
     */
    public List<Book> findByAuthorIdAndContainingTitle(Long authorId, String titlePart) {
        // List<Book> containTitle = findByContainingTitle(titlePart);
        
        String jpql = "SELECT b FROM Book b WHERE b.title LIKE '%' || :titlePart || '%' AND :authorId = INDEX(b.authors, 1) ORDER BY b.title ASC";
        return entityManager.createNamedQuery(jpql, Book.class)
                .setParameter("titlePart", titlePart)
                .setParameter("authorId", authorId)
                .getResultList();
    }

    /**
     * Recherche des livres dont le nom de l'auteur contient la chaine passée (non sensible à la casse)
     * @param namePart tout ou partie du nom
     * @return une liste de livres
     */
    public List<Book> findBooksByAuthorContainingName(String namePart) {
        // TODO créer les named query
        return entityManager.createNamedQuery("find-books-by-authors-name", Book.class)
                // TODO completer l'appel pour utiliser le paramètre de cette méthode
                .getResultList();
    }

    /**
     * Trouve des livres avec un nombre d'auteurs supérieur au compte donné
     * @param count le compte minimum d'auteurs
     * @return une liste de livres
     */
    public List<Book> findBooksHavingAuthorCountGreaterThan(int count) {
        // TODO créer les named query
        return entityManager.createNamedQuery("find-books-by-several-authors", Book.class)
                // TODO completer l'appel pour utiliser le paramètre de cette méthode
                .getResultList();
    }

}
