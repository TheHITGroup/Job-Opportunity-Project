package com.hit.controller;
import java.util.ArrayList;
import java.util.List;

import com.hit.controller.ComplexQueryController;
import com.hit.controller.UserController;
import com.hit.json.LocationJSON;
import com.hit.json.TechnologyJSON;
import com.hit.json.TextJSON;
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
		 
//		 OtherRequirementsController orc = new OtherRequirementsController();
//		 JobOpeningJSON JOJ = new JobOpeningJSON();
//		 List<String> techs = new ArrayList<>();
//		 techs.add("Go");
//		 techs.add("Beego");
//		 JOJ.setTechs(techs);
//		 JOJ.setUserId("2");
//		 JOJ.setZipcode("08876");
//		 String jobId = orc.addJobOpening(JOJ);
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
         
//		 String result = tc.getPopOfTwoTechsByZip("Java", "C++", "08876");
		 TextJSON tj1 = tc.getPopOfTwoTechsByCityState("C++", "Java", "Somerville", "NJ");
		 TextJSON tj5 = tc.getPopOfTwoTechsByZip("C++", "Java", "08876");
		 TextJSON tj2 = ComplexQueryController.getPopOfTwoCityStatesForTech("Java", "New York", "NY", "Newark", "NJ");
		 TextJSON tj3 = tc.getPopFWForLangInCityState("Java", "Somerville", "NJ");
		 TextJSON tj4 = tc.getCityInStateAtLeastNJobsForTech("NY", "Java", "10");
		 
		 PatternQueryController pc = new PatternQueryController();
		 
		 TextJSON pq1 = pc.getPopLangInZip("08876");
		 TextJSON pq2 = pc.getPopFWInZip("08876");
		 TextJSON pq3 = pc.getPopCityForTechInState("NY", "Python");
		 TextJSON pq4 = pc.getPopStateForTech("Java");
//		 System.out.println(result);
//		 System.out.println(result2);
//		 System.out.println(result3);
//		 System.out.println(result4);
//		 System.out.println(result5);
		 LocationController lc = new LocationController();
		 int result6 = lc.getMaxJobsForLangInCityByStateTech("NJ", "Java");
		 System.out.println(tj1.getResult());
		 System.out.println(tj4.getResult());
		 System.out.println(tj3.getResult());
		 System.out.println(tj5.getResult());
		 System.out.println(pq1.getResult());
		 System.out.println(pq2.getResult());
		 System.out.println(pq3.getResult());
		 System.out.println(pq4.getResult());
		 
		 UserJSON tom = new UserJSON();
		 tom.setId(1);
		 UserController uc = new UserController();
		 List<JobOpeningJSON> JOs = uc.deleteUser(tom);
		 for(JobOpeningJSON JO : JOs) {
			 System.out.println(JO.getCity() + ", " + JO.getState() + ", " + JO.getZipcode() + ", " + JO.getTechs().get(0));
		 }
	}

}
