package sk.stuba.fei.uim.upb.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import sk.stuba.fei.uim.upb.exceptions.CipherException;
import sk.stuba.fei.uim.upb.utils.FileType;
import sk.stuba.fei.uim.upb.utils.Timer;

public class SymmetricCipher {

	private static final int  IV_SIZE = 16;
	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES/CBC/PKCS5PADDING";

	private final Timer timer;
	private final SecretKey secretKey;
	private final IvParameterSpec initializationVector;

	public SymmetricCipher() {
		this.timer = new Timer();
		this.secretKey = new SecretKeyGenerator(ALGORITHM).generateSecretKey();
		this.initializationVector = new InitializationVectorGenerator(IV_SIZE).createInitializationVector();
	}

	public void encryptFile(File inputFile) {

		String fileName = FilenameUtils.removeExtension(inputFile.getAbsolutePath());
		String encryptedFileName = fileName + FileType.ENCRYPTED_FILE.getExtension();
		String keyFileName = fileName + FileType.KEY.getExtension();

		try (FileInputStream inputFileStream = new FileInputStream(inputFile);
				FileOutputStream keyFile = new FileOutputStream(keyFileName);
				FileOutputStream outputFile = new FileOutputStream(encryptedFileName)) {

			System.out.print("\nPrebieha šifrovanie...");

			keyFile.write(this.secretKey.getEncoded());
			Cipher cipher = createCipher(Cipher.ENCRYPT_MODE, this.secretKey);

			try (CipherOutputStream cipherOS = new CipherOutputStream(outputFile, cipher);) {
				timer.start();
				IOUtils.copy(inputFileStream, cipherOS);
				timer.stop();

				System.out.printf("%nŠifrovanie bolo dokončené (%s)...%n", timer.durationString());
				System.out.printf("%s '%s' bol vytvorený.%n", FileType.ENCRYPTED_FILE.getDescription(),
						encryptedFileName);
				System.out.printf("%s '%s' bol vytvorený.%n", FileType.KEY.getDescription(), keyFileName);
			}
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public void decryptFile(File inputFile, File keyFile) {

		String decryptedFileName = FilenameUtils.removeExtension(inputFile.getAbsolutePath())
				+ FileType.DECRYPTED_FILE.getExtension();

		try (FileInputStream inputFileStream = new FileInputStream(inputFile);
				FileInputStream keyIS = new FileInputStream(keyFile);
				FileOutputStream outputFile = new FileOutputStream(decryptedFileName)) {

			System.out.print("\nPrebieha dešifrovanie...");

			SecretKey key = new SecretKeySpec(IOUtils.toByteArray(keyIS), ALGORITHM);
			Cipher cipher = createCipher(Cipher.DECRYPT_MODE, key);

			try (CipherOutputStream cipherOS = new CipherOutputStream(outputFile, cipher)) {

				timer.start();
				IOUtils.copy(inputFileStream, cipherOS);
				timer.stop();

				System.out.printf("%nDešifrovanie bolo dokončené (%s)...%n", timer.durationString());
				System.out.printf("%s '%s' bol vytvorený.%n", FileType.DECRYPTED_FILE.getDescription(),
						decryptedFileName);
			}
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	private Cipher createCipher(int mode, SecretKey key) {

		try {
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(mode, key, this.initializationVector);
			return cipher;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException e) {
			throw new CipherException(e);
		}
	}
}
