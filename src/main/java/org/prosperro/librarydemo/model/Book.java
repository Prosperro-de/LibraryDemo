package org.prosperro.librarydemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "library")
@Setter
@Getter
@EqualsAndHashCode(exclude = "library")
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    @Column(name = "publication_date")
    private String publicationDate;
    private String genre;
    private String language;
    private String format;
    private String pages;
    private String dimensions;
    private String weight;
    private String description;
    @Column(name = "image_url")
    private String imageUrl;
    private String availability;
    @ManyToOne
    private Library library;
}
