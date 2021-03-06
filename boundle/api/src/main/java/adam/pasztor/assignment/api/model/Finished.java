package adam.pasztor.assignment.api.model;

import java.text.SimpleDateFormat;
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
	
	//date formatting
	
	SimpleDateFormat form=new SimpleDateFormat ("yyyy.MM.dd. 'at' hh:mm:ss a");
	
	@Override
	public String toString() {
		return "Finished [bookID=" + bookID + ", nameofBorrower=" + nameofBorrower + ", fromDate=" + form.format(fromDate)
				+ ", toDate=" + form.format(toDate) + "]";
	}

	public boolean equals(Finished other) {
		
		
		if (other==null)
			return false;
		
		if (this.nameofBorrower.equals(other.getNameofBorrower()) && this.bookID.equals(other.getBookID()) && this.fromDate.equals(other.getFromDate()) && this.toDate.equals(other.getToDate()))
			return true;
		return false;
		

	}
	
	
		
}
