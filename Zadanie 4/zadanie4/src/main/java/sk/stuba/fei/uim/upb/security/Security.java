//////////////////////////////////////////////////////////////////////////
// TODO:                                                                //
// Uloha1: Vytvorit funkciu na bezpecne generovanie saltu.              //
// Uloha2: Vytvorit funkciu na hashovanie.                              //
// Je vhodne vytvorit aj dalsie pomocne funkcie napr. na porovnavanie   //
// hesla ulozeneho v databaze so zadanym heslom.                        //
//////////////////////////////////////////////////////////////////////////
package sk.stuba.fei.uim.upb.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Security {

	private static final int ITERATIONS = 7000;
	
	public String generateHashedPassword(String password, byte[] salt) {
		try {
			// Uloha2: Funkciu na hashovanie 
			KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, 64 * 8);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(keySpec).getEncoded();
			return Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new SecurityException(e);
		}
	}

	// Uloha1: Funkciu na bezpecne generovanie saltu 
	public byte[] generatSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}

	// Funkcia na porovnavanie hesla ulozeneho v databaze so zadanym heslom  
	public boolean checkPasswords(String password, String dbPassword, String saltString) {
		byte[] salt = Base64.getDecoder().decode(saltString);
		byte[] hash = Base64.getDecoder().decode(dbPassword);

		try {
			KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, hash.length * 8);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] testHash = factory.generateSecret(keySpec).getEncoded();

			return Arrays.equals(hash, testHash);

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new SecurityException(e);
		}
	}
}
