package system.comparator;

import java.util.Comparator;
import system.models.Book;

/**
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
