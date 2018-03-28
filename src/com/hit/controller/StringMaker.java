package com.hit.controller;
import java.sql.ResultSet;

import com.hit.controller.Controller;

public class StringMaker extends Controller
{

	public String getResultStringForMostPopularTechnology(ResultSet resultSet)
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

	public String getResultStringForMostPopularCityState(ResultSet resultSet)
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

	public String getResultStringForMostPopularFrameworkForLanguageInCityState(ResultSet resultSet)
	{
		String city = getResultByColumnNameWithReset("city", resultSet);
		String state = getResultByColumnNameWithReset("state", resultSet);
		String numJobs = getResultByColumnNameWithReset("count", resultSet);
		String frameworkString = buildFrameworkList(resultSet);
		
		String resultString = "The framework" + (resultHasMoreThanOneRow(resultSet) ? "s" : "")
							+ " most used in " + city + ", " + state + " " 
							+ frameworkString + " with " + numJobs + " jobs";
		
		return resultString;
	}

	private String buildFrameworkList(ResultSet resultSet)
	{
		if(!resultHasMoreThanOneRow(resultSet))
			return "is " + getResultByColumnNameWithReset("name", resultSet);

		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		String resultString = "are ";
		for(int i = 1; i < sizeOfResultSet; i++) {
			resultString += getResultByColumnNameNoReset("name", resultSet);
			resultString += (sizeOfResultSet != 2) ? ", " : "";
		}
		resultString += " and " + getResultByColumnNameWithReset("name", resultSet);
		return resultString;
	}

	public String getResultStringForCityInStateWithAtLeastNJobsForTechnology(ResultSet resultSet, String numJobsRequest)
	{
		if(!resultHasMoreThanOneRow(resultSet))
			return "The only city with more than N jobs is " + getResultByColumnNameWithReset("city", resultSet)
					+ " with " + getResultByColumnNameWithReset("count", resultSet) + " jobs.";
		
		String resultString = "The cities with more than " + numJobsRequest + " jobs are ";
		resultString += buildCityList(resultSet);
		
		return resultString;
	}

	private String buildCityList(ResultSet resultSet)
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
		cityAndNumJobsInCityArray[0] = getResultByColumnNameNoReset("city", resultSet);
		setRow(resultSet, rowNum);
		cityAndNumJobsInCityArray[1] = getResultByColumnNameNoReset("count", resultSet);
		
		return cityAndNumJobsInCityArray;
	}

}
