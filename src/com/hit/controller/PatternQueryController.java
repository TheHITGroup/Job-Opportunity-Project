package com.hit.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hit.json.TextJSON;

import Queries.PatternQueries;

public class PatternQueryController extends Controller
{
	public static TextJSON getPopLangInZip(String zip)
	{
		PatternQueries SQL = new PatternQueries();
		String sql = SQL.getSQLPopLangInZip();
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {zip, zip};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		PatternStringMaker sm = new PatternStringMaker();
		String resultString = sm.getStringPopTechInZip(resultSet, "language");
		
        TextJSON textJSON = new TextJSON();
        textJSON.setResult(resultString);
        
        return textJSON;
	}
	
	public static TextJSON getPopFWInZip(String zip)
	{
		PatternQueries SQL = new PatternQueries();
		String sql = SQL.getSQLPopFWInZip();
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {zip, zip};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		PatternStringMaker sm = new PatternStringMaker();
		String resultString = sm.getStringPopTechInZip(resultSet, "framework");
		
        TextJSON textJSON = new TextJSON();
        textJSON.setResult(resultString);
        
        return textJSON;
	}
	
	public static TextJSON getPopCityForTechInState(String state, String tech)
	{
		PatternQueries SQL = new PatternQueries();
		String sql = SQL.getSQLPopCityForTechInState();
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {state, tech, state, tech};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		PatternStringMaker sm = new PatternStringMaker();
		String resultString = sm.getStringPopLocationForTech(resultSet, "city");
		
        TextJSON textJSON = new TextJSON();
        textJSON.setResult(resultString);
        
        return textJSON;
	}
	
	public static TextJSON getPopStateForTech(String tech)
	{
		PatternQueries SQL = new PatternQueries();
		String sql = SQL.getSQLPopStateForTech();
		
		PreparedStatement preparedStatement = getPreparedStatement(sql);
		
		String[] values = {tech, tech};
		setPlaceholderValues(values, preparedStatement);
		
		ResultSet resultSet = getResultSet(preparedStatement);
		
		PatternStringMaker sm = new PatternStringMaker();
		String resultString = sm.getStringPopLocationForTech(resultSet, "state");
		
        TextJSON textJSON = new TextJSON();
        textJSON.setResult(resultString);
        
        return textJSON;
	}

}
