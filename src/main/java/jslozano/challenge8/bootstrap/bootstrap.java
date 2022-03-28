package jslozano.challenge8.bootstrap;


import jslozano.challenge8.model.Book;
import jslozano.challenge8.model.Category;
import jslozano.challenge8.model.Status;
import jslozano.challenge8.model.Tag;
import jslozano.challenge8.repositories.BookRepository;
import jslozano.challenge8.repositories.CategoryRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public bootstrap(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        bookRepository.saveAll(getBooks());
    }

    private List<Book> getBooks(){
        List<Book> books = new ArrayList<>();
        // get categories and handle null references with Optional
        Optional<Category> actionCategoryOptional = categoryRepository.
                findByName("Action and Adventure");
        if(actionCategoryOptional.isEmpty()){
            throw new RuntimeException("Expected Category not found");
        }
        Optional<Category> mysteryCategoryOptional = categoryRepository.
                findByName("Detective and Mystery");
        if(mysteryCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected Category not found");
        }
        Optional<Category> fantasyCategoryOptional = categoryRepository.
                findByName("Fantasy");
        if(fantasyCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected Category not found");
        }
        Optional<Category> horrorCategoryOptional = categoryRepository.
                findByName("Horror");
        if(horrorCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected Category not found");
        }
        Category actionAndAdventureCategory = actionCategoryOptional.get();
        Category detectiveAndMysteryCategory = mysteryCategoryOptional.get();
        Category fantasyCategory = fantasyCategoryOptional.get();
        Category horrorCategory = horrorCategoryOptional.get();

        // Book Harry Potter chamber of secrets

        Book harryPotterBook = new Book();
        harryPotterBook.setName("Harry Potter Chamber of Secrets");
        harryPotterBook.setStatus(Status.AVALIABLE);

        Tag tag1 = new Tag();
        tag1.setName("Harry Potter");
        tag1.setBook(harryPotterBook);
        harryPotterBook.getTag().add(tag1);

        Tag tag2 = new Tag();
        tag2.setName("J.K Rowling");
        tag2.setBook(harryPotterBook);
        harryPotterBook.getTag().add(tag2);

        Tag tag3 = new Tag();
        tag3.setName("Hogwarts");
        tag3.setBook(harryPotterBook);
        harryPotterBook.getTag().add(tag3);

        harryPotterBook.getCategories().add(fantasyCategory);

        books.add(harryPotterBook);



        return books;

    }

}
