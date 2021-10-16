package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

public class Crypto {
	
	/**
	 * Funcion que hashea un texto en formato Sha256
	 * @param text
	 * @return La string en formato hexadecimal del texto hasheado
	 * @throws NoSuchAlgorithmException
	 */
	public String hashStringSha256(String text) {
		String hex = "";
	    MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			// Change this to UTF-16 if needed
		    md.update(text.getBytes(StandardCharsets.UTF_8));
		    byte[] digest = md.digest();

		    hex = String.format("%064x", new BigInteger(1, digest));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    
	    return hex;
	}
	
}