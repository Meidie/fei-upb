package sk.stuba.fei.uim.upb;

import java.io.Console;

import sk.stuba.fei.uim.upb.crypto.AsymmetricCipher;
import sk.stuba.fei.uim.upb.crypto.SymmetricCipher;
import sk.stuba.fei.uim.upb.utils.FileOpener;
import sk.stuba.fei.uim.upb.utils.FileType;

public class ConsoleApplication {

	private final SymmetricCipher symmetricCipher;
	private final AsymmetricCipher asymmetricCipher;
	private final FileOpener fileOpener;
	private final Console console;

	public ConsoleApplication() {
		this.console = System.console();
		this.asymmetricCipher = new AsymmetricCipher();
		this.symmetricCipher = new SymmetricCipher(asymmetricCipher);
		this.fileOpener = new FileOpener();
	}

	public void run() {

		if (console == null) {
			System.err.println("Konzola nebola nájdená...\n");
			return;
		} else {
			System.out.println("Aplikácia bola úspešne spustená...\n");
		}

		symmetricCipher.encryptFile(fileOpener.open(FileType.PLAIN_FILE));

		System.out.println("\nPre dešifrovanie stlačte ENTER...");
		console.readLine();

		symmetricCipher.decryptFile(fileOpener.open(FileType.ENCRYPTED_FILE));
	}
}
