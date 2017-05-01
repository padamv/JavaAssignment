package adam.pasztor.assignment.service.impl;

import java.util.Collection;

import adam.pasztor.assignment.api.model.Entry;
import adam.pasztor.assignment.api.service.EntryManagementService;
import adam.pasztor.assignment.service.dao.EntryDAO;

public class EntryManagementServiceImpl implements EntryManagementService {

	//class variables
	
	private EntryDAO entryDAO;
	
	//constructor
	
	public EntryManagementServiceImpl(EntryDAO entryDAO) {
		super();
		this.entryDAO = entryDAO;
	}

	//methods
	
	public Collection<Entry> listEntries() {
		return entryDAO.readEntries();
	}

	public void addEntry(Entry entry) {
		entryDAO.createEntry(entry);

	}

}
