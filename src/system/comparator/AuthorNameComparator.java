/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.comparator;

import java.util.Comparator;
import system.models.Author;

/**
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class AuthorNameComparator implements Comparator<Author> {

    @Override
    public int compare(Author object1, Author object2) {
        return object1.getName().compareTo(object2.getName());
    }

}
