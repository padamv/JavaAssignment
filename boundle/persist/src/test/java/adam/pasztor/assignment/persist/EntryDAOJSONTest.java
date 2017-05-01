package adam.pasztor.assignment.persist;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import adam.pasztor.assignment.api.model.Entry;
import adam.pasztor.assignment.service.dao.EntryDAO;

public class EntryDAOJSONTest {

	private EntryDAO dao;
	
	@Before
	public void setUp(){
	
		dao= new EntryDAOJSON("resources/entries.json");
	}

	@Test
	public void testCreateEntry() {
		Date today=new Date();
		Entry entry=new Entry("John Smith", today, "123456",Entry.Mode.BORROWED);
		dao.createEntry(entry);
	}
	
	@Test
	public void testReadEntries(){
		Collection<Entry> entries = dao.readEntries();
		System.out.println(entries);	
	}
}
