package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author margaret = new Author("Margaret", "Weis");
        Book dragonlance = new Book("Dragons of Autumn Twilight", "12345");

        dragonlance.getAuthors().add(margaret);
        margaret.getBooks().add(dragonlance);

        authorRepository.save(margaret);
        bookRepository.save(dragonlance);

        Author jay = new Author("Jay", "Kristoff");
        Book darkdawn = new Book("DarkDawn", "234567");

        darkdawn.getAuthors().add(jay);
        jay.getBooks().add(darkdawn);

        authorRepository.save(jay);
        bookRepository.save(darkdawn);

        System.out.println("Number of Books: " + bookRepository.count());

        Publisher publisher = new Publisher("St. Martin's Press", "120 Broadway", "New York", "NY", "10271");
        darkdawn.setPublisher(publisher);
        publisher.getBooks().add(darkdawn);
        publisherRepository.save(publisher);

        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Publisher: " + publisher.toString());
    }
}
