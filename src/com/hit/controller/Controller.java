package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * Constructor superclass. Inherited by ComplexQueryController, PatternController, and StringMaker
 *
 */
public class Controller
{
	
	/**
	 * Gets a prepared statement from a SQL query
	 * 
	 * @param sql
	 * @return
	 */
	public static PreparedStatement getPreparedStatement(String sql)
	{
		DbController Dbc = new DbController();
		PreparedStatement preparedStatement = Dbc.getPreparedStatement(sql);
		
		return preparedStatement;
	}
	

	public static PreparedStatement getPreparedStatementWithLastInsertId(String sql)
	{
		DbController Dbc = new DbController();
		PreparedStatement preparedStatement = Dbc.getPreparedStatementWithLastInsertId(sql);
		
		return preparedStatement;
	}
	/**
	 * Gets a ResultObject from a PreparedStatement object
	 * 
	 * @param preparedStatement
	 * @return ResultSet
	 */
	public static ResultSet getResultSet(PreparedStatement preparedStatement)
	{
		ResultSet resultSet = null;
		
		try {
		    resultSet = preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultSet;
	}
	
	public static boolean executeInsertQuery(PreparedStatement preparedStatement)
	{ 
		try {
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
		
	}

	/**
	 * Returns true if ResultSet object has more than one row; false otherwise
	 * 
	 * @param resultSet
	 * @return boolean
	 */
	public boolean resultHasMoreThanOneRow(ResultSet resultSet) 
	{
		int i = 0;
		try {
			while(resultSet.next()) {
				i++;
			}	
			//set the row pointer back to the first row
			resultSet.beforeFirst();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return i > 1;	
	}
	
	/**
	 * Gets the result by name of column. Resets the row pointer in the ResultSet object to
	 * its original position.
	 * 
	 * @param columnName
	 * @param resultSet
	 * @return result
	 */
	public String getResultByColumnNameWithReset(String columnName, ResultSet resultSet)
	{	
		String result = "";
		try {
			//advance the row pointer to the first row
			resultSet.next();
			
			result = resultSet.getString(columnName);
			
			//set the row pointer back to the first row
			resultSet.beforeFirst();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public int getIntResultByColumnNameWithReset(String columnName, ResultSet resultSet)
	{	
		int result = -1;
		try {
			//advance the row pointer to the first row
			resultSet.next();
			
			result = resultSet.getInt(columnName);
			
			//set the row pointer back to the first row
			resultSet.beforeFirst();
		} catch (Exception e) {
			System.out.println(columnName);
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public int getIntResultByColumnNameNoReset(String columnName, ResultSet resultSet)
	{	
		int result = -1;
		try {
			//advance the row pointer to the first row
			resultSet.next();
			
			result = resultSet.getInt(columnName);
		} catch (Exception e) {
			System.out.println(columnName);
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * Gets the result by name of column. Does not reset the row pointer in the ResultSet object to
	 * its original position.
	 * 
	 * @param columnName
	 * @param resultSet
	 * @return result
	 */
	
	public static String getResultByColumnNameNoReset(String columnName, ResultSet resultSet)
	{	
		String result = "";
		try {
			//advance the row pointer to the first row
			resultSet.next();
			
			result = resultSet.getString(columnName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * gets size of ResultSet object
	 * 
	 * @param resultSet
	 * @return size of ResultSet
	 */
	public static int getSizeOfResultSet(ResultSet resultSet)
	{
		int size = 0;
		
		try {
			if(resultSet.last()) {
				size = resultSet.getRow();
				
				//set the row pointer back to the first row
				resultSet.beforeFirst();
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return size;
	}
	
	/**
	 * Sets the row pointer to the specified rowNum
	 * 
	 * @param resultSet
	 * @param rowNum row at which to point the pointer
	 */
	public static void setRow(ResultSet resultSet, int rowNum)
	{
		try {
			//ResultSet.absolute cannot absolute position to row 0, so we do it ourself
			if(rowNum == 0) {
				resultSet.beforeFirst();
				return;
			}
			resultSet.absolute(rowNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getIdForLastInsert(PreparedStatement preparedStatement)
	{
		ResultSet resultSet = null;
		int id = -1;
		try {
			resultSet = preparedStatement.getGeneratedKeys();
			
			resultSet.next();
			
			id = resultSet.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return id;
	}

}
