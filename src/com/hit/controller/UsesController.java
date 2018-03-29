package com.hit.controller;

import java.sql.PreparedStatement;

public class UsesController extends Controller
{
	public boolean insertUses(int techId, int jobId)
	{
		String sql = "INSERT INTO Uses (t_id, j_id) VALUES(" + techId + ", " + jobId+ ")";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		return executeInsertQuery(preparedStatement);
	}
}
