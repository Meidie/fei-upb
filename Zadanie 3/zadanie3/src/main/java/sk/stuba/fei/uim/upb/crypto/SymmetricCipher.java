package sk.stuba.fei.uim.upb.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import sk.stuba.fei.uim.upb.exceptions.CipherException;
import sk.stuba.fei.uim.upb.utils.FileType;
import sk.stuba.fei.uim.upb.utils.Timer;

public class SymmetricCipher {

	private static final int GCM_IV_LENGTH = 12;
	private static final int GCM_TAG_LENGTH_BITS = 128;
	private static final int SECRET_KEY_LENGTH_BITS = 256;
	private static final int RSA_SIZE = 256;
	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES/GCM/NoPadding";

	private final Timer timer;
	private final AsymmetricCipher asymmetricCipher;

	public SymmetricCipher(AsymmetricCipher asymmetricCipher) {
		this.timer = new Timer();
		this.asymmetricCipher = asymmetricCipher;
	}

	public void encryptFile(File inputFile) {

		// Generovanie Secret key 
		SecretKey secretKey = new SecretKeyGenerator(ALGORITHM, SECRET_KEY_LENGTH_BITS).generateSecretKey();
		
		// Generovanie IV a Tagu na kontrolu integrity 
		GCMParameterSpec paramSpec = new InitVectorAndAuthTagGenerator(GCM_IV_LENGTH,
				GCM_TAG_LENGTH_BITS).generateParamSpec();

		String fileName = FilenameUtils.removeExtension(inputFile.getAbsolutePath());
		String encryptedFileName = fileName + FileType.ENCRYPTED_FILE.getExtension();

		try (FileInputStream inputFileStream = new FileInputStream(inputFile);
				FileOutputStream outputFileStream = new FileOutputStream(encryptedFileName)) {

			System.out.print("\nPrebieha šifrovanie...");
			
			// Zapisanie asymetricky zasifrovaneho kluca do hlavicky
			byte[] encryptedSecretKey = asymmetricCipher.encryptSecretKey(secretKey);
			outputFileStream.write(encryptedSecretKey);
			// Zapisanie IV do hlavicky
			outputFileStream.write(paramSpec.getIV());

			// Sifrovanie
			writeCipher(Cipher.ENCRYPT_MODE, secretKey, encryptedSecretKey, paramSpec, inputFileStream, outputFileStream);

			System.out.printf("%nŠifrovanie bolo dokončené (%s)...%n", timer.durationString());
			System.out.printf("%s '%s' bol vytvorený.%n", FileType.ENCRYPTED_FILE.getDescription(), encryptedFileName);

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public void decryptFile(File inputFile) {

		String decryptedFileName = FilenameUtils.removeExtension(inputFile.getAbsolutePath())
				+ FileType.DECRYPTED_FILE.getExtension();

		try (FileInputStream inputFileStream = new FileInputStream(inputFile);
				FileOutputStream outputFileStream = new FileOutputStream(decryptedFileName)) {

			System.out.print("\nPrebieha dešifrovanie...");

			// Precitanie asymetricky zasifrovaneho kluca z hlavicky
			byte[] encryptedSecretKey = IOUtils.readFully(inputFileStream, RSA_SIZE);
			// Precitanie IV z hlavicky
			byte[] iv = IOUtils.readFully(inputFileStream, GCM_IV_LENGTH);

			// Desifrovanie kluca
			SecretKey decryptedSecretKey = asymmetricCipher.decryptAESKey(encryptedSecretKey);

			// Desifrovanie textu
			writeCipher(Cipher.DECRYPT_MODE, decryptedSecretKey, encryptedSecretKey, new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv),
					inputFileStream, outputFileStream);

			System.out.printf("%nDešifrovanie bolo dokončené (%s)...%n", timer.durationString());
			System.out.printf("%s '%s' bol vytvorený.%n", FileType.DECRYPTED_FILE.getDescription(), decryptedFileName);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	private void writeCipher(int mode, SecretKey key, byte[] encryptedKey, GCMParameterSpec param, FileInputStream inputFileStream,
			FileOutputStream outputFileStream) {

		try {
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(mode, key, param);	
			//Pridanie sifrovaneho kluca k Additional Associated Data, 
			//IV pridavat netreba pretoze je sucastou overovacieho tagu automaticky, vid GCMParameterSpec
			cipher.updateAAD(encryptedKey);
			timer.start();
			byte[] encryptedChunk = cipher.doFinal(IOUtils.toByteArray(inputFileStream));
			outputFileStream.write(encryptedChunk);
			timer.stop();
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | IOException | IllegalBlockSizeException
				| BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new CipherException(e);
		}
	}
}
