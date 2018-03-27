package com.hit.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Queries.SQLQueries;

public class TechnologyController
{
	public String getMostPopularOfTwoTechnologiesByCityState(String techOne, String techTwo, String city, String state)
	{
		SQLQueries SQL = new SQLQueries();
		String sql = SQL.getSqlForMostPopularOfTwoTechnologies(techOne, techTwo, city, state);
		
		String resultString = getMostPopularOfTwoTechnologiesBySQL(sql);
		
		return resultString;	    
	}
	
	public String getMostPopularOfTwoTechnologiesByZip(String techOne, String techTwo, String zip)
	{
		SQLQueries SQL = new SQLQueries();
		String sql = SQL.getSqlForMostPopularOfTwoTechnologiesByZip(techOne, techTwo, zip);
		
		String resultString = getMostPopularOfTwoTechnologiesBySQL(sql);
		
		return resultString;
	}
	
	public String getMostPopularOfTwoCityStatesForTechnology(String tech, 
			String cityOne, String stateOne, String cityTwo, String stateTwo)
	{
		SQLQueries SQL = new SQLQueries();
		String sql = SQL.getSQLForMostPopularOfTwoCityStatesForTechnology(tech, cityOne, stateOne, cityTwo, stateTwo);
		
		DbController Dbc = new DbController();
		Connection conn = Dbc.getDBConnection();
		PreparedStatement preparedStatement = Dbc.getPreparedStatement(conn, sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		String resultString = getResultStringForMostPopularCityState(resultSet);
		
		return resultString;		
	}

	private String getResultStringForMostPopularCityState(ResultSet resultSet)
	{	
		String city = getResultByColumnName("city", resultSet);
		String state = getResultByColumnName("state", resultSet);
		String numJobs = getResultByColumnName("count", resultSet);
		
		String resultString =  "";
		
		if(resultHasMoreThanOneRow(resultSet)) {
			resultString = "Both cities have the same number of jobs with " + numJobs + " jobs"		
							+ (numJobs.equals("1") ? "" : "s") + ".";			
		} else {
			resultString = "The city with the most jobs is " + city + ", " + state + "" + " with " + numJobs + " job"
							+ (numJobs.equals("1") ? "" : "s") + ".";
		}
		
		return resultString;
	}

	private String getMostPopularOfTwoTechnologiesBySQL(String sql) 
	{
		DbController Dbc = new DbController();
		Connection conn = Dbc.getDBConnection();
		PreparedStatement preparedStatement = Dbc.getPreparedStatement(conn, sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		String resultString = getResultStringForMostPopularTechnology(resultSet);
		
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

	private String getResultStringForMostPopularTechnology(ResultSet resultSet)
	{	
		String name = getResultByColumnName("name", resultSet);
		String numJobs = getResultByColumnName("count", resultSet);
		
		String resultString =  "";
		
		if(resultHasMoreThanOneRow(resultSet)) {
			resultString = "Both languages have the same number of jobs with " + numJobs + " job"
							+ (numJobs.equals("1") ? "" : "s") + ".";
		} else {
			resultString = "The most popular of the two languages is " + name + " with " + numJobs + " job"
							+ (numJobs.equals("1") ? "" : "s") + ".";
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
				resultSet.getInt(1);
				i++;
			}		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return i == 2;	
	}

}
