package com.hit.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Queries.SQLQueries;

public class TechnologyController
{
	public String getMostPopularOfTwoTechnologies(String techOne, String techTwo, String city, String state)
	{
		SQLQueries SQL = new SQLQueries();
		String sql = SQL.getSqlForMostPopularOfTwoTechnologies(techOne, techTwo, city, state);
		
    	DbController Dbc = new DbController();
        Connection conn = Dbc.getDBConnection();
	    PreparedStatement preparedStatement = Dbc.getPreparedStatement(conn, sql);
	    
	    ResultSet resultSet = getResultSet(preparedStatement);
	    
	    String resultString = getResultString(resultSet);
	    
	    return resultString;	    
	}
	
	private ResultSet getResultSet(PreparedStatement preparedStatement)
	{
		ResultSet resultSet = null;
		
		try {
		    resultSet = preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultSet;
	}

	private String getResultString(ResultSet resultSet)
	{	
		String name = getResultByColumnName("name", resultSet);
		String numJobs = getResultByColumnName("count", resultSet);
		
		String resultString =  "";
		
		if(resultHasMoreThanOneRow(resultSet)) {
			resultString = "Both languages have the same number of jobs with " + numJobs + " jobs.";			
		} else {
			resultString = "The most popular of the two languages is " + name + ", having " + numJobs + " jobs.";
		}
		
		return resultString;
	}
	
	private String getResultByColumnName(String columnName, ResultSet resultSet)
	{	
		String result = "";
		try {
			//advance the row pointer to the first row
			resultSet.next();
			
			result = resultSet.getString(columnName);
			
			//set the row pointer back
			resultSet.relative(-1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	private boolean resultHasMoreThanOneRow(ResultSet resultSet) 
	{
		int i = 0;
		try {
			while(resultSet.next()) {
				resultSet.getString("name");
				i++;
			}		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return i == 2;	
	}

}
