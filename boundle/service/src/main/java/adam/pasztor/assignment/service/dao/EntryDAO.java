package adam.pasztor.assignment.service.dao;

import java.util.Collection;

import adam.pasztor.assignment.api.model.Entry;

public interface EntryDAO {

	void createEntry (Entry entry);
	
	Collection<Entry> readEntries();
}
