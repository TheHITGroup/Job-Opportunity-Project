package com.hit.controller;

import java.util.ArrayList;
import java.util.List;

public class OtherRequirementsController extends Controller
{
	public int addJob(int zipcode, String[] techs, int userId) 
	{		
		JobController jc = new JobController();
		int jobId = jc.insertJob(userId, zipcode);
		
		List<Integer> techIds = techHandler(techs);
		
		UsesController uc = new UsesController();
		for(int techId: techIds) {
			uc.insertUses(techId, jobId);
		}
		
		return jobId;
	}

	/**
	 * Gets the techId from DB if it exists; otherwise inserts new tech in DB and gets new id
	 * 
	 * @param techs
	 * @return list of techIds
	 */
	private List<Integer> techHandler(String[] techs)
	{
		TechnologyController tc = new TechnologyController();
		List<Integer> techIds = new ArrayList<>();
		
		for(String tech: techs) {
			int techId;
			if(tc.techExists(tech)) {
				System.out.println("exists\n");
				techId = tc.getIdByName(tech);
			} else {
				System.out.println("does not exist\n");
				techId = tc.insertTech(tech);
			}
			techIds.add(techId);
		}
		return techIds;
	}
}
