/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.controller;

import java.util.HashMap;
import java.util.Map;
import system.models.Author;
import static system.models.Constants.AUTHORS;
import static system.models.Constants.SCANNER;
import static system.utils.SearchUtil.indexingOfObject;

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
    
    public void search(Integer option) {
        switch (option) {
            case 1:
                searchByName();
                break;
            case 2:
                searchBySurname();
                break;
        }
    }

    public void searchByName() {
        System.out.println("Search by name: ");
        String name = SCANNER.next().toLowerCase();
        Integer[] indexes = INDEXED_NAME_VALUE.get(name);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any student with a name: '" + name + "'");
        }
    }

    public void searchBySurname() {
        System.out.println("Search by surname: ");
        String surname = SCANNER.next().toLowerCase();
        Integer[] indexes = INDEXED_SURNAME_VALUE.get(surname);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any student with a surname: '" + surname + "'");
        }
    }

    private void findAndPrint(Integer[] indexes) {
        for (Integer index : indexes) {
            System.out.println(AUTHORS.get(index));
        }
    }

    private void prepareSearchData() {
        for (int i = 0; i < AUTHORS.size(); i++) {
            Author author = AUTHORS.get(i);
            indexingOfObject(INDEXED_NAME_VALUE, author.getName(), i);
            indexingOfObject(INDEXED_SURNAME_VALUE, author.getSurname(), i);
        }
    }
}
