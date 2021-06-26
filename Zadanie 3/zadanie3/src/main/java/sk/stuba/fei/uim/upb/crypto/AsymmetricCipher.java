package sk.stuba.fei.uim.upb.crypto;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource.PSpecified;
import javax.crypto.spec.SecretKeySpec;

import sk.stuba.fei.uim.upb.exceptions.CipherException;

public class AsymmetricCipher {

	private static final int KEY_PAIR_LENGTH = 2048;
	private static final String ALGORITHM = "RSA";
	private static final String TRANSFORMATION_ENCRYPT = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
	private static final String TRANSFORMATION_DECRYPT = "RSA/ECB/OAEPPadding";

	private final PrivateKey privateKey;
	private final PublicKey publicKey;

	public AsymmetricCipher() {
		KeyPair keyPair = new AutomaticKeyPairGenerator(ALGORITHM, KEY_PAIR_LENGTH).generateKeyPair();
		this.privateKey = keyPair.getPrivate();
		this.publicKey = keyPair.getPublic();
	}

	public byte[] encryptSecretKey(SecretKey secretKey) {

		byte[] key = null;

		try {
			Cipher cipher = Cipher.getInstance(TRANSFORMATION_ENCRYPT);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			key = cipher.doFinal(secretKey.getEncoded());
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
				| InvalidKeyException e) {
			throw new CipherException(e);
		}

		return key;
	}

	public SecretKey decryptAESKey(byte[] encryptedKey) {

		SecretKey key = null;

		try {
			Cipher cipher = Cipher.getInstance(TRANSFORMATION_DECRYPT);
			OAEPParameterSpec oaepParams = new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-1"),
					PSpecified.DEFAULT);
			cipher.init(Cipher.DECRYPT_MODE, privateKey, oaepParams);
			key = new SecretKeySpec(cipher.doFinal(encryptedKey), "AES");
		} catch (Exception e) {
			throw new CipherException(e);
		}

		return key;
	}

}
