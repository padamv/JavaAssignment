package adam.pasztor.assignment.service.impl;

import java.util.Collection;

import adam.pasztor.assignment.api.model.Finished;
import adam.pasztor.assignment.api.service.FinishedManagementService;
import adam.pasztor.assignment.service.dao.FinishedDAO;

public class FinishedManagementServiceImpl implements FinishedManagementService {

	//class variables
	private FinishedDAO finishedDAO;
	
	//constructor
	
	public FinishedManagementServiceImpl(FinishedDAO finishedDAO) {
		super();
		this.finishedDAO = finishedDAO;
	}
	
	public Collection<Finished> listFinished() {
		return finishedDAO.readFinisheds();
	}



	public void addFinished(Finished finished) {
		finishedDAO.createFinished(finished);

	}

}
