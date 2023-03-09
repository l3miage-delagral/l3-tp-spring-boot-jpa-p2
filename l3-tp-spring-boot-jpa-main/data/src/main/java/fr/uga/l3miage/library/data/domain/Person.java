package fr.uga.l3miage.library.data.domain;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "personId")
    private String id;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "fistName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birth;

    public enum Gender {
        FEMALE, MALE, FLUID
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return gender == person.gender && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(birth, person.birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, firstName, lastName, birth);
    }
}
