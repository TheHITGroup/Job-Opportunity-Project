package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hit.json.TechnologyJSON;

/**
 * This is the controller for Technology entity
 *
 */
public class TechnologyController extends Controller
{
	/**
	 * Given a name of a technology, this method returns the technology's id
	 * 
	 * @param name
	 * 
	 * @return tech id
	 */
	public int getIdByName(String name)
	{
		String sql = "SELECT id FROM Technology WHERE name='" + name + "'";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		int id = getIntResultByColNameAndReset("id", resultSet);
		
		return id;
	}

	/**
	 * This method returns true if the technology is in the database; false otherwise
	 * 
	 * @param name
	 * 
	 * @return boolean
	 */
	public boolean techExists(String name)
	{
		String sql = "SELECT id FROM Technology where name='" + name + "'";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		return (getSizeOfResultSet(resultSet) != 0);
	}

	/**
	 * Given a technology name, this method inserts a Technology tuple into the database
	 * 
	 * @param userId
	 * @param zipcode
	 * 
	 * @return id of inserted Technology
	 */
	public int insertTech(String tech)
	{
		String sql = "INSERT INTO Technology (name) VALUES('" + tech + "')";
		
		PreparedStatement preparedStatement = getPreparedStatementWithLastInsertId(sql);
		
		executeInsertQuery(preparedStatement);
		
		int techId = getIdForLastInsert(preparedStatement);
		
		return techId;	
	}

	/**
	 * Returns of list of all Technology tuples
	 * 
	 * @return List of Technology Json objects
	 */
	public static List<TechnologyJSON> getListOfTechnologies()
	{
		String sql = "SELECT * FROM Technology ORDER BY name";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);

		List<TechnologyJSON> techs = technologyListBuilder(resultSet);
		
		return techs;
	}

	/**
	 * Builds the list for the getListOfTechnologies method
	 * 
	 * @param resultSet
	 * 
	 * @return List of Technology Json objects
	 */
	private static List<TechnologyJSON> technologyListBuilder(ResultSet resultSet)
	{
		List<TechnologyJSON> techs = new ArrayList<TechnologyJSON>();
		
		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		for(int i = 0; i < sizeOfResultSet; i++) {
			TechnologyJSON Tech = new TechnologyJSON();
			
			setRow(resultSet, i);
			String id = getStringResultByColNameNoReset("id", resultSet);
			Tech.setId(id);
			
			setRow(resultSet, i);
			String name = getStringResultByColNameNoReset("name", resultSet);
			Tech.setName(name);
			
			setRow(resultSet, i);
			String type = getStringResultByColNameNoReset("type", resultSet);
			Tech.setType(type);
			
			techs.add(Tech);
		}	
		return techs;
	}
}
