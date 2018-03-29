package com.hit.controller;
import java.util.List;

import com.hit.controller.ComplexQueryController;
import com.hit.controller.UserController;
import com.hit.json.LocationJSON;
import com.hit.json.TechnologyJSON;
import com.hit.json.UserJSON;

public class Test {

	public static void main(String[] args) {
         
         UserJSON userJSON = new UserJSON();
         userJSON.setUserName("Ivan");
         userJSON.setPassword("test");
         
         System.out.println(UserController.checkLogin(userJSON));
         
         ComplexQueryController tc = new ComplexQueryController();
         
		 String result = tc.getMostPopularOfTwoTechnologiesByZip("JavaScript", "C", "08876");
		 String result2 = tc.getMostPopularOfTwoTechnologiesByCityState("C++", "Java", "Somerville", "NJ");
		 String result3 = tc.getMostPopularOfTwoCityStatesForTechnology("Spring", "New York", "NY", "Newark", "NJ");
		 String result4 = tc.getMostPopularFrameworkForLanguageInCityState("Java", "Newark", "NJ");
		 String result5 = tc.getCityInStateWithAtLeastNJobsForTechnology("NJ", "JavaScript", "10");
		 
		 TechnologyController tctrlr = new TechnologyController();
		 List<TechnologyJSON> Techs = tctrlr.getListOfTechnologies();
		 System.out.println(result);
		 System.out.println(result2);
		 System.out.println(result3);
		 System.out.println(result4);
		 System.out.println(result5);
		 for(TechnologyJSON Tech : Techs) {
			 System.out.println(Tech.getName());
			 System.out.println(Tech.getId());
			 System.out.println(Tech.getType());
		 }
		 
		 LocationController lc = new LocationController();
		 List<LocationJSON> Locs = lc.getListOfLocations();
		 for(LocationJSON Loc : Locs) {
			 System.out.println(Loc.getCity());
			 System.out.println(Loc.getState());
			 System.out.println(Loc.getZipcode());
		 }
	}

}
