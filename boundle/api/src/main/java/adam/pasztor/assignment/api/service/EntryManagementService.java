package adam.pasztor.assignment.api.service;

import java.util.Collection;
import java.util.Date;
import adam.pasztor.assignment.api.model.Entry;


public interface EntryManagementService {

	Collection<Entry> listEntries();
	void addEntry(Entry entry);
	
}
