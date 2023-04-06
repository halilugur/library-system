package system.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author halilugur
 */
public class WaitingQueue {

    private Book[] books;
    private Student[] students;
    private int elementCount;

    public WaitingQueue() {
        books = new Book[0];
        students = new Student[0];
        this.elementCount = 0;
    }

    /**
     * 
     * @param book
     * @param student
     * @return 
     */
    public boolean add(Book book, Student student) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].equals(book)
                    && students[i].equals(student)) {
                return false;
            }
        }

        if (elementCount >= books.length) {
            books = Arrays.copyOf(books, elementCount + 1);
            students = Arrays.copyOf(students, elementCount + 1);
        }
        books[elementCount] = book;
        students[elementCount] = student;
        elementCount++;
        return true;
    }

    /**
     * 
     * @param book
     * @return 
     */
    public Student poll(Book book) {
        int findPosition = -1;
        for (int i = 0; i < books.length; i++) {
            Book checkBook = books[i];
            if (checkBook.equals(book)) {
                findPosition = i;
                break;
            }
        }
        if (findPosition != -1) {
            elementCount--;
            if (elementCount < 0) {
                elementCount = 0;
                return null;
            }
            Student student = students[findPosition];
            Book[] newBookIds = new Book[elementCount];
            Student[] newStudentIds = new Student[elementCount];

            System.arraycopy(books, findPosition + 1, newBookIds, findPosition, books.length - findPosition - 1);
            System.arraycopy(books, 0, newBookIds, 0, findPosition);
            books = newBookIds;

            System.arraycopy(students, findPosition + 1, newStudentIds, findPosition, students.length - findPosition - 1);
            System.arraycopy(students, 0, newStudentIds, 0, findPosition);
            students = newStudentIds;
            return student;
        }
        return null;
    }

    /**
     * 
     * @param book
     * @return 
     */
    public Student peek(Book book) {
        for (int i = 0; i < books.length; i++) {
            Book checkBook = books[i];
            if (checkBook.equals(book)) {
                return students[i];
            }
        }
        return null;
    }

    /**
     * 
     * @return 
     */
    public Map<String, Integer> toMap() {
        Map<String, Integer> mapped = new HashMap<>();
        for (int i = 0; i < books.length; i++) {
            mapped.put(books[i].getCode(), students[i].getId());
        }
        return mapped;
    }

    @Override
    public String toString() {
        return "WaitingList{"
                + "books=" + Arrays.toString(books)
                + ", students=" + Arrays.toString(students)
                + ", elementCount=" + elementCount + '}';
    }
}
