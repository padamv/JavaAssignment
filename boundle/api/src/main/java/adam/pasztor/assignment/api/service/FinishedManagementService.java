package adam.pasztor.assignment.api.service;

import java.util.Collection;
import java.util.Date;
import adam.pasztor.assignment.api.model.Finished;

public interface FinishedManagementService {

	Collection<Finished> listFinished();
	void addFinished(Finished finished);
	
}
