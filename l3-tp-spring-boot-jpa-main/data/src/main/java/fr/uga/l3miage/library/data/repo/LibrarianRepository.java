package fr.uga.l3miage.library.data.repo;

import fr.uga.l3miage.library.data.domain.Librarian;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        String jpql = "select b1_0.borrow_id,b1_0.borrower_person_id,b1_0.finished,b1_0.librarian_person_id,b1_0.requested_return,b1_0.start from Borrow b1_0 group by b1_0.librarian_person_id, b1_0.borrow_id order by count(b1_0.borrow_id) desc";

        return entityManager.createQuery(jpql, Librarian.class)
                .getResultList();
    }

}
