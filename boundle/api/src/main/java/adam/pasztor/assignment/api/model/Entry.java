package adam.pasztor.assignment.api.model;

import java.util.Date;

public class Entry {

	// enum for borrowing / returning
	public static enum Mode{
		BORROWED, RETURNED
	}
	
	// class variables
	
	private String nameofBorrower;
	private Date date;
	private String bookID;
	private Mode mode;
	
	// constructors
	
	public Entry(){
		super();
	}

	public Entry(String nameofBorrower, Date date, String bookID, Mode mode) {
		super();
		this.nameofBorrower = nameofBorrower;
		this.date = date;
		this.bookID = bookID;
		this.mode = mode;
	}
	
	// getters & setters

	public String getNameofBorrower() {
		return nameofBorrower;
	}

	public void setNameofBorrower(String nameofBorrower) {
		this.nameofBorrower = nameofBorrower;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	
	
	
}
