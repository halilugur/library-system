package system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import system.comparator.BookAuthorNameComparator;
import system.comparator.BookAuthorSurnameComparator;
import system.comparator.BookTitleComparator;
import system.models.Book;
import static system.models.Constants.BOOKS;
import static system.models.Constants.SCANNER;
import static system.utils.ScannerUtil.checkNumber;
import system.screens.Menu;
import system.utils.SearchUtil;

import static system.utils.SearchUtil.indexingOfObject;
import static system.utils.SearchUtil.indexingOfList;
import static system.utils.SearchUtil.listAllDataByCompare;

/**
 *
 * The BookController class is responsible for managing the search and listing
 * of books. It contains methods for searching books by various criteria such as
 * ID, code, title, genre, author name, and author surname. It also has methods
 * for listing all books by title, author name, and author surname. The class
 * initializes four maps to store the indexed values of books by title, genre,
 * author name, and author surname.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class BookController {

    Menu MENU = Menu.getInstance();
    private final Map<String, Integer[]> INDEXED_TITLE_VALUE;
    private final Map<String, Integer[]> INDEXED_GENRE_VALUE;
    private final Map<String, Integer[]> INDEXED_AUTHOR_NAME_VALUE;
    private final Map<String, Integer[]> INDEXED_AUTHOR_SURNAME_VALUE;

    public BookController() {
        INDEXED_TITLE_VALUE = new HashMap<>();
        INDEXED_GENRE_VALUE = new HashMap<>();
        INDEXED_AUTHOR_NAME_VALUE = new HashMap<>();
        INDEXED_AUTHOR_SURNAME_VALUE = new HashMap<>();
        prepareSearchData();
    }

    /**
     * Searches for a book based on the user's selected option.
     *
     * @param option The user's selected option.
     *              1: Search by ID
     *              2: Search by code
     *              3: Search by title
     *              4: Search by genre
     *              5: Search by author name
     *              6: Search by author surname
     *              7: List all books by title
     *              8: List all books by author name
     *              9: List all books by author surname
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
     * found.
     */
    public void searchById() {
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
    public void searchByCode() {
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
     * Searches for books by title and prints the results in a table format.
     * Prompts the user to enter a title to search for. If any books are found
     * with a matching title, they are printed in a table format. If no books
     * are found with a matching title, a message is printed to the console.
     */
    private void searchByTitle() {
        System.out.println("Search by title: ");
        String title = SCANNER.nextLine().toLowerCase();
        List<Book> found = BOOKS.stream()
                .filter(book -> book.getTitle().contains(title))
                .collect(Collectors.toList());
        if (!found.isEmpty()) {
            printTableLabels();
            found.forEach(System.out::println);
        } else {
            System.out.println("Couldn't find any book with a title: '" + title + "'");
        }
    }

    /**
     * Searches for books by genre and prints the results. Prompts the user to
     * enter a genre and searches the INDEXED_GENRE_VALUE map for the
     * corresponding indexes. If found, calls findAndPrint() to print the books
     * at the indexes. If not found, prints an error message.
     */
    private void searchByGenre() {
        System.out.println("Search by genre: ");
        String genre = SCANNER.nextLine().toLowerCase();
        Integer[] indexes = INDEXED_GENRE_VALUE.get(genre);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any book with a title: '" + genre + "'");
        }
    }

    /**
     * Searches for books by author name and prints the results. Prompts the
     * user to enter the author name to search for. If any books are found, they
     * are printed to the console. If no books are found, a message is printed
     * to the console.
     */
    private void searchByAuthorName() {
        System.out.println("Search by author name: ");
        String name = SCANNER.nextLine().toLowerCase();
        Integer[] indexes = INDEXED_AUTHOR_NAME_VALUE.get(name);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any book with a name: '" + name + "'");
        }
    }

    /**
     * Searches for books by author surname and prints the results. Prompts the
     * user to enter a surname and searches the indexed author surnames for
     * matches. If matches are found, the corresponding book indexes are
     * retrieved and passed to the findAndPrint method. If no matches are found,
     * a message is printed to the console.
     */
    private void searchByAuthorSurname() {
        System.out.println("Search by surname: ");
        String surname = SCANNER.nextLine().toLowerCase();
        Integer[] indexes = INDEXED_AUTHOR_SURNAME_VALUE.get(surname);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any book with a surname: '" + surname + "'");
        }
    }

    /**
     * Lists all books sorted by author name in a table format. Prints the table
     * labels and then lists all data by comparing the books using the
     * BookAuthorNameComparator.
     */
    private void listAllBookByAuthorName() {
        printTableLabels();
        listAllDataByCompare(BOOKS, new BookAuthorNameComparator());
    }

    /**
     * Lists all books sorted by author surname. Prints a table with the book
     * data.
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
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println(String.format("%-10s%-40s%-20s%-20s%-15s%-20s%-20s", "Book ID", "Book Code", "Book Title", "Genres", "Author ID", "Author Name", "Author Surname"));
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
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
            indexingOfObject(INDEXED_TITLE_VALUE, book.getTitle(), i);
            indexingOfObject(INDEXED_AUTHOR_NAME_VALUE, book.getAuthor().getName(), i);
            indexingOfObject(INDEXED_AUTHOR_SURNAME_VALUE, book.getAuthor().getSurname(), i);
            indexingOfList(INDEXED_GENRE_VALUE, book.getGenres(), i);
        }
    }
}
