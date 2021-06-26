package sk.stuba.fei.uim.upb.utils;

public enum FileType {

	PLAIN_FILE("Nešifrovaný súbor"),
	ENCRYPTED_FILE("Šifrovaný súbor", ".encrypted"),
	DECRYPTED_FILE("Dešifrovaný súbor", ".decrypted"),
	KEY("Kľúč", ".key");

	private final String description;
	private final String extension;
	
	FileType(String description, String extension) {
		this.description = description;
		this.extension = extension;
	}
	
	FileType(String description) {
		this(description, null);
	}

	public String getDescription() {
		return this.description;
	}
	
	public String getExtension() {
		return this.extension;
	}

	public boolean compareExtensions(String extension) {
		return this.extension == null ? true : this.extension.replace(".", "").equals(extension);
	}
}
