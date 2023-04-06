package system.comparator;

import java.util.Comparator;
import system.models.Student;

/**
 * Compare surname of students
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
