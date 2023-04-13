package system.controller;

import java.util.HashMap;
import java.util.Map;
import system.comparator.StudentNameComparator;
import system.comparator.StudentSurnameComparator;
import static system.models.Constants.SCANNER;
import static system.models.Constants.STUDENTS;
import static system.utils.SearchUtil.indexingOfObject;
import static system.utils.SearchUtil.listAllDataByCompare;
import static system.utils.ScannerUtil.checkNumber;
import system.models.Student;
import system.utils.SearchUtil;

/**
 * The StudentController class is responsible for managing the search and
 * retrieval of student data. It contains methods for searching by ID, name, and
 * surname, as well as listing all students by name or surname. It also has
 * methods for retrieving the borrowed and waiting lists of a student by their
 * ID. The class initializes two maps for indexing student data by name and
 * surname for faster search times.
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

    /**
     * Searches for a student based on the given option.
     *
     * @param option The option to search by:
     *               1 - search by ID
     *               2 - search by name
     *               3 - search by surname
     *               4 - get borrowed list by student ID
     *               5 - get waiting list by student ID
     *               6 - list all students by name
     *               7 - list all students by surname
     */
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
                listAllStudentsByName();
                break;
            case 7:
                listAllStudentsBySurname();
                break;
        }
    }

    /**
     * Searches for a student by their ID number and prints their information if
     * found.
     *
     * @throws NumberFormatException if the input is not a valid integer
     */
    private void searchById() {
        Integer id = checkNumber("Search by ID: ");
        Integer index = SearchUtil.find(STUDENTS, id);
        if (index != -1) {
            System.out.println(STUDENTS.get(index));
        } else {
            System.out.println("Couldn't find any student with an ID number: '" + id + "'");
        }
    }

    /**
     * Searches for an student by name and prints their information if found.
     * Uses a map of indexed name values to quickly find the student.
     */
    private void searchByName() {
        System.out.println("Search by name: ");
        String name = SCANNER.nextLine().toLowerCase();
        Integer[] indexes = INDEXED_NAME_VALUE.get(name);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any student with a name: '" + name + "'");
        }
    }

    /**
     * Searches for an student by surname and prints their information if found.
     * Uses a map of indexed surname values to quickly find the student.
     */
    private void searchBySurname() {
        System.out.println("Search by surname: ");
        String surname = SCANNER.nextLine().toLowerCase();
        Integer[] indexes = INDEXED_SURNAME_VALUE.get(surname);
        if (indexes != null) {
            findAndPrint(indexes);
        } else {
            System.out.println("Couldn't find any student with a surname: '" + surname + "'");
        }
    }

    /**
     * Lists all students in the RAM by their name, sorted in alphabetical
     * order. Prints a table with the book information.
     */
    private void listAllStudentsByName() {
        printTableLabels();
        listAllDataByCompare(STUDENTS, new StudentNameComparator());
    }

    /**
     * Lists all students in the RAM by their surname, sorted in alphabetical
     * order. Prints a table with the book information.
     */
    private void listAllStudentsBySurname() {
        printTableLabels();
        listAllDataByCompare(STUDENTS, new StudentSurnameComparator());
    }

    /**
     * Prints the list of borrowed items by a student with the given ID. If the
     * student is not found, prints an error message.
     */
    private void borrowedListByStudentId() {
        Integer id = checkNumber("Student ID: ");
        Integer index = SearchUtil.find(STUDENTS, id);
        if (index != -1) {
            printBookTableLabels();
            STUDENTS.get(index).getBorrowedList().forEach(System.out::println);
        } else {
            System.out.println("Couldn't find any student with an ID number: '" + id + "'");
        }
    }

    /**
     * Prints the waiting list of a student with a given ID number. If the
     * student is not found, prints an error message.
     */
    private void waitingListByStudentId() {
        Integer id = checkNumber("Student ID: ");
        Integer index = SearchUtil.find(STUDENTS, id);
        if (index != -1) {
            printBookTableLabels();
            STUDENTS.get(index).getWaitingList().forEach(System.out::println);
        } else {
            System.out.println("Couldn't find any student with an ID number: '" + id + "'");
        }
    }

    /**
     * Prints the table labels for a list of people, including ID, name, and
     * surname. The labels are printed in a formatted table with a horizontal
     * line above and below.
     */
    private void printTableLabels() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println(String.format("%-10s%-20s%-20s%-20s", "ID", "Name", "Surname", "Address"));
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    /**
     * Prints the labels for the table of books and authors. The labels include
     * the book ID, book code, book title, genres, author ID, author name, and
     * author surname.
     */
    private void printBookTableLabels() {
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        System.out.println(String.format("%-10s%-40s%-20s%-20s%-15s%-20s%-20s", "Book ID", "Book Code", "Book Title", "Genres", "Author ID", "Author Name", "Author Surname"));
        System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
    }

    /**
     * Prints the table labels and the students at the given indexes.
     *
     * @param indexes An array of indexes of the students to print
     */
    private void findAndPrint(Integer[] indexes) {
        printTableLabels();
        for (Integer index : indexes) {
            System.out.println(STUDENTS.get(index));
        }
    }

    /**
     * Prepares the search data by indexing the name and surname of each student
     * in the STUDENTS list. The indexing is done by calling the
     * indexingOfObject method with the appropriate parameters.
     */
    private void prepareSearchData() {
        for (int i = 0; i < STUDENTS.size(); i++) {
            Student student = STUDENTS.get(i);
            indexingOfObject(INDEXED_NAME_VALUE, student.getName(), i);
            indexingOfObject(INDEXED_SURNAME_VALUE, student.getSurname(), i);
        }
    }
}
