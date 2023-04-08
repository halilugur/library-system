package system.comparator;

import java.util.Comparator;
import system.models.Author;

/**
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class AuthorSurnameComparator implements Comparator<Author> {

    @Override
    public int compare(Author object1, Author object2) {
        return object1.getSurname().compareTo(object2.getSurname());
    }
}
