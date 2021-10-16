package util;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.User;

public class FieldValidator {
	
	public boolean isEmailValid(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	
	public boolean isPasswordValid(String password) {
		String pattern= "^(?=.*[0-9]).{8,15}$";
		return password.matches(pattern);
	}
	
	public boolean isUsernameValid(String username , HashMap<Integer, User> users) {
		for (User user : users.values()) {
			if (username.equals(user.getUsername())) return false;
		}
		return true;
	}


	
}
