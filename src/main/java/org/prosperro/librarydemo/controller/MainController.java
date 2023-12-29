package org.prosperro.librarydemo.controller;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.prosperro.librarydemo.model.Book;
import org.prosperro.librarydemo.model.Customer;
import org.prosperro.librarydemo.model.Library;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class MainController {

    private EntityManager entityManager;

    @Transactional
    @GetMapping("/")
    public String initDb() {
        Library library = setupLibrary();

        entityManager.persist(library);
        return library.toString();
    }

    @Transactional
    @PostMapping("/edit/{id}")
    public void updateLibrary(@PathVariable String id) {
        Library library = entityManager.find(Library.class, id);
        library.setName("Updated Library Name");
    }

    private static Library setupLibrary() {
        Book book = Book.builder()
                .title("The Hobbit")
                .author("J.R.R. Tolkien")
                .isbn("9780547928227")
                .publisher("Houghton Mifflin Harcourt")
                .publicationDate("2012")
                .genre("Fantasy")
                .language("English")
                .format("Hardcover")
                .pages("320")
                .dimensions("5.5 x 1.1 x 8.2 inches")
                .weight("1.1 pounds")
                .imageUrl("https://images-na.ssl-images-amazon.com/images/I/51o5n%2BQmBML._SX331_BO1,204,203,200_.jpg")
                .availability("Available")
                .build();
        Customer customer = Customer.builder()
                .firstName("John")
                .lastName("Doe")
                .email("email")
                .phoneNumber("1234567890")
                .address("123 Main St")
                .city("Anytown")
                .state("NY")
                .zipCode("12345")
                .country("USA")
                .build();
        Library library = Library.builder()
                .name("Main Library")
                .address("123 Main St")
                .city("Anytown")
                .state("NY")
                .zipCode("12345")
                .country("USA")
                .books(Set.of(book))
                .customers(Set.of(customer))
                .build();
        customer.setLibrary(library);
        book.setLibrary(library);
        return library;
    }
}
