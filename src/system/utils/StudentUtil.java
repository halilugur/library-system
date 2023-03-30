package system.utils;

import java.util.ArrayList;
import java.util.List;
import system.models.Student;

/**
 *
 * @author halilugur
 */
public class StudentUtil {

    private final static String STUDENT_CSV_PATH = "src/resource/student.csv";

    public static List<Student> readFromCsv() {
        List<Student> students = new ArrayList<>();
        List<String[]> dataList = LoadData.dataFromCsv(STUDENT_CSV_PATH, "Student data loading...", "Student data is loaded!");
        dataList.parallelStream().forEach(data -> {
            Integer id = Integer.valueOf(data[0]);
            String name = data[1];
            String surname = data[2];
            Student student = new Student(id, name, surname);
            students.add(student);
        });
        return students;
    }
}
