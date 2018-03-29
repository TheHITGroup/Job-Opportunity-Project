package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hit.json.TechnologyJSON;

public class TechnologyController extends Controller
{
	public int getIdByName(String name)
	{
		String sql = "SELECT id FROM Technology WHERE name='" + name + "'";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		int id = getIntResultByColumnNameWithReset("id", resultSet);
		
		return id;
	}
	
	public static List<TechnologyJSON> getListOfTechnologies()
	{
		String sql = "SELECT * FROM Technology";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);

		List<TechnologyJSON> Techs = new ArrayList<TechnologyJSON>();
		
		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		for(int i = 0; i < sizeOfResultSet; i++) {
			TechnologyJSON Tech = new TechnologyJSON();
			
			setRow(resultSet, i);
			String id = getResultByColumnNameNoReset("id", resultSet);
			Tech.setId(id);
			
			setRow(resultSet, i);
			String name = getResultByColumnNameNoReset("name", resultSet);
			Tech.setName(name);
			
			setRow(resultSet, i);
			String type = getResultByColumnNameNoReset("type", resultSet);
			Tech.setType(type);
			
			Techs.add(Tech);
		}	
		return Techs;
	}
        
      
        
	
	public boolean techExists(String name)
	{
		String sql = "SELECT id FROM Technology where name='" + name + "'";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		return (getSizeOfResultSet(resultSet) != 0);
	}

	public int insertTech(String tech)
	{
		String sql = "INSERT INTO Technology (name) VALUES('" + tech + "')";
		
		PreparedStatement preparedStatement = getPreparedStatementWithLastInsertId(sql);
		
		executeInsertQuery(preparedStatement);
		
		int techId = getIdForLastInsert(preparedStatement);
		
		return techId;
		
	}
}
