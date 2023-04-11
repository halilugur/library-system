package system.comparator;

import java.util.Comparator;
import system.models.Author;

/**
 * 
 * A comparator for comparing two Author objects based on their name. This
 * class should be used in conjunction with the sort() method of the Collections
 * class.
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
