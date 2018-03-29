package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Queries.ComplexQueries;

public class ComplexQueryController extends Controller
{
	public String getMostPopularOfTwoTechnologiesByCityState(String techOne, String techTwo, String city, String state)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSqlForMostPopularOfTwoTechnologies(techOne, techTwo, city, state);
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
	
		StringMaker sm = new StringMaker();
		String resultString = sm.getResultStringForMostPopularTechnology(resultSet);
		
		return resultString;	    
	}
	
	public String getMostPopularOfTwoTechnologiesByZip(String techOne, String techTwo, String zip)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSqlForMostPopularOfTwoTechnologiesByZip(techOne, techTwo, zip);
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		StringMaker sm = new StringMaker();
		String resultString = sm.getResultStringForMostPopularTechnology(resultSet);
		
		return resultString;
	}
	
	public String getMostPopularOfTwoCityStatesForTechnology(String tech, 
			String cityOne, String stateOne, String cityTwo, String stateTwo)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSQLForMostPopularOfTwoCityStatesForTechnology(tech, cityOne, stateOne, cityTwo, stateTwo);
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		StringMaker sm = new StringMaker();
		String resultString = sm.getResultStringForMostPopularCityState(resultSet);
		
		return resultString;		
	}
	
	public String getMostPopularFrameworkForLanguageInCityState(String language, String city, String state)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSQLForMostPopularFrameworkForLanguageInCityState(language, city, state);
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		StringMaker sm = new StringMaker();
		String resultString = sm.getResultStringForMostPopularFrameworkForLanguageInCityState(resultSet);
		
		return resultString;
	}
	
	public String getCityInStateWithAtLeastNJobsForTechnology(String state, String tech, String numJobsRequest)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSQLForCityInStateWithAtLeastNJobsForTechnology(state, tech, numJobsRequest);
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		StringMaker sm = new StringMaker();
		String resultString = sm.getResultStringForCityInStateWithAtLeastNJobsForTechnology(resultSet, numJobsRequest);
		
		return resultString;
	}

}
