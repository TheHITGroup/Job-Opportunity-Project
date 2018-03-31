package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Queries.ComplexQueries;
/*
 * Class containing the queries used to fulfill requirement number 4 in the grading rubric. Inherits from Controller
 * All the SQL for this class is stored in Queries/ComplexQueries.java
 */
public class ComplexQueryController extends Controller
{
	/**
	 * Given two technologies, a city, and a state, this method returns which of the two technologies has
	 * the most jobs in the specified city as well as the number of jobs available for that technology.
	 * 
	 * @param techOne 
	 * @param techTwo
	 * @param city
	 * @param state
	 * 
	 * @return Result String
	 */
	public static String getPopOfTwoTechsByCityState(String techOne, String techTwo, String city, String state)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSqlForMostPopularOfTwoTechnologies(techOne, techTwo, city, state);
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
	
		StringMaker sm = new StringMaker();
		String resultString = sm.getStringPopOfTwoTechsByZip(resultSet);
		
		return resultString;	    
	}
	
	/**
	 * Given two technologies and a zipcode this method returns which of the two technologies has
	 * the most jobs in the specified zipcode as well as the number of jobs available for that technology.
	 * 
	 * @param techOne
	 * @param techTwo
	 * @param zip
	 * 
	 * @return Result String
	 */
	public static String getPopOfTwoTechsByZip(String techOne, String techTwo, String zip)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSqlForMostPopularOfTwoTechnologiesByZip(techOne, techTwo, zip);
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		StringMaker sm = new StringMaker();
		String resultString = sm.getStringPopOfTwoTechsByZip(resultSet);
		
		return resultString;
	}
	
	/**
	 * Given two cities and states and a technology, this method returns which of the two cities
	 * has the most job openings for the given technology as well as the number of jobs for that technology.
	 * 
	 * @param tech
	 * @param cityOne
	 * @param stateOne
	 * @param cityTwo
	 * @param stateTwo
	 * 
	 * @return Result String
	 */
	public static String getPopOfTwoCityStatesForTech(String tech, 
			String cityOne, String stateOne, String cityTwo, String stateTwo)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSQLForPopOfTwoCityStatesForTech(tech, cityOne, stateOne, cityTwo, stateTwo);
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		StringMaker sm = new StringMaker();
		String resultString = sm.getStringPopOfTwoCityStatesForTech(resultSet);
		
		return resultString;		
	}
	
	/**
	 * Given a programming language, city, and state, this method returns the framework with the most job openings
	 * associated with the given language in the specified city, state combo.
	 * 
	 * @param language
	 * @param city
	 * @param state
	 * 
	 * @return Result String
	 */
	public static String getPopFWForLangInCityState(String language, String city, String state)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSQLForMostPopularFrameworkForLanguageInCityState(language, city, state);
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		StringMaker sm = new StringMaker();
		String resultString = sm.getStringPopFWForLangInCityState(resultSet);
		
		return resultString;
	}
	
	/**
	 * Given a state, technology, and number of jobs, this method returns all cities in the specified state
	 * with at least the specified number of jobs for the given technology
	 * 
	 * @param state
	 * @param tech
	 * @param numJobsRequest
	 * 
	 * @return Result String
	 */
	public static String getCityInStateAtLeastNJobsForTech(String state, String tech, String numJobsRequest)
	{
		ComplexQueries SQL = new ComplexQueries();
		String sql = SQL.getSQLForCityInStateWithAtLeastNJobsForTechnology(state, tech, numJobsRequest);
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		StringMaker sm = new StringMaker();
		String resultString = sm.getStringCityInStateAtLeastNJobsForTech(resultSet, numJobsRequest);
		
		return resultString;
	}

}
