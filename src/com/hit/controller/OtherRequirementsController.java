package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hit.json.UserJobJSON;

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
	
	public List<UserJobJSON> getJobsForUser(int userId)
	{
		String sql = "SELECT city, state, L.zipcode, name, J.id as jobId FROM Location AS L, Uses AS Us, Job AS J, User AS Ur, Technology AS T" + 
				"	WHERE Ur.id=" + userId + " AND J.u_id=Ur.id AND Us.j_id=J.id AND Us.t_id=T.id AND L.zipcode=J.zipcode ORDER BY J.id";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		List<UserJobJSON> UJJs = jobsForUserListBuilder(resultSet);
		
		return UJJs;
		
	}
	
	private List<UserJobJSON> jobsForUserListBuilder(ResultSet resultSet)
	{
		List<UserJobJSON> UJJs = new ArrayList<>();
		
		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		for(int i = 0; i < sizeOfResultSet; i++) {
			UserJobJSON UJJ = new UserJobJSON();
			
			setRow(resultSet, i);
			
			String city = getResultByColumnNameNoReset("city", resultSet);
			UJJ.setCity(city);
			setRow(resultSet, i);
			
			String state = getResultByColumnNameNoReset("state", resultSet);
			UJJ.setState(state);
			setRow(resultSet, i);
			
			int zipcode = getIntResultByColumnNameNoReset("zipcode", resultSet);
			UJJ.setZipcode(zipcode);
			setRow(resultSet, i);
			
			String tech = getResultByColumnNameNoReset("name", resultSet);
			List<String> techs = new ArrayList<>();
			techs.add(tech);
			setRow(resultSet, i);
			
			i = multipleTechsForOneJobHandler(UJJ, techs, resultSet, i);
			
			UJJs.add(UJJ);
		}
		return UJJs;
	}

	private int multipleTechsForOneJobHandler(UserJobJSON UJJ, List<String> techs, ResultSet resultSet, int i)
	{
		
		int jobIdForPreviousTech = getIntResultByColumnNameNoReset("jobId", resultSet);
		int jobIdForCurrentTech = getIntResultByColumnNameNoReset("jobId", resultSet);
		while(jobIdForCurrentTech == jobIdForPreviousTech) {
			i++;
			
			setRow(resultSet, i);
			String currentTech = getResultByColumnNameNoReset("name", resultSet);
			techs.add(currentTech);
			
			//advance to next job
			jobIdForCurrentTech = getIntResultByColumnNameNoReset("jobId", resultSet);
		}
		UJJ.setTechs(techs);
		
		return i;		
	}
}
