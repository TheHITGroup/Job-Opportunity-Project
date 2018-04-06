package com.hit.controller;

import java.sql.ResultSet;

public class PatternStringMaker extends Controller
{

	public String getStringPopTechInZip(ResultSet resultSet, String type)
	{	
		if(getSizeOfResultSet(resultSet) == 0)
			return getNoResultString();
		
		String name = getStringResultByColNameAndReset("name", resultSet);
		String numJobs = getStringResultByColNameAndReset("count2", resultSet);
		
		String resultString =  "";
		
		if(resultHasMoreThanOneRow(resultSet)) {
			String langList = techListBuilder(resultSet);
			resultString += "The " + type + "s with the most jobs are " + langList;
		} else {
			resultString += "The " + type + " with the most jobs is " + name + " with " + numJobs + " job"
							+ (numJobs.equals("1") ? "" : "s") + ".";
		}
		
		return resultString;
	}
	
	private String techListBuilder(ResultSet resultSet)
	{
		String resultString = "";
		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		for(int i = 1; i < sizeOfResultSet; i++) {
			setRow(resultSet, i);
			resultString += getStringResultByColNameNoReset("name", resultSet);
			resultString += " with ";
			setRow(resultSet, i);
			resultString += getStringResultByColNameNoReset("count2", resultSet);
			resultString += " jobs";
			resultString += (sizeOfResultSet != 2) ? ", " : "";
		}
		resultString += " and " + getStringResultByColNameAndReset("name", resultSet);
		
		return resultString;
	}

	public String getStringPopLocationForTech(ResultSet resultSet, String location)
	{	
		if(getSizeOfResultSet(resultSet) == 0)
			return getNoResultString();
		
		String name = getStringResultByColNameAndReset(location, resultSet);
		String numJobs = getStringResultByColNameAndReset("count2", resultSet);
		
		String resultString =  "";
		
		if(resultHasMoreThanOneRow(resultSet)) {
			String locationList = locationListBuilder(resultSet, location);
			resultString += "The ";
			resultString += (location.equals("city")) ? "citie" : "state";
			resultString += "s with the most jobs are " + locationList;					
		} else {
			resultString = "The " + location + " with the most jobs is " + name + " with " + numJobs + " job"
							+ (numJobs.equals("1") ? "" : "s") + ".";
		}
		
		return resultString;
	}

	private String locationListBuilder(ResultSet resultSet, String location)
	{
		String resultString = "";
		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		for(int i = 1; i < sizeOfResultSet; i++) {
			setRow(resultSet, i);
			resultString += getStringResultByColNameNoReset(location, resultSet);
			resultString += " with ";
			setRow(resultSet, i);
			resultString += getStringResultByColNameNoReset("count2", resultSet);
			resultString += " jobs";
			resultString += (sizeOfResultSet != 2) ? ", " : "";
		}
		resultString += " and " + getStringResultByColNameAndReset("name", resultSet);
		
		return resultString;
	}

}
