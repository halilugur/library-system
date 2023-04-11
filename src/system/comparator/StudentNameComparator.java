package system.comparator;

import java.util.Comparator;
import system.models.Student;

/**
 * A comparator for comparing two Student objects based on their name. This
 * class should be used in conjunction with the sort() method of the Collections
 * class.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class StudentNameComparator implements Comparator<Student> {

    @Override
    public int compare(Student object1, Student object2) {
        return object1.getName().compareTo(object2.getName());
    }
}
