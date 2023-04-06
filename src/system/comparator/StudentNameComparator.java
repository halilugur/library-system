package system.comparator;

import java.util.Comparator;
import system.models.Student;

/**
 * Compare name of students
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
