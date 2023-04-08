package system.comparator;

import java.util.Comparator;
import system.models.Book;

/**
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class BookAuthorNameComparator implements Comparator<Book> {

    @Override
    public int compare(Book object1, Book object2) {
        return object1.getAuthor().getName().compareTo(object2.getAuthor().getName());
    }
}
