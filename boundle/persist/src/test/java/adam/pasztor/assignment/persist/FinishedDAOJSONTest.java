package adam.pasztor.assignment.persist;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import adam.pasztor.assignment.api.model.Finished;
import adam.pasztor.assignment.service.dao.FinishedDAO;

public class FinishedDAOJSONTest {
	
	private FinishedDAO dao;

	@Before
	public void setUp(){
		dao=new FinishedDAOJSON("resources/finisheds.json");
	}

	@Test
	public void testCreateFinished() {
		Date today=new Date();
		Date yesterday= new Date (today.getTime()-24*60*60*1000);
		Finished finished=new Finished ("123456","John Smith",yesterday,today);
		dao.createFinished(finished);
	}

	@Test
	public void testReadFinisheds() {
		Collection<Finished> finisheds=dao.readFinisheds();
		System.out.println(finisheds);
	}

}
