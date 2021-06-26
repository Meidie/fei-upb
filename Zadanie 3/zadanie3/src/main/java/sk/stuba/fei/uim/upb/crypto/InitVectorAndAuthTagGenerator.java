package sk.stuba.fei.uim.upb.crypto;

import java.security.SecureRandom;

import javax.crypto.spec.GCMParameterSpec;

public class InitVectorAndAuthTagGenerator {
	
	private final int ivLength;
	private final int tagLength;
	private final SecureRandom secureRandom;
	
	
	public InitVectorAndAuthTagGenerator(int ivLength, int tagLength) {
		this.ivLength = ivLength;
		this.tagLength = tagLength;
		this.secureRandom = new SecureRandom();
	}

	public GCMParameterSpec generateParamSpec() {

		byte[] initializationVector = new byte[ivLength];
		secureRandom.nextBytes(initializationVector);
		GCMParameterSpec ivSpec = new GCMParameterSpec(tagLength, initializationVector);
		return ivSpec;
	}
}
