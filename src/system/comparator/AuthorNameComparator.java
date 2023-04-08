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
