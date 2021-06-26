package sk.stuba.fei.uim.upb.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import sk.stuba.fei.uim.upb.exceptions.CipherException;

public class AutomaticKeyPairGenerator {

	private final SecureRandom secureRandom;
	private final String algorithm;
	private final int keyLength;
	
	public AutomaticKeyPairGenerator(String algorithm, int keyLength) {
		this.algorithm = algorithm;
		this.keyLength = keyLength;
		this.secureRandom = new SecureRandom();
	}

	public KeyPair generateKeyPair() {
		
		KeyPair keyPair = null;
		
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
			keyGen.initialize(keyLength, secureRandom);
			keyPair = keyGen.generateKeyPair();			
		} catch (NoSuchAlgorithmException e) {
			throw new CipherException(e);
		}
		
		return keyPair;
	}
}
