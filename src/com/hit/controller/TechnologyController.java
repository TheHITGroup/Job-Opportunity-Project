package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hit.json.TechnologyJSON;

public class TechnologyController extends Controller
{
	public List<TechnologyJSON> getListOfTechnologies()
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
}
