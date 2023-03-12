package fr.uga.l3miage.library.data.repo;

import fr.uga.l3miage.library.data.domain.Borrow;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Repository
public class BorrowRepository implements CRUDRepository<String, Borrow> {

    private final EntityManager entityManager;

    @Autowired
    public BorrowRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Borrow save(Borrow entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Borrow get(String id) {
        return entityManager.find(Borrow.class, id);
    }

    @Override
    public void delete(Borrow entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<Borrow> all() {
        return entityManager.createQuery("from Borrow", Borrow.class).getResultList();
    }

    /**
     * Trouver des emprunts en cours pour un emprunteur donné
     *
     * @param userId l'id de l'emprunteur
     * @return la liste des emprunts en cours
     */
    public List<Borrow> findInProgressByUser(Long userId) {
        return entityManager.createQuery("SELECT b FROM Borrow b WHERE b.borrower.id = :userId AND b.finished = false", Borrow.class)
        .setParameter("userId", userId)
        .getResultList();
    }

    /**
     * Compte le nombre total de livres emprunté par un utilisateur.
     *
     * @param userId l'id de l'emprunteur
     * @return le nombre de livre
     */
    public int countBorrowedBooksByUser(Long userId) {
        return entityManager.createQuery("SELECT COUNT(DISTINCT b.borrower.id) FROM Borrow b WHERE b.borrower.id = :userId", Long.class)
        .setParameter("userId", userId)
        .getSingleResult()
        .intValue();
    }

    /**
     * Compte le nombre total de livres non rendu par un utilisateur.
     *
     * @param userId l'id de l'emprunteur
     * @return le nombre de livre
     */
    public int countCurrentBorrowedBooksByUser(Long userId) {
        return entityManager.createQuery("SELECT COUNT(b) FROM Borrow b WHERE b.borrower.id = :userId AND b.returnedDate IS NULL", Long.class)
        .setParameter("userId", userId)
        .getSingleResult()
        .intValue();
    }

    /**
     * Recherche tous les emprunt en retard trié
     *
     * @return la liste des emprunt en retard
     */
    public List<Borrow> foundAllLateBorrow() {
        return entityManager.createQuery("SELECT b FROM Borrow b WHERE b.requestedReturn < CURRENT_DATE AND b.finished = false ORDER BY b.requestedReturn", Borrow.class)
        .getResultList();
    }

    /**
     * Calcul les emprunts qui seront en retard entre maintenant et x jours.
     *
     * @param days le nombre de jour avant que l'emprunt soit en retard
     * @return les emprunt qui sont bientôt en retard
     */
    public List<Borrow> findAllBorrowThatWillLateWithin(int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dueDate = now.plusDays(days);
        Date dueDateAsDate = Date.from(dueDate.atZone(ZoneId.systemDefault()).toInstant());

        return entityManager.createQuery("SELECT b FROM Borrow b WHERE b.finished = false AND b.requestedReturn BETWEEN CURRENT_TIMESTAMP AND :dueDate ORDER BY b.requestedReturn", Borrow.class)
                .setParameter("dueDate", dueDateAsDate)
                .getResultList();
    }

}
