package adam.pasztor.assignment.service.dao;

import java.util.Collection;

import adam.pasztor.assignment.api.model.Finished;

public interface FinishedDAO {

	void createFinished (Finished finished);
	
	Collection<Finished> readFinisheds();
}
