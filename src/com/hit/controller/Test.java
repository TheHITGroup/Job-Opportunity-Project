package com.hit.controller;

import com.hit.json.UserJSON;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         
         UserJSON userJSON = new UserJSON();
         userJSON.setUserName("Ivan");
         userJSON.setPassword("trrrest");
         
         System.out.println(UserController.checkLogin(userJSON));
         
	}

}
