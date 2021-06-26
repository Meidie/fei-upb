package sk.stuba.fei.uim.upb.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import sk.stuba.fei.uim.upb.exceptions.CipherException;

public class SecretKeyGenerator {

	private final SecureRandom secureRandom;
	private final String algorithm;

	public SecretKeyGenerator(String algorithm) {
		this.algorithm = algorithm;
		this.secureRandom = new SecureRandom();
	}

	public SecretKey generateSecretKey() {

		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
			keyGenerator.init(256, secureRandom);
			return keyGenerator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			throw new CipherException(e);
		}
	}
}
