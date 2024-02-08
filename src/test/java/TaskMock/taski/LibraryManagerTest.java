package TaskMock.taski;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class LibraryManagerTest {
    BookRepository bookRepository = Mockito.mock(BookRepository.class);
    @Mock
    Book book;
    Book bookNotEmpty = new Book("Test","Test","Test");
    List<Book> books = Arrays.asList(book,book);
    @InjectMocks
    LibraryManager libraryManager;
    @Test
    void findBookByIdTest() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.of(book));
        Optional<Book> expected = Optional.of(book);
        Optional<Book> actual = libraryManager.findBookById(Mockito.anyString());
        Mockito.verify(bookRepository).findById(Mockito.anyString());
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void findAllBooks() {
    Mockito.when(bookRepository.findAll()).thenReturn(books);
    List<Book> actual = libraryManager.findAllBooks();
    Mockito.verify(bookRepository).findAll();
    Assertions.assertEquals(books,actual);
    }

    @Test
    void addBookTest() {
        Mockito.when(bookRepository.save(bookNotEmpty)).thenReturn(bookNotEmpty);
        Assertions.assertEquals(bookNotEmpty,libraryManager.addBook(bookNotEmpty));
        Mockito.verify(bookRepository).save(bookNotEmpty);
    }
    @Test
    void addEmptyBookTest() {
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Assertions.assertThrows(IllegalArgumentException.class, () -> libraryManager.addBook(book));
    }

    @Test
    void deleteBook() {
        libraryManager.deleteBook(Mockito.anyString());
        Mockito.verify(bookRepository).deleteById(Mockito.anyString());
    }

    @Test
    void updateBookTest() {
        Mockito.when(bookRepository.existsById("Test")).thenReturn(true);
        Mockito.when(bookRepository.save(bookNotEmpty)).thenReturn(bookNotEmpty);
        Book updatedBook = libraryManager.updateBook("Test",bookNotEmpty);
        List<Book> expected = Collections.singletonList(bookNotEmpty);
        List<Book> actual = Collections.singletonList(updatedBook);
        Assertions.assertIterableEquals(expected,actual);
    }
    @Test
    void updateBookExceptionTest() {
        Mockito.when(bookRepository.existsById("Test")).thenReturn(false);
        Mockito.when(bookRepository.save(bookNotEmpty)).thenReturn(bookNotEmpty);
        Assertions.assertThrows(IllegalArgumentException.class,() -> libraryManager.updateBook("Test",bookNotEmpty));
    }

    @Test
    void findBooksByAuthorTest() {
        Mockito.when(bookRepository.findByAuthor(Mockito.anyString())).thenReturn(books);
        Assertions.assertEquals(books,libraryManager.findBooksByAuthor(Mockito.anyString()));

    }

    @Test
    void findBookByTitleTest() {
        Optional<Book> bookOptional = Optional.of(book);
        Mockito.when(bookRepository.findByTitle(Mockito.anyString())).thenReturn(bookOptional);
        Assertions.assertEquals(bookOptional,libraryManager.findBookByTitle(Mockito.anyString()));
        Mockito.verify(bookRepository).findByTitle(Mockito.anyString());
    }

    @Test
    void findBooksContainingTitleTest() {
        Mockito.when(bookRepository.findContainingTitle(Mockito.anyString())).thenReturn(books);
        Assertions.assertEquals(books,libraryManager.findBooksContainingTitle(Mockito.anyString()));
        Mockito.verify(bookRepository).findContainingTitle(Mockito.anyString());
    }

    @Test
    void lendBookTestScenarioIsNotLentTest() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.of(book));
        Mockito.when(book.isLent()).thenReturn(false);
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        libraryManager.lendBook(Mockito.anyString(),"UserTest");
        Mockito.verify(bookRepository).findById(Mockito.anyString());
        Mockito.verify(book).isLent();
        Mockito.verify(bookRepository).save(book);
    }
    @Test
    void lendBookTestScenarioIsLentTest() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.of(book));
        Mockito.when(book.isLent()).thenReturn(true);
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Assertions.assertThrows(IllegalStateException.class,() ->libraryManager.lendBook(Mockito.anyString(),
                "UserTest"));
        Mockito.verify(bookRepository).findById(Mockito.anyString());
        Mockito.verify(book).isLent();
    }

    @Test
    void returnBookIsLentTest() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.of(book));
        Mockito.when(book.isLent()).thenReturn(true);
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        libraryManager.returnBook(Mockito.anyString());
        Mockito.verify(bookRepository).findById(Mockito.anyString());
        Mockito.verify(book).isLent();
        Mockito.verify(bookRepository).save(book);
    }
    @Test
    void returnBookIsNotLentTest() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.of(book));
        Mockito.when(book.isLent()).thenReturn(false);
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Assertions.assertThrows(IllegalStateException.class,() -> libraryManager.returnBook(Mockito.anyString()));
        Mockito.verify(bookRepository).findById(Mockito.anyString());
        Mockito.verify(book).isLent();

    }
}