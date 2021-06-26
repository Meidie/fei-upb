package sk.stuba.fei.uim.upb.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import sk.stuba.fei.uim.upb.exceptions.CipherException;

public class SecretKeyGenerator {

	private final SecureRandom secureRandom;
	private final String algorithm;
	private final int keyLength;

	public SecretKeyGenerator(String algorithm, int keyLength) {
		this.algorithm = algorithm;
		this.keyLength = keyLength;
		this.secureRandom = new SecureRandom();
	}

	public SecretKey generateSecretKey() {

		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
			keyGenerator.init(keyLength, secureRandom);
			return keyGenerator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			throw new CipherException(e);
		}
	}
}
