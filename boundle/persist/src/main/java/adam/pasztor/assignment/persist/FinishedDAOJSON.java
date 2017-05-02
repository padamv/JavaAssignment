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

import adam.pasztor.assignment.api.model.Finished;
import adam.pasztor.assignment.service.dao.FinishedDAO;

public class FinishedDAOJSON implements FinishedDAO {
	
	//LOGGER
	
	private Logger LOGGER= LogManager.getLogger(FinishedDAOJSON.class);
	

	// classvariables
	
	private File database;
	
	//constructor
	
	public FinishedDAOJSON(String databasePath) {
		this.database = new File(databasePath);
		LOGGER.debug(String.format("Finished Database: %s", database.getAbsolutePath()));
	}
	
	//methods
	
	public void createFinished(Finished finished) {
		Collection<Finished> allFinisheds=readFinisheds();
		allFinisheds.add(finished);
		Finished[] extendedDatabase= allFinisheds.toArray(new Finished[allFinisheds.size()]);
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
		LOGGER.info(String.format("History for the book (ID: %s) has been added!", finished.getBookID()));
	}



	public Collection<Finished> readFinisheds() {
		ObjectMapper mapper= new ObjectMapper();
		Finished[] finisheds=new Finished[] {};
		
		try {
			finisheds=mapper.readValue(database,  Finished[].class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		
		Collection<Finished> result= new ArrayList<Finished>(Arrays.asList(finisheds));
		
		
		return result;
	}

}
