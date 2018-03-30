package com.hit.controller;

import java.sql.PreparedStatement;

/**
 * This is the controller for the Job relation
 *
 */
public class JobController extends Controller
{
	/**
	 * Given a userId and zipcode, this method inserts a Job tuple into the database
	 * 
	 * @param userId
	 * @param zipcode
	 * 
	 * @return id of inserted Job
	 */
	public int insertJob(int userId, int zipcode)
	{
		String sql = "INSERT INTO Job (u_id, zipcode) VALUES(" + userId + ", " + zipcode + ")";
		
		PreparedStatement preparedStatement = getPreparedStatementWithLastInsertId(sql);
		
		executeInsertQuery(preparedStatement);
		
		int jobId = getIdForLastInsert(preparedStatement);
		
		return jobId;
	}
}
