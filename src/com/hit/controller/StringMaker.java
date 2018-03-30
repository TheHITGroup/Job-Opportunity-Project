package com.hit.controller;
import java.sql.ResultSet;

import com.hit.controller.Controller;

/**
 * This class creates result strings for the ComplexQueries class. Inherits from Controller.
 * @author tom
 *
 */
public class StringMaker extends Controller
{

	/**
	 * Creates result string for getPopOfTwoTechsByCityState and getPopOfTwoTechsByZip method in ComplexQueries class.
	 * 
	 * @param resultSet
	 * 
	 * @return Result String
	 */
	public String getStringPopOfTwoTechsByZip(ResultSet resultSet)
	{	
		if(getSizeOfResultSet(resultSet) == 0)
			return getNoResultString();
		
		String name = getStringResultByColNameAndReset("name", resultSet);
		String numJobs = getStringResultByColNameAndReset("count", resultSet);
		
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

	/**
	 * Creates result string for getPopOfTwoCityStatesForTech in ComplexQuerie class.
	 * 
	 * @param resultSet
	 * 
	 * @return Result String
	 */
	public String getStringPopOfTwoCityStatesForTech(ResultSet resultSet)
	{	
		if(getSizeOfResultSet(resultSet) == 0)
			return getNoResultString();
		
		String city = getStringResultByColNameAndReset("city", resultSet);
		String state = getStringResultByColNameAndReset("state", resultSet);
		String numJobs = getStringResultByColNameAndReset("count", resultSet);
		
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


	/**
	 * Creates result string for getPopFWForLangInCityState in ComplexQuerie class.
	 * 
	 * @param resultSet
	 * 
	 * @return Result String
	 */
	public String getStringPopFWForLangInCityState(ResultSet resultSet)
	{
		if(getSizeOfResultSet(resultSet) == 0)
			return getNoResultString();
		
		String city = getStringResultByColNameAndReset("city", resultSet);
		String state = getStringResultByColNameAndReset("state", resultSet);
		String numJobs = getStringResultByColNameAndReset("count", resultSet);
		String frameworkString = frameworkStringBuilder(resultSet);
		
		String resultString = "The framework" + (resultHasMoreThanOneRow(resultSet) ? "s" : "")
							+ " most used in " + city + ", " + state + " " 
							+ frameworkString + " with " + numJobs + " jobs";
		
		return resultString;
	}

	/**
	 * Builds a string representation of frameworks for the getStringPopFWForLangInCityState method
	 * 
	 * @param resultSet
	 * 
	 * @return String
	 */
	private String frameworkStringBuilder(ResultSet resultSet)
	{
		if(!resultHasMoreThanOneRow(resultSet))
			return "is " + getStringResultByColNameAndReset("name", resultSet);

		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		String resultString = "are ";
		for(int i = 1; i < sizeOfResultSet; i++) {
			resultString += getStringResultByColNameNoReset("name", resultSet);
			resultString += (sizeOfResultSet != 2) ? ", " : "";
		}
		resultString += " and " + getStringResultByColNameAndReset("name", resultSet);
		
		return resultString;
	}


	/**
	 * Creates result string for getCityInStateAtLeastNJobsForTech in ComplexQuerie class.
	 * 
	 * @param resultSet
	 * 
	 * @return Result String
	 */
	public String getStringCityInStateAtLeastNJobsForTech(ResultSet resultSet, String numJobsRequest)
	{
		if(getSizeOfResultSet(resultSet) == 0)
			return getNoResultString();
		
		if(!resultHasMoreThanOneRow(resultSet))
			return "The only city with more than N jobs is " + getStringResultByColNameAndReset("city", resultSet)
					+ " with " + getStringResultByColNameAndReset("count", resultSet) + " jobs.";
		
		String resultString = "The cities with more than " + numJobsRequest + " jobs are ";
		resultString += cityStringBuilder(resultSet);
		
		return resultString;
	}

	/**
	 * Builds a string representation of cities for getStringCityInStateAtLeastNJobsForTech method
	 * 
	 * @param resultSet
	 * 
	 * @return String
	 */
	private String cityStringBuilder(ResultSet resultSet)
	{
		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		String resultString = "";
		int i = 0;
		for(; i < sizeOfResultSet - 1; i++) {
			String[] cityAndNumJobsInCityArray = getCityAndNumJobsArray(resultSet, i);
			
			resultString += cityAndNumJobsInCityArray[0] + " with " + cityAndNumJobsInCityArray[1] + " jobs";
			resultString += (sizeOfResultSet != 2) ? ", " : " ";
		}
		String[] cityAndNumJobsInCityArray = getCityAndNumJobsArray(resultSet, i);		
		resultString += " and " + cityAndNumJobsInCityArray[0] + " with " + cityAndNumJobsInCityArray[1] + " jobs.";
		
		return resultString;
	}
	
	private String[] getCityAndNumJobsArray(ResultSet resultSet, int rowNum)
	{
		String[] cityAndNumJobsInCityArray = new String[2];
		
		setRow(resultSet, rowNum); 
		cityAndNumJobsInCityArray[0] = getStringResultByColNameNoReset("city", resultSet);
		
		setRow(resultSet, rowNum);
		cityAndNumJobsInCityArray[1] = getStringResultByColNameNoReset("count", resultSet);
		
		return cityAndNumJobsInCityArray;
	}
	
	/**
	 * Returns a string to tell the user their query did not produce any results. Called
	 * by all public methods in the class.
	 * 
	 * @return String
	 */
	private String getNoResultString() 
	{
		return "Sorry, your search did not produce any results.";
	}

}
