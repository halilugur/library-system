package system.controller;

import java.util.HashMap;
import java.util.Map;
import system.comparator.AuthorNameComparator;
import system.comparator.AuthorSurnameComparator;
import system.models.Author;
import static system.models.Constants.AUTHORS;
import static system.models.Constants.SCANNER;
import system.utils.SearchUtil;
import static system.utils.SearchUtil.indexingOfObject;
import static system.utils.SearchUtil.listAllDataByCompare;

/**
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
     *
     * @param option
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
     *
     */
    public void searchById() {
        System.out.println("Search by ID: ");
        Integer id = SCANNER.nextInt();
        Integer index = SearchUtil.find(AUTHORS, id);
        if (index != -1) {
            printTableLabels();
            System.out.println(AUTHORS.get(index));
        } else {
            System.out.println("Couldn't find any author with an ID number: '" + id + "'");
        }
    }

    /**
     *
     */
    private void searchByName() {
        System.out.println("Search by name: ");
        String name = SCANNER.next().toLowerCase();
        Integer[] indexes = INDEXED_NAME_VALUE.get(name);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any author with a name: '" + name + "'");
        }
    }

    /**
     *
     */
    private void searchBySurname() {
        System.out.println("Search by surname: ");
        String surname = SCANNER.next().toLowerCase();
        Integer[] indexes = INDEXED_SURNAME_VALUE.get(surname);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any author with a surname: '" + surname + "'");
        }
    }

    /**
     *
     */
    private void listAuthorsOrderByName() {
        printTableLabels();
        listAllDataByCompare(AUTHORS, new AuthorNameComparator());
    }

    /**
     *
     */
    private void listAuthorsOrderBySurname() {
        printTableLabels();
        listAllDataByCompare(AUTHORS, new AuthorSurnameComparator());
    }

    /**
     *
     */
    private void printTableLabels() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println(String.format("%-10s%-20s%-20s", "ID", "Name", "Surname"));
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    /**
     *
     * @param indexes
     */
    private void findAndPrint(Integer[] indexes) {
        printTableLabels();
        for (Integer index : indexes) {
            System.out.println(AUTHORS.get(index));
        }
    }

    /**
     *
     */
    private void prepareSearchData() {
        for (int i = 0; i < AUTHORS.size(); i++) {
            Author author = AUTHORS.get(i);
            indexingOfObject(INDEXED_NAME_VALUE, author.getName(), i);
            indexingOfObject(INDEXED_SURNAME_VALUE, author.getSurname(), i);
        }
    }
}
