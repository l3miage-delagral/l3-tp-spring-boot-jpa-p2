package fr.uga.l3miage.library.data.domain;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue(value = "librarian")
public class Librarian extends Person {

<<<<<<< HEAD
    // @Column(name = "manager")
=======
    @PrimaryKeyJoinColumn(name = "manager")
>>>>>>> 44586341d1cade17d05659048d6868757218c099
    @OneToOne
    private Librarian manager;

    public Librarian getManager() {
        return manager;
    }

    public void setManager(Librarian manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Librarian librarian)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(manager, librarian.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), manager);
    }
}
