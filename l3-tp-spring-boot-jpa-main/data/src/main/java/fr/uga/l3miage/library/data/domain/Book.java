package fr.uga.l3miage.library.data.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookId")
    private Long id;
    
    @Column(name = "title", nullable = false)
    @NotNull
    @NotBlank
    private String title;

    @Column(name = "isbn",nullable = false)
    @NotNull
    @NotBlank
    private long isbn;

    @Column(name = "publisher", length = 30)
    private String publisher;

    @Column(name = "yearB")
    private short year;

    @Column(name = "language", length = 30)
    private Language language;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        if (this.authors == null) {
            this.authors = new HashSet<>();
        }
        this.authors.add(author);
    }

    public enum Language {
        FRENCH,
        ENGLISH
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn == book.isbn && year == book.year && Objects.equals(title, book.title) && Objects.equals(publisher, book.publisher) && language == book.language && Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, isbn, publisher, year, language, authors);
    }
}
