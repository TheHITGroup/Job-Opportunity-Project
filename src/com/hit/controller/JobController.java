package com.hit.controller;

import java.sql.PreparedStatement;

public class JobController extends Controller
{
	public int insertJob(int userId, int zipcode)
	{
		String sql = "INSERT INTO Job (u_id, zipcode) VALUES(" + userId + ", " + zipcode + ")";
		
		PreparedStatement preparedStatement = getPreparedStatementWithLastInsertId(sql);
		
		executeInsertQuery(preparedStatement);
		
		int jobId = getIdForLastInsert(preparedStatement);
		
		return jobId;
	}
}
