package vss.common;

import java.util.Vector;
//import vss.server.bz.*;
public class Book {
	//private static final long serialVersionUID = 2342342342342342343L;
	private String bookID;
	private String bookName;
	private String bookAmount;
	private String left;

	public Book() { }	

	public Book(String bookID, String bookName, String bookAmount,String left) {
		this.bookID = bookID;
		this.bookName = bookName;
		this.bookAmount = bookAmount;
		this.left = left;
	}

	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getLeft() {
		return this.left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public Book bookID(String bookID) {
		setBookID(bookID);
		return this;
	}

	public Book bookName(String bookName) {
		setBookName(bookName);
		return this;
	}

	public Book bookAmount(String bookAmount) {
		setBookAmount(bookAmount);
		return this;
	}

	@Override
	public String toString() {
		return "{" +
			" bookID='" + getBookID() + "'" +
			", bookName='" + getBookName() + "'" +
			", bookAmount='" + getBookAmount() + "'" +
			", bookLeft=' " + getLeft() +
			"}";
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAmount() {
		return bookAmount;
	}
	public void setBookAmount(String bookAmount) {
		this.bookAmount = bookAmount;
	}
	public Vector<String> getContent() {
		Vector<String> bookContents=new Vector<String>();
		bookContents.add(bookID);
		bookContents.add(bookName);
		bookContents.add(bookAmount);
		bookContents.add(left);
		return bookContents;
	}
	public void setContent(Vector<String> content){
		bookID = content.get(0);
		bookName = content.get(1);
		bookAmount = content.get(2);
		left = content.get(3);
	}
}
