package adam.pasztor.assignment.api.model;

import java.util.Date;

public class Finished {

	//class variables
	
	private String bookID;
	private String nameofBorrower;
	private Date fromDate;
	private Date toDate;
	
	// constructors
	
	public Finished() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Finished(String bookID, String nameofBorrower, Date fromDate, Date toDate) {
		super();
		this.bookID = bookID;
		this.nameofBorrower = nameofBorrower;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	// getters & setters
	
	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getNameofBorrower() {
		return nameofBorrower;
	}

	public void setNameofBorrower(String nameofBorrower) {
		this.nameofBorrower = nameofBorrower;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "Finished [bookID=" + bookID + ", nameofBorrower=" + nameofBorrower + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + "]";
	}
	
	
		
}
