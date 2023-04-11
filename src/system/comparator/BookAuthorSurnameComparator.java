package system.comparator;

import java.util.Comparator;
import system.models.Book;

/**
 *
 * A comparator for comparing two Book objects based on the surnames of their
 * authors. The comparison is case-sensitive and uses the natural ordering of
 * strings.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class BookAuthorSurnameComparator implements Comparator<Book> {

    @Override
    public int compare(Book object1, Book object2) {
        return object1.getAuthor().getSurname().compareTo(object2.getAuthor().getSurname());
    }
}
