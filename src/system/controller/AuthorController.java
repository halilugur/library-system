package system.controller;

import java.util.HashMap;
import java.util.Map;
import system.comparator.AuthorNameComparator;
import system.comparator.AuthorSurnameComparator;
import system.models.Author;
import static system.models.Constants.AUTHORS;
import static system.models.Constants.SCANNER;
import static system.utils.ScannerUtil.checkNumber;
import system.utils.SearchUtil;
import static system.utils.SearchUtil.indexingOfObject;
import static system.utils.SearchUtil.listAllDataByCompare;

/**
 * The AuthorController class is responsible for managing the search and listing
 * of authors. It contains two maps, one for indexing authors by name and
 * another for indexing authors by surname. In addition,the class provides methods
 * for searching authors by ID, name, and surname and listing all authors in
 * alphabetical order by name or surname.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class AuthorController {

    private final Map<String, Integer[]> INDEXED_NAME_VALUE;
    private final Map<String, Integer[]> INDEXED_SURNAME_VALUE;

    public AuthorController() {
        INDEXED_NAME_VALUE = new HashMap<>();
        INDEXED_SURNAME_VALUE = new HashMap<>();
        prepareSearchData();
    }

    /**
     * Searches for authors based on the given option.
     *
     * @param option The option to search by:
     *               1 - search by ID
     *               2 - search by name
     *               3 - search by surname
     *               4 - list authors ordered by name
     *               5 - list authors ordered by surname
     */
    public void search(Integer option) {
        switch (option) {
            case 1:
                searchById();
                break;
            case 2:
                searchByName();
                break;
            case 3:
                searchBySurname();
                break;
            case 4:
                listAuthorsOrderByName();
                break;
            case 5:
                listAuthorsOrderBySurname();
                break;
        }
    }

    /**
     * Searches for an author by their ID number and prints their information if
     * found.
     */
    public void searchById() {
        Integer id = checkNumber("Search by ID: ");
        Integer index = SearchUtil.find(AUTHORS, id);
        if (index != -1) {
            printTableLabels();
            System.out.println(AUTHORS.get(index));
        } else {
            System.out.println("Couldn't find any author with an ID number: '" + id + "'");
        }
    }

    /**
     * Searches for an author by name and prints their information if found.
     * Uses a map of indexed name values to quickly find the author.
     */
    private void searchByName() {
        System.out.println("Search by name: ");
        String name = SCANNER.nextLine().toLowerCase();
        Integer[] indexes = INDEXED_NAME_VALUE.get(name);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any author with a name: '" + name + "'");
        }
    }

    /**
     * Searches for authors by surname and prints the results if any are found.
     * Uses a map of indexed surnames to quickly find matching authors.
     */
    private void searchBySurname() {
        System.out.println("Search by surname: ");
        String surname = SCANNER.nextLine().toLowerCase();
        Integer[] indexes = INDEXED_SURNAME_VALUE.get(surname);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any author with a surname: '" + surname + "'");
        }
    }

    /**
     * Lists all authors in the library by their name, sorted in alphabetical
     * order. Prints a table with the book information.
     */
    private void listAuthorsOrderByName() {
        printTableLabels();
        listAllDataByCompare(AUTHORS, new AuthorNameComparator());
    }

    /**
     * Lists all authors in the library by their surname, sorted in alphabetical
     * order. Prints a table with the book information.
     */
    private void listAuthorsOrderBySurname() {
        printTableLabels();
        listAllDataByCompare(AUTHORS, new AuthorSurnameComparator());
    }

    /**
     * Prints the table labels for a list of author, including ID, name, and
     * surname. The labels are printed in a formatted table with a horizontal
     * line above and below.
     */
    private void printTableLabels() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println(String.format("%-10s%-20s%-20s", "ID", "Name", "Surname"));
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    /**
     * Prints the authors from the AUTHORS list at the given indexes.
     *
     * @param indexes An array of indexes of the authors to print
     */
    private void findAndPrint(Integer[] indexes) {
        printTableLabels();
        for (Integer index : indexes) {
            System.out.println(AUTHORS.get(index));
        }
    }

    /**
     * Prepares the search data by indexing the authors names and surnames. The
     * indexing is done by adding the author's name and surname to the
     * corresponding indexed value list, along with the author's index in the
     * AUTHORS list.
     */
    private void prepareSearchData() {
        for (int i = 0; i < AUTHORS.size(); i++) {
            Author author = AUTHORS.get(i);
            indexingOfObject(INDEXED_NAME_VALUE, author.getName(), i);
            indexingOfObject(INDEXED_SURNAME_VALUE, author.getSurname(), i);
        }
    }
}
