package system.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import system.Main;
import system.comparator.IdComparator;
import system.models.Student;

/**
 * A utility class for reading student data from a CSV file.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class StudentUtil {

    private static final InputStream STUDENT_CSV_PATH = Main.class.getResourceAsStream("/resource/student.csv");

    private StudentUtil() {

    }

    /**
     * Reads student data from a CSV file and returns a sorted list of Student
     * objects.
     *
     * @return A sorted list of Student objects
     */
    public static List<Student> readFromCsv() {
        List<Student> students = new ArrayList<>();
        List<String[]> dataList = CSVUtil.readCSV(STUDENT_CSV_PATH,
                "Student data loading...",
                "Student data is loaded!");
        dataList.forEach(data -> {
            Integer id = Integer.valueOf(data[0]);
            String name = data[1];
            String surname = data[2];
            String address = data[3];
            Student student = new Student(id, name, surname, address);
            students.add(student);
        });
        SortUtil.sort(students, new IdComparator<>());
        return students;
    }
}
