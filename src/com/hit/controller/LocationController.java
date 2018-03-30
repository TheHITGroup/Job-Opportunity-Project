package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hit.json.LocationJSON;

/**
 * This is the class for the Location entity
 *
 */
public class LocationController extends Controller
{
	/**
	 * Returns of list of all Location tuples
	 * 
	 * @return List of Location Json objects
	 */
	public List<LocationJSON> getListOfLocations()
	{
		String sql = "SELECT * FROM Location";
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		ResultSet resultSet = getResultSet(preparedStatement);

		List<LocationJSON> locations = locationListBuilder(resultSet);
		
		return locations;
	}

	/**
	 * Builds the list for the getListOfLocations method
	 * 
	 * @param resultSet
	 * 
	 * @return List of Location Json objects
	 */
	private List<LocationJSON> locationListBuilder(ResultSet resultSet)
	{
		List<LocationJSON> locations = new ArrayList<LocationJSON>();
		
		int sizeOfResultSet = getSizeOfResultSet(resultSet);
		for(int i = 0; i < sizeOfResultSet; i++) {
			LocationJSON Location = new LocationJSON();
			
			setRow(resultSet, i);
			String zipcode = getStringResultByColNameNoReset("zipcode", resultSet);
			Location.setZipcode(zipcode);
			
			setRow(resultSet, i);
			String city = getStringResultByColNameNoReset("city", resultSet);
			Location.setCity(city);
			
			setRow(resultSet, i);
			String state = getStringResultByColNameNoReset("state", resultSet);
			Location.setState(state);
			
			locations.add(Location);
		}	
		return locations;
	}

}
