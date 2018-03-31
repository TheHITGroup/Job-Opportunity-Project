package com.hit.controller;
import java.util.List;

import com.hit.controller.ComplexQueryController;
import com.hit.controller.UserController;
import com.hit.json.LocationJSON;
import com.hit.json.TechnologyJSON;
import com.hit.json.UserJSON;
import com.hit.json.JobOpeningJSON;

public class Test {

	public static void main(String[] args) {
         
         UserJSON userJSON = new UserJSON();
         userJSON.setUserName("Ivan");
         userJSON.setPassword("test");
         
         System.out.println(UserController.checkLogin(userJSON));
         
         ComplexQueryController tc = new ComplexQueryController();
		 
		 TechnologyController tctrlr = new TechnologyController();
		 List<TechnologyJSON> Techs = tctrlr.getListOfTechnologies();
		 for(TechnologyJSON Tech : Techs) {
			 System.out.println(Tech.getName());
			 System.out.println(Tech.getId());
			 System.out.println(Tech.getType());
		 }
//		 
//		 LocationController lc = new LocationController();
//		 List<LocationJSON> Locs = lc.getListOfLocations();
//		 for(LocationJSON Loc : Locs) {
//			 System.out.println(Loc.getCity());
//			 System.out.println(Loc.getState());
//			 System.out.println(Loc.getZipcode());
//		 }
		 
		 OtherRequirementsController orc = new OtherRequirementsController();
		 String[] techs = {"Python", "Flask"};
		 int jobId = orc.addJobOpening( 90210, techs, 1);
		 //System.out.println(jobId);
		 
//		 List<JobOpeningJSON> UJJs = orc.getJobOpeningsForUser(1);
//		 int i = 1;
//		 for(JobOpeningJSON UJJ : UJJs) {
//			 System.out.println("------Job " + i++ + "------");
//			 System.out.println(UJJ.getCity());
//			 System.out.println(UJJ.getState());
//			 System.out.println(UJJ.getZipcode());
//			 for(String tech : UJJ.getTechs()) {
//				 System.out.println(tech);
//			 }
//		 }
         
		 String result = tc.getPopOfTwoTechsByZip("Java", "C++", "08876");
		 String result2 = tc.getPopOfTwoTechsByCityState("C++", "Java", "Somerville", "NJ");
		 String result3 = tc.getPopOfTwoCityStatesForTech("Java", "New York", "NY", "Newark", "NJ");
		 String result4 = tc.getPopFWForLangInCityState("Java", "Newark", "NJ");
		 String result5 = tc.getCityInStateAtLeastNJobsForTech("NY", "Java", "10");
		 System.out.println(result);
		 System.out.println(result2);
		 System.out.println(result3);
		 System.out.println(result4);
		 System.out.println(result5);
		 LocationController lc = new LocationController();
		 int result6 = lc.getMaxJobsForLangInCityByStateTech("NJ", "Java");
		 System.out.println(result6);
	}

}
