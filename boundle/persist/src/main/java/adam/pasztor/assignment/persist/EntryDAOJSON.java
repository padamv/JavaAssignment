package adam.pasztor.assignment.persist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import adam.pasztor.assignment.api.model.Entry;
import adam.pasztor.assignment.service.dao.EntryDAO;

public class EntryDAOJSON implements EntryDAO {
	
	//LOGGER
	
	private Logger LOGGER= LogManager.getLogger(EntryDAOJSON.class);
	
	
	// classvariables
	
	private File database;
	
	//constructor
	
	public EntryDAOJSON(String databasePath) {
		this.database = new File(databasePath);
		LOGGER.debug(String.format("Entry Database: %s", database.getAbsolutePath()));
	}

	//methods
	
	public void createEntry(Entry entry) {
		
		Collection<Entry> allEntries=readEntries();
		allEntries.add(entry);
		Entry[] extendedDatabase= allEntries.toArray(new Entry[allEntries.size()]);
		ObjectMapper mapper =new ObjectMapper();
		
		try {
			mapper.writeValue(database,  extendedDatabase);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		LOGGER.info(String.format("Entry for the book (ID: %s) has been added!", entry.getBookID()));
		

	}

	public Collection<Entry> readEntries() {
		
		ObjectMapper mapper= new ObjectMapper();
		Entry[] entries=new Entry[] {};
		
		try {
			entries=mapper.readValue(database,  Entry[].class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		
		Collection<Entry> result= new ArrayList<Entry>(Arrays.asList(entries));
		
		
		return result;
	}

}
