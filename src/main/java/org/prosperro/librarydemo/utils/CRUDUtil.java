package org.prosperro.librarydemo.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.prosperro.librarydemo.model.Book;
import org.prosperro.librarydemo.model.Customer;
import org.prosperro.librarydemo.model.Library;

public class CRUDUtil {

    private static final String TITLE = "title";

    private static final String AUTHOR = "author";

    private static final String GENRE = "genre";

    private static final String ADDRESS = "address";

    private static final String SURNAME = "surname";

    private static final String EMAIL = "email";

    public static final String CITY = "city";

    private static final String STATE = "state";


    public  List<Library> setUpLibraryList(){
        List<Library> libraries = new ArrayList<>();
        int librariesCount = 10;
        int booksCount = 100000;
        int customerCount = 10;
        for (int i=0; i<librariesCount; i++){
            Library lib = generateLibraryWithBooksAndCustomers(i,booksCount,customerCount);
            libraries.add(lib);
        }
        return libraries;
    }


    public Library generateLibraryWithBooksAndCustomers(int identifier, int bookCount, int customerCount){
        Library library = Library.builder()
            .name(String.join("-", TITLE, String.valueOf(identifier)))
            .address(String.join("-", ADDRESS, String.valueOf(identifier)))
            .city(String.join("-", CITY, String.valueOf(identifier)))
            .state(String.join("-", STATE, String.valueOf(identifier)))
            .zipCode(String.join("-","zipCode",String.valueOf(identifier)))
            .country(String.join("-","UA"))
            .build();

        Set<Customer> customers = generateBatchOfCustomers(customerCount, library);
        library.setCustomers(customers);

        Set<Book> books = generateBatchOfBooks(bookCount, library);
        library.setBooks(books);

        return library;


    }



    private Set<Book> generateBatchOfBooks(int batchSize, Library library){
        Set<Book> books = new HashSet<>();
        for (int i=0;i<batchSize;i++){
            books.add(generateBook(i, library));
        }
        return books;
    }

    private Set<Customer> generateBatchOfCustomers(int batchSize, Library library){
        Set<Customer> customers = new HashSet<>();
        for (int i=0;i<batchSize;i++){
            customers.add(generateCustomer(i, library));
        }
        return customers;
    }


    private static Customer generateCustomer(int identifier, Library library) {
        String strIdentifier = String.valueOf(identifier);

        return Customer.builder()
            .firstName(String.join("-", TITLE, strIdentifier))
            .lastName(String.join("-", SURNAME, strIdentifier))
            .library(library)
            .email(String.join("-", EMAIL, strIdentifier))
            .address(String.join("-", ADDRESS, strIdentifier))
            .build();
    }

    private static Book generateBook(int identifier, Library library) {
        String strIdentifier = String.valueOf(identifier);

        return Book.builder()
            .title(String.join("-", TITLE, strIdentifier))
            .author(String.join("-", AUTHOR, strIdentifier))
            .genre(String.join("-", GENRE, strIdentifier))
            .availability("true")
            .pages(String.valueOf(identifier + 88))

            //.takenTime(new Date())
            .library(library)
// ????     .customer(getRandomCustomer(library.getCustomers()))
  //          .price(50)
            .build();
    }

    public static Customer getRandomCustomer(Set<Customer> customers) {
        int size = customers.size();
        int item = new Random().nextInt(size);
        int i = 0;
        for(Customer customer : customers) {
            if (i == item) {
                return customer;
            }
            i++;
        }
        throw new RuntimeException("Empty customers list");
    }

    private static Library generateLibrary(int identifier, Set<Book> books, Set<Customer> customers) {
        String strIdentifier = String.valueOf(identifier);

        return Library.builder()
            .name(String.join("-", TITLE, strIdentifier))
            .address(String.join("-", ADDRESS, strIdentifier))
            .books(books)
            .customers(customers)
            .build();

    }
}
