package com.hit.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Queries.ComplexQueries;

public class ComplexQueryController extends Controller
{
	public String getMostPopularOfTwoTechnologiesByCityState(String techOne, String techTwo, String city, String state)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSqlForMostPopularOfTwoTechnologies(techOne, techTwo, city, state);
		
		String resultString = getMostPopularOfTwoTechnologiesBySQL(sql);
		
		return resultString;	    
	}
	
	public String getMostPopularOfTwoTechnologiesByZip(String techOne, String techTwo, String zip)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSqlForMostPopularOfTwoTechnologiesByZip(techOne, techTwo, zip);
		
		String resultString = getMostPopularOfTwoTechnologiesBySQL(sql);
		
		return resultString;
	}
	
	public String getMostPopularOfTwoCityStatesForTechnology(String tech, 
			String cityOne, String stateOne, String cityTwo, String stateTwo)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSQLForMostPopularOfTwoCityStatesForTechnology(tech, cityOne, stateOne, cityTwo, stateTwo);
		
		DbController Dbc = new DbController();
		Connection conn = Dbc.getDBConnection();
		PreparedStatement preparedStatement = Dbc.getPreparedStatement(conn, sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		String resultString = getResultStringForMostPopularCityState(resultSet);
		
		return resultString;		
	}
	
	public String getMostPopularFrameworkForLanguageInCityState(String language, String city, String state)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSQLForMostPopularFrameworkForLanguageInCityState(language, city, state);
		
		DbController Dbc = new DbController();
		Connection conn = Dbc.getDBConnection();
		PreparedStatement preparedStatement = Dbc.getPreparedStatement(conn, sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		
		String resultString = getResultStringForMostPopularFrameworkForLanguageInCityState(resultSet);
		
		return resultString;
	}

	private String getResultStringForMostPopularFrameworkForLanguageInCityState(ResultSet resultSet)
	{
		String city = getResultByColumnNameWithReset("city", resultSet);
		String state = getResultByColumnNameWithReset("state", resultSet);
		String numJobs = getResultByColumnNameWithReset("count", resultSet);
		String frameworkString = getFrameworkString(resultSet);
		
		String resultString = "The framework" + (resultHasMoreThanOneRow(resultSet) ? "s" : "")
							+ " most used in " + city + ", " + state + " " 
							+ frameworkString + " with " + numJobs + " jobs";
		
		return resultString;
	}

	private String getFrameworkString(ResultSet resultSet)
	{
		if(!resultHasMoreThanOneRow(resultSet))
			return "is " + getResultByColumnNameWithReset("name", resultSet);

		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		String resultString = "are ";
		for(int i = 0; i < sizeOfResultSet; i++) {
			resultString += getResultByColumnNameNoReset("name", resultSet);
			resultString += !(sizeOfResultSet != 2) ? ", " : "";
		}
		resultString += " and " + getResultByColumnNameWithReset("name", resultSet);
		return resultString;
	}

	private String getResultStringForMostPopularCityState(ResultSet resultSet)
	{	
		String city = getResultByColumnNameWithReset("city", resultSet);
		String state = getResultByColumnNameWithReset("state", resultSet);
		String numJobs = getResultByColumnNameWithReset("count", resultSet);
		
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

	private String getResultStringForMostPopularTechnology(ResultSet resultSet)
	{	
		String name = getResultByColumnNameWithReset("name", resultSet);
		String numJobs = getResultByColumnNameWithReset("count", resultSet);
		
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

}
