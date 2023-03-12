package fr.uga.l3miage.library.data.repo;

import fr.uga.l3miage.library.data.domain.Librarian;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LibrarianRepository implements CRUDRepository<String, Librarian> {

    private final EntityManager entityManager;

    @Autowired
    public LibrarianRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Librarian save(Librarian entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Librarian get(String id) {
        return entityManager.find(Librarian.class, id);
    }

    @Override
    public void delete(Librarian entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<Librarian> all() {
        return entityManager.createQuery("from Librarian", Librarian.class).getResultList();
    }

    /**
     * Récupere les bibliothéquaires ayant enregistré le plus de prêts
     * @return les bibliothéquaires les plus actif
     */
    public List<Librarian> top3WorkingLibrarians() {
            String jpql = "SELECT b.librarian, COUNT(b) FROM Borrow b GROUP BY b.librarian ORDER BY COUNT(b) DESC";
        
            List<Librarian> librarians = entityManager.createQuery(jpql, Object[].class)
                    .setMaxResults(3)
                    .getResultList()
                    .stream()
                    .map(obj -> (Librarian) obj[0])
                    .collect(Collectors.toList());
        
            return librarians;
        }
        
}
