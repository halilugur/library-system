package system.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A waiting queue for books and students. Books and students are added to the
 * queue together. Books can be polled from the queue, and the corresponding
 * student will be returned. The queue can also be converted to a map of book
 * codes to student IDs.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class WaitingQueue {

    private Book[] books;
    private Student[] students;
    private int elementCount;

    /**
     * Constructs a new WaitingQueue object with no books or students and an
     * element count of 0.
     */
    public WaitingQueue() {
        books = new Book[0];
        students = new Student[0];
        this.elementCount = 0;
    }

    /**
     * Adds a book to a student's list of borrowed books, if it is not already
     * borrowed by the student.
     *
     * @param book    The book to be borrowed.
     * @param student The student who is borrowing the book.
     * @return true if the book was successfully borrowed, false if the book is
     * already borrowed by the student.
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
     * Removes a student from the library system by removing the book they have
     * borrowed.
     *
     * @param book The book that the student has borrowed.
     * @return The student who borrowed the book, or null if the book is not
     * found.
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

            System.arraycopy(students, findPosition + 1, newStudentIds, findPosition,
                    students.length - findPosition - 1);
            System.arraycopy(students, 0, newStudentIds, 0, findPosition);
            students = newStudentIds;
            return student;
        }
        return null;
    }

    /**
     * Returns the student who has borrowed the specified book without removing
     * the book from the library.
     *
     * @param book The book to check for
     * @return The student who has borrowed the book, or null if the book is not
     * borrowed
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
     * Converts the array of books and students to a map where the book is
     * the key and the student is the value.
     *
     * @return A map with book codes as keys and student IDs as values.
     */
    public Map<Book, Student> toMap() {
        Map<Book, Student> mapped = new HashMap<>();
        for (int i = 0; i < books.length; i++) {
            mapped.put(books[i], students[i]);
        }
        return mapped;
    }
}
