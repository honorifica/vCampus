package vss.server.dao;
import java.util.*;
import vss.common.Book;

public interface BookDao {
	/**
	 * get all books' information
	 * @return all books
	 */
	public List<Book> getAllBook();
	/**
	 * Administrators add books
     * @param book object book as a parameter to get the book to database
     * @return return a value of boolean, showing this function success or fail
     */
	public boolean addBook(Book book);
	/**
	 * Student borrow book(s)
     * @param bookID search a book by bookID from database
     * @param uID uID is the student's ID who borrowed the book 
     * @return return a value of boolean, showing this function success or fail
     */
	public boolean borrowBook(String bookID,String uID);
	/**
	 * Administrators delete books
     * @param bookID search a book by bookID from database
     * @return return a value of boolean, showing this function success or fail
     */
	public boolean removeBook(String bookID);
	/**
	 * Student return book(s)
     * @param bookID search a book by bookID from database
     * @param uID uID is the student's ID who return the book 
     * @return return a value of boolean, showing this function success or fail
     */
	public boolean returnBook(String bookID,String uID);
	/**
	 * Find books by bookID
     * @param bookID search a book by bookID from database
     * @return return a value of Book which id is bookID
     */
	public Book searchBookByID(String bookID);
	/**
	 * Find books by bookName
     * @param bookName search a book by bookName from database
     * @return return a value of Book which name is bookName
     */
	public Book searchBookByName(String bookName);
}
