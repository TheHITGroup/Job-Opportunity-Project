package com.hit.controller;

import java.sql.PreparedStatement;

/**
 * This is the controller for the Uses relation
 *
 */
public class UsesController extends Controller
{
	/**
	 * Given a techId and jobId, this method inserts a Uses tuple into the database
	 * 
	 * @param techId
	 * @param jobId
	 * 
	 * @return true if successful; false otherwise
	 */
	public boolean insertUses(int techId, int jobId)
	{
		String sql = "INSERT INTO Uses (t_id, j_id) VALUES(" + techId + ", " + jobId+ ")";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		return executeInsertQuery(preparedStatement);
	}
}
