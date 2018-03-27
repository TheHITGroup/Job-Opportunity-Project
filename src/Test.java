import com.hit.controller.ComplexQueryController;
import com.hit.controller.UserController;
import com.hit.json.UserJSON;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         
         UserJSON userJSON = new UserJSON();
         userJSON.setUserName("Ivan");
         userJSON.setPassword("test");
         
         System.out.println(UserController.checkLogin(userJSON));
         
         ComplexQueryController tc = new ComplexQueryController();
         
		 String result = tc.getMostPopularOfTwoTechnologiesByZip("Python", "C", "08876");
		 String result2 = tc.getMostPopularOfTwoTechnologiesByCityState("C++", "Java", "Somerville", "NJ");
		 String result3 = tc.getMostPopularOfTwoCityStatesForTechnology("Spring", "New York", "NY", "Newark", "NJ");
		 System.out.println(result);
		 System.out.println(result2);
		 System.out.println(result3);
	}

}
