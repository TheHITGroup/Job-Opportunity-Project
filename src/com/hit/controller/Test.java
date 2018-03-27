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
         
		 String result = tc.getMostPopularOfTwoTechnologiesByZip("Java", "C", "08876");
		 String result2 = tc.getMostPopularOfTwoTechnologiesByCityState("C++", "Java", "Somerville", "NJ");
		 String result3 = tc.getMostPopularOfTwoCityStatesForTechnology("Java", "Somerville", "NJ", "Newark", "NJ");
		 System.out.println(result);
		 System.out.println(result2);
		 System.out.println(result3);
	}

}
