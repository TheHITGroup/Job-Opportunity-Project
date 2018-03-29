package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hit.json.LocationJSON;

public class LocationController extends Controller
{
	public List<LocationJSON> getListOfLocations()
	{
		String sql = "SELECT * FROM Location";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);

		List<LocationJSON> Locations = new ArrayList<LocationJSON>();
		
		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		for(int i = 0; i < sizeOfResultSet; i++) {
			LocationJSON Location = new LocationJSON();
			
			setRow(resultSet, i);
			String zipcode = getResultByColumnNameNoReset("zipcode", resultSet);
			Location.setZipcode(zipcode);
			
			setRow(resultSet, i);
			String city = getResultByColumnNameNoReset("city", resultSet);
			Location.setCity(city);
			
			setRow(resultSet, i);
			String state = getResultByColumnNameNoReset("state", resultSet);
			Location.setState(state);
			
			Locations.add(Location);
		}	
		return Locations;
	}

}
