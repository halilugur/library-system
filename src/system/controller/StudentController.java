package system.controller;

import java.util.HashMap;
import java.util.Map;
import system.comparator.StudentNameComparator;
import system.comparator.StudentSurnameComparator;
import static system.models.Constants.SCANNER;
import static system.models.Constants.STUDENTS;
import static system.utils.SearchUtil.indexingOfObject;
import static system.utils.SearchUtil.listAllDataByCompare;
import system.models.Student;
import system.utils.SearchUtil;

/**
 * This class provide student searching functionality.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class StudentController {

    private final Map<String, Integer[]> INDEXED_NAME_VALUE;
    private final Map<String, Integer[]> INDEXED_SURNAME_VALUE;

    public StudentController() {
        INDEXED_NAME_VALUE = new HashMap<>();
        INDEXED_SURNAME_VALUE = new HashMap<>();
        prepareSearchData();
    }

    public void search(Integer option) {
        switch (option) {
            case 1:
                searchById();
                break;
            case 2:
                searchByName();
                break;
            case 3:
                searchBySurname();
                break;
            case 4:
                borrowedListByStudentId();
                break;
            case 5:
                waitingListByStudentId();
                break;
            case 6:
                listAllStudentByName();
                break;
            case 7:
                listAllStudentBySurname();
                break;
        }
    }

    public void searchById() {
        System.out.println("Search by ID: ");
        Integer id = SCANNER.nextInt();
        Integer index = SearchUtil.find(STUDENTS, id);
        if (index != -1) {
            System.out.println(STUDENTS.get(index));
        } else {
            System.out.println("Couldn't find any student with an ID number: '" + id + "'");
        }
    }

    public void searchByName() {
        System.out.println("Search by name: ");
        String name = SCANNER.next().toLowerCase();
        Integer[] indexes = INDEXED_NAME_VALUE.get(name);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any student with a name: '" + name + "'");
        }
    }

    public void searchBySurname() {
        System.out.println("Search by surname: ");
        String surname = SCANNER.next().toLowerCase();
        Integer[] indexes = INDEXED_SURNAME_VALUE.get(surname);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any student with a surname: '" + surname + "'");
        }
    }

    public void listAllStudentByName() {
        listAllDataByCompare(STUDENTS, new StudentNameComparator());
    }

    public void listAllStudentBySurname() {
        listAllDataByCompare(STUDENTS, new StudentSurnameComparator());
    }

    public void borrowedListByStudentId() {
        System.out.println("Student ID: ");
        Integer id = SCANNER.nextInt();
        Integer index = SearchUtil.find(STUDENTS, id);
        if (index != -1) {
            System.out.println(STUDENTS.get(index).getBorrowedList());
        } else {
            System.out.println("Couldn't find any student with an ID number: '" + id + "'");
        }
    }

    public void waitingListByStudentId() {
        System.out.println("Student ID: ");
        Integer id = SCANNER.nextInt();
        Integer index = SearchUtil.find(STUDENTS, id);
        if (index != -1) {
            System.out.println(STUDENTS.get(index).getWaitingList());
        } else {
            System.out.println("Couldn't find any student with an ID number: '" + id + "'");
        }
    }

    private void findAndPrint(Integer[] indexes) {
        for (Integer index : indexes) {
            System.out.println(STUDENTS.get(index));
        }
    }

    private void prepareSearchData() {
        for (int i = 0; i < STUDENTS.size(); i++) {
            Student student = STUDENTS.get(i);
            indexingOfObject(INDEXED_NAME_VALUE, student.getName(), i);
            indexingOfObject(INDEXED_SURNAME_VALUE, student.getSurname(), i);
        }
    }
}
