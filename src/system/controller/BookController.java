package system.controller;

import static system.models.Constants.BOOKS;
import static system.models.Constants.MENU;
import static system.models.Constants.SCANNER;
import static system.utils.ScannerUtil.checkNumber;
import static system.utils.SearchUtil.indexingOfList;
import static system.utils.SearchUtil.indexingOfObject;
import static system.utils.SearchUtil.listAllDataByCompare;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import system.comparator.BookAuthorNameComparator;
import system.comparator.BookAuthorSurnameComparator;
import system.comparator.BookTitleComparator;
import system.models.Book;
import system.utils.SearchUtil;

/**
 * The BookController class is responsible for managing the search and listing
 * of books. It contains methods for searching books by various criteria such as
 * ID, code, title, genre, author name, and author surname. It also has methods
 * for listing all books by title, author name, and author surname. Finally, the
 * class initializes four maps to store the indexed values of books by title,
 * genre, author name, and author surname.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class BookController {

    private final Map<String, Integer[]> indexedGenreValue;
    private final Map<String, Integer[]> indexedAuthorNameValue;
    private final Map<String, Integer[]> indexedAuthorSurnameValue;

    public BookController() {
        indexedGenreValue = new HashMap<>();
        indexedAuthorNameValue = new HashMap<>();
        indexedAuthorSurnameValue = new HashMap<>();
        prepareSearchData();
    }

    /**
     * Searches for a book based on the user's selected option.
     *
     * @param option The user's selected option.
     *               1: Search by ID
     *               2: Search by code
     *               3: Search by title
     *               4: Search by genre
     *               5: Search by author name
     *               6: Search by author surname
     *               7: List all books by title
     *               8: List all books by author name
     *               9: List all books by author surname
     */
    public void search(Integer option) {
        switch (option) {
            case 1:
                searchById();
                break;
            case 2:
                searchByCode();
                break;
            case 3:
                searchByTitle();
                break;
            case 4:
                MENU.printGenreOptions();
                searchByGenre();
                break;
            case 5:
                searchByAuthorName();
                break;
            case 6:
                searchByAuthorSurname();
                break;
            case 7:
                listAllBookByTitle();
                break;
            case 8:
                listAllBookByAuthorName();
                break;
            case 9:
                listAllBookByAuthorSurname();
                break;
        }
    }

    /**
     * Searches for a book by its ID number and prints the book's information if
     * found. If no book is found, prints a message indicating that no book was
     * found.
     */
    private void searchById() {
        Integer id = checkNumber("Search by ID: ");
        Integer index = SearchUtil.find(BOOKS, id);
        if (index != -1) {
            printTableLabels();
            System.out.println(BOOKS.get(index));
        } else {
            System.out.println("Couldn't find any book with an ID number: '" + id + "'");
        }
    }

    /**
     * Searches for a book by its code and prints the book information if found.
     * If no book is found, prints a message indicating that no book was found.
     */
    private void searchByCode() {
        System.out.println("Search by code: ");
        String code = SCANNER.nextLine().toLowerCase();
        Optional<Book> foundBook = BOOKS.stream()
                .filter(book -> book.getCode().equals(code))
                .findFirst();
        if (foundBook.isPresent()) {
            printTableLabels();
            System.out.println(foundBook.get());
        } else {
            System.out.println("Couldn't find any book with a code: '" + code + "'");
        }
    }

    /**
     * Searches for a book by its title and prints the book information if
     * found. If no book is found, prints a message indicating that no book was
     * found.
     */
    private void searchByTitle() {
        System.out.println("Search by title: ");
        String title = SCANNER.nextLine().toLowerCase();
        List<Book> found = BOOKS.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title))
                .collect(Collectors.toList());
        if (!found.isEmpty()) {
            printTableLabels();
            found.forEach(System.out::println);
        } else {
            System.out.println("Couldn't find any book with a title: '" + title + "'");
        }
    }

    /**
     * Searches for a book by genre and prints the book information if found. If
     * no book is found, prints a message indicating that no book was found.
     */
    private void searchByGenre() {
        System.out.println("Search by genre: ");
        String genre = SCANNER.nextLine().toLowerCase();
        Integer[] indexes = indexedGenreValue.get(genre);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any book with a title: '" + genre + "'");
        }
    }

    /**
     * Searches for a book by author name and prints the book information if
     * found. If no book is found, prints a message indicating that no book was
     * found.
     */
    private void searchByAuthorName() {
        System.out.println("Search by author name: ");
        String name = SCANNER.nextLine().toLowerCase();
        Integer[] indexes = indexedAuthorNameValue.get(name);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any book with a name: '" + name + "'");
        }
    }

    /**
     * Searches for a book by author surname and prints the book information if
     * found. If no book is found, prints a message indicating that no book was
     * found.
     */
    private void searchByAuthorSurname() {
        System.out.println("Search by surname: ");
        String surname = SCANNER.nextLine().toLowerCase();
        Integer[] indexes = indexedAuthorSurnameValue.get(surname);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any book with a surname: '" + surname + "'");
        }
    }

    /**
     * Lists all books in the library by author name, sorted in alphabetical
     * order. Prints a table with the book information.
     */
    private void listAllBookByAuthorName() {
        printTableLabels();
        listAllDataByCompare(BOOKS, new BookAuthorNameComparator());
    }

    /**
     * Lists all books in the library by author surname, sorted in alphabetical
     * order. Prints a table with the book information.
     */
    private void listAllBookByAuthorSurname() {
        printTableLabels();
        listAllDataByCompare(BOOKS, new BookAuthorSurnameComparator());
    }

    /**
     * Lists all books in the library by their title, sorted in alphabetical
     * order. Prints a table with the book information.
     */
    private void listAllBookByTitle() {
        printTableLabels();
        listAllDataByCompare(BOOKS, new BookTitleComparator());
    }

    /**
     * Prints the labels for the table of books and authors. The labels include
     * the book ID, book code, book title, genres, author ID, author name, and
     * author surname.
     */
    private void printTableLabels() {
        System.out.println(
                "⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.printf("%-10s%-40s%-20s%-20s%-15s%-15s%-15s%n",
                "Book ID", "Book Code", "Book Title", "Genres",
                "Author ID", "Author Name", "Author Surname");
        System.out.println(
                "⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    /**
     * Finds and prints the books at the given indexes in the BOOKS list.
     *
     * @param indexes An array of indexes of the books to print
     */
    private void findAndPrint(Integer[] indexes) {
        printTableLabels();
        for (Integer index : indexes) {
            System.out.println(BOOKS.get(index));
        }
    }

    /**
     * Prepares the search data by indexing the books in the library by title,
     * author name, author surname, and genre. The indexing is done by calling
     * the indexingOfObject and indexingOfList methods.
     */
    private void prepareSearchData() {
        for (int i = 0; i < BOOKS.size(); i++) {
            Book book = BOOKS.get(i);
            indexingOfObject(indexedAuthorNameValue, book.getAuthor().getName(), i);
            indexingOfObject(indexedAuthorSurnameValue, book.getAuthor().getSurname(), i);
            indexingOfList(indexedGenreValue, book.getGenres(), i);
        }
    }
}
