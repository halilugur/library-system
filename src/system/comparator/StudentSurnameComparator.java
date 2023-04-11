package system.comparator;

import java.util.Comparator;
import system.models.Student;

/**
 * A comparator for comparing two Student objects based on their surnames. This
 * class should be used in conjunction with the sort() method of the Collections
 * class.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class StudentSurnameComparator implements Comparator<Student> {

    @Override
    public int compare(Student obejct1, Student obejct2) {
        return obejct1.getSurname().compareTo(obejct2.getSurname());
    }
}
