package com.hit.controller;

import java.sql.SQLException;

import com.hit.json.UserJSON;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         
         UserJSON userJSON = new UserJSON();
         userJSON.setUserName("Ivan");
         userJSON.setPassword("test");
         
         System.out.println(UserController.checkLogin(userJSON));
         
         TechnologyController tc = new TechnologyController();
         
		 String result = tc.getMostPopularOfTwoTechnologies("Java", "C", "Somerville", "NJ");
		 System.out.println(result);
	}

}
