package system.controller;

import java.util.HashMap;
import java.util.Map;
import system.comparator.AuthorNameComparator;
import system.comparator.AuthorSurnameComparator;
import system.models.Book;
import static system.models.Constants.AUTHORS;
import static system.models.Constants.BOOKS;
import static system.models.Constants.SCANNER;
import system.screens.Menu;

import static system.utils.SearchUtil.indexingOfObject;
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

    public BookController() {
        INDEXED_TITLE_VALUE = new HashMap<>();
        INDEXED_GENRE_VALUE = new HashMap<>();
        prepareSearchData();
    }

    public void search(Integer option) {
        switch (option) {
            case 1:
                searchByTitle();
                break;
            case 2:
                MENU.printGenreOptions();
                searchByGenre();

                break;
            case 3:
                findAllByAuthorName();
                break;
            case 4:
                findAllByAuthorSurname();
                break;
        }
    }

    public void searchByTitle() {
        System.out.println("Search by title: ");
        String title = SCANNER.next().toLowerCase();
        Integer[] indexes = INDEXED_TITLE_VALUE.get(title);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any book with a title: '" + title + "'");
        }
    }

    public void searchByGenre() {
        System.out.println("Search by genre: ");
        String genre = SCANNER.next().toLowerCase();
        Integer[] indexes = INDEXED_GENRE_VALUE.get(genre);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any book with a title: '" + genre + "'");
        }

    }

    public void findAllByAuthorName() {
        listAllDataByCompare(AUTHORS, new AuthorNameComparator());
    }

    public void findAllByAuthorSurname() {
        listAllDataByCompare(AUTHORS, new AuthorSurnameComparator());
    }

    private void findAndPrint(Integer[] indexes) {
        for (Integer index : indexes) {
            System.out.println(BOOKS.get(index));
        }
    }

    private void prepareSearchData() {
        for (int i = 0; i < BOOKS.size(); i++) {
            Book book = BOOKS.get(i);
            indexingOfObject(INDEXED_TITLE_VALUE, book.getTitle(), i);
            indexingOfObject(INDEXED_GENRE_VALUE, book.getGenre(), i);
        }
    }
}
