package system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import system.comparator.BookAuthorNameComparator;
import system.comparator.BookAuthorSurnameComparator;
import system.models.Book;
import static system.models.Constants.BOOKS;
import static system.models.Constants.SCANNER;
import system.screens.Menu;
import system.utils.SearchUtil;

import static system.utils.SearchUtil.indexingOfObject;
import static system.utils.SearchUtil.indexingOfList;
import static system.utils.SearchUtil.listAllDataByCompare;

/**
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
                listAllBookByAuthorName();
                break;
            case 8:
                listAllBookByAuthorSurname();
                break;
        }
    }

    public void searchById() {
        System.out.println("Search by ID: ");
        Integer id = SCANNER.nextInt();
        Integer index = SearchUtil.find(BOOKS, id);
        if (index != -1) {
            printTableLabels();
            System.out.println(BOOKS.get(index));
        } else {
            System.out.println("Couldn't find any book with an ID number: '" + id + "'");
        }
    }

    public void searchByCode() {
        System.out.println("Search by code: ");
        String code = SCANNER.next().toLowerCase();
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

    private void searchByTitle() {
        System.out.println("Search by title: ");
        String title = SCANNER.next().toLowerCase();
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

    private void searchByGenre() {
        System.out.println("Search by genre: ");
        String genre = SCANNER.next().toLowerCase();
        Integer[] indexes = INDEXED_GENRE_VALUE.get(genre);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any book with a title: '" + genre + "'");
        }
    }

    private void searchByAuthorName() {
        System.out.println("Search by author name: ");
        String name = SCANNER.next().toLowerCase();
        Integer[] indexes = INDEXED_AUTHOR_NAME_VALUE.get(name);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any book with a name: '" + name + "'");
        }
    }

    private void searchByAuthorSurname() {
        System.out.println("Search by surname: ");
        String surname = SCANNER.next().toLowerCase();
        Integer[] indexes = INDEXED_AUTHOR_SURNAME_VALUE.get(surname);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any book with a surname: '" + surname + "'");
        }
    }

    private void listAllBookByAuthorName() {
        printTableLabels();
        listAllDataByCompare(BOOKS, new BookAuthorNameComparator());
    }

    private void listAllBookByAuthorSurname() {
        printTableLabels();
        listAllDataByCompare(BOOKS, new BookAuthorSurnameComparator());
    }

    private void printTableLabels() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println(String.format("%-10s%-40s%-20s%-20s%-15s%-20s%-20s", "Book ID", "Book Code", "Book Title", "Genres", "Author ID", "Author Name", "Author Surname"));
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    private void findAndPrint(Integer[] indexes) {
        printTableLabels();
        for (Integer index : indexes) {
            System.out.println(BOOKS.get(index));
        }
    }

    private void prepareSearchData() {
        for (int i = 0; i < BOOKS.size(); i++) {
            Book book = BOOKS.get(i);
            final int finalIndex = i;
            indexingOfObject(INDEXED_TITLE_VALUE, book.getTitle(), finalIndex);
            indexingOfObject(INDEXED_AUTHOR_NAME_VALUE, book.getAuthor().getName(), finalIndex);
            indexingOfObject(INDEXED_AUTHOR_SURNAME_VALUE, book.getAuthor().getSurname(), finalIndex);
            indexingOfList(INDEXED_GENRE_VALUE, book.getGenres(), i);
        }
    }
}
