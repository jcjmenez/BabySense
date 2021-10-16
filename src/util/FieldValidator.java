package util;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.User;

public class FieldValidator {
	
	/**
	 * Funcion que comprueba si un email es valido
	 * @param email
	 * @return true: es valido, false: no es valido
	 */
	public boolean isEmailValid(String email) {
		if (email.length() <= 0) return false;
		final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
	}
	
	/**
	 * Funcion que comprueba si la longitud de una contraseña es mayor
	 * de 8 digitos, tiene numeros y mayusculas
	 * @param password
	 * @return true: cumple los requisitos, false: no cumple los requisitos
	 */
	public boolean isPasswordValid(String password) {
		if (password.length() <= 0) return false;
		String pattern= "^(?=.*[0-9]).{8,15}$";
		return password.matches(pattern);
	}
	
	/**
	 * Funcion que comprueba si un nombre de usuario no esta registrado previamente
	 * @param username
	 * @param users
	 * @return true: no ha sido registrado, false: ha sido registrado
	 */
	public boolean isUsernameValid(String username , HashMap<Integer, User> users) {
		if (username.length() <= 3) return false;
		for (User user : users.values()) {
			if (username.equals(user.getUsername())) return false;
		}
		return true;
	}


	
}
