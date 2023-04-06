package system.comparator;

import java.util.Comparator;
import system.models.Student;

/**
 * Compare name of students
 *
 * @author halilugur
 */
public class StudentNameComparator implements Comparator<Student> {

    @Override
    public int compare(Student obejct1, Student obejct2) {
        return obejct1.getName().compareTo(obejct2.getName());
    }
}
