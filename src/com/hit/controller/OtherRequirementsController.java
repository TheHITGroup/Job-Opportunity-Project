package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hit.json.JobOpeningJSON;

/**
 *  Class containing the queries used to fulfill requirements 1 and 2 in the grading rubric
 *
 */
public class OtherRequirementsController extends Controller
{

	/**
	 * Adds a new job opening entered by the user to the database and creates all necessary relations.
	 * If the new job opening is for a programming language or framework not already in the technology
	 * table, it adds the new technology to the table.
	 * 
	 * @param zipcode
	 * @param techs
	 * @param userId
	 * @return
	 */
	public int addJobOpening(int zipcode, String[] techs, int userId) 
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
				techId = tc.getIdByName(tech);
			} else {
				techId = tc.insertTech(tech);
			}
			techIds.add(techId);
		}
		return techIds;
	}
	
	/**
	 * Gets a list of all job openings created by a user identified by userId. A job opening
	 * contains the city, state, and zipcode of the job as well as any technologies being used.
	 * 
	 * @param userId
	 * 
	 * @return List of JobOpenings
	 */
	public List<JobOpeningJSON> getJobOpeningsForUser(int userId)
	{
		String sql = "SELECT city, state, L.zipcode, name, J.id as jobId FROM Location AS L, Uses AS Us, Job AS J, User AS Ur, Technology AS T" + 
				"	WHERE Ur.id=" + userId + " AND J.u_id=Ur.id AND Us.j_id=J.id AND Us.t_id=T.id AND L.zipcode=J.zipcode ORDER BY J.id";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		List<JobOpeningJSON> UJJs = jobOpeningsForUserListBuilder(resultSet);
		
		return UJJs;
		
	}
	
	/**
	 * Builds the JobOpenings list for the getJobOpeningsForUser method
	 * 
	 * @param resultSet
	 * 
	 * @return List of JobOpenings
	 */
	private List<JobOpeningJSON> jobOpeningsForUserListBuilder(ResultSet resultSet)
	{
		List<JobOpeningJSON> UJJs = new ArrayList<>();
		
		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		for(int i = 0; i < sizeOfResultSet; i++) {
			JobOpeningJSON UJJ = new JobOpeningJSON();
			
			setRow(resultSet, i);
			
			String city = getStringResultByColNameNoReset("city", resultSet);
			UJJ.setCity(city);
			setRow(resultSet, i);
			
			String state = getStringResultByColNameNoReset("state", resultSet);
			UJJ.setState(state);
			setRow(resultSet, i);
			
			int zipcode = getIntResultByColNameNoReset("zipcode", resultSet);
			UJJ.setZipcode(zipcode);
			setRow(resultSet, i);
			
			String tech = getStringResultByColNameNoReset("name", resultSet);
			List<String> techs = new ArrayList<>();
			techs.add(tech);
			setRow(resultSet, i);
			
			i = multipleTechsForOneJobHandler(UJJ, techs, resultSet, i);
			
			UJJs.add(UJJ);
		}
		return UJJs;
	}

	/**
	 * Handles JobOpenings with more than one technology. Increases the row pointer which doubles as
	 * the loop counter for the jobOpeningsForUserListBuilder method
	 * 
	 * @param UJJ
	 * @param techs
	 * @param resultSet
	 * @param rowPointer
	 * 
	 * @return row pointer
	 */
	private int multipleTechsForOneJobHandler(JobOpeningJSON UJJ, List<String> techs, ResultSet resultSet, int rowPointer)
	{
		
		int jobIdForPreviousTech = getIntResultByColNameNoReset("jobId", resultSet);
		int jobIdForCurrentTech = getIntResultByColNameNoReset("jobId", resultSet);
		while(jobIdForCurrentTech == jobIdForPreviousTech) {
			rowPointer++;
			
			setRow(resultSet, rowPointer);
			String currentTech = getStringResultByColNameNoReset("name", resultSet);
			techs.add(currentTech);
			
			//advance to next job
			jobIdForCurrentTech = getIntResultByColNameNoReset("jobId", resultSet);
		}
		UJJ.setTechs(techs);
		
		return rowPointer;		
	}
}
