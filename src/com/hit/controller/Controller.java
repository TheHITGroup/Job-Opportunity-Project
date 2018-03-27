package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Controller
{
	public ResultSet getResultSet(PreparedStatement preparedStatement)
	{
		ResultSet resultSet = null;
		
		try {
		    resultSet = preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultSet;
	}

	public boolean resultHasMoreThanOneRow(ResultSet resultSet) 
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
		return i > 1;	
	}
	
	public String getResultByColumnName(String columnName, ResultSet resultSet)
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

}
