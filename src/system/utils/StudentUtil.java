package system.utils;

import system.comparator.IdComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import system.models.Student;

/**
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class StudentUtil {

    private final static String STUDENT_CSV_PATH = "src/resource/student.csv";

    /**
     * This method prepare student data for storage on the RAM.
     * 
     * @return list of student
     */
    public static List<Student> readFromCsv() {
        List<Student> students = new ArrayList<>();
        List<String[]> dataList = CSVUtil.readCSV(STUDENT_CSV_PATH, "Student data loading...", "Student data is loaded!");
        dataList.stream().forEach(data -> {
            Integer id = Integer.valueOf(data[0]);
            String name = data[1];
            String surname = data[2];
            Student student = new Student(id, name, surname);
            students.add(student);
        });
        Collections.sort(students, new IdComparator<>());
        return students;
    }
}
