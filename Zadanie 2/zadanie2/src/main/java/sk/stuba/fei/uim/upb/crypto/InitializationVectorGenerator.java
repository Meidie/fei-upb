package sk.stuba.fei.uim.upb.crypto;

import java.security.SecureRandom;

import javax.crypto.spec.IvParameterSpec;

public class InitializationVectorGenerator {
	
	private final int size;
	private final SecureRandom secureRandom;
	
	
	public InitializationVectorGenerator(int size) {
		this.size = size;
		this.secureRandom = new SecureRandom();
	}
	

	public IvParameterSpec createInitializationVector() {

		byte[] initializationVector = new byte[size];
		secureRandom.nextBytes(initializationVector);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
		return ivParameterSpec;
	}
}
