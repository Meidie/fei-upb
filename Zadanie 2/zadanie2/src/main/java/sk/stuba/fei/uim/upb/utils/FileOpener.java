package sk.stuba.fei.uim.upb.utils;

import java.io.Console;
import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class FileOpener {

	private final Console console;

	public FileOpener() {
		this.console = System.console();
	}

	public File open(FileType fileType) {

		String inputFilePath = console.readLine("Zadajte názov resp. absolútnu cestu k súboru (%s): ", fileType.getDescription());
		File inputFile = new File(inputFilePath);

		while (!inputFile.exists() || inputFile.isDirectory() || !isCorrectFileType(fileType, inputFile.getName())) {

			inputFilePath = console.readLine("Súbor sa nepodarilo nájsť, skúste zadať názov súboru ešte raz: ");
			inputFile = new File(inputFilePath);
		}

		return inputFile;
	}

	private boolean isCorrectFileType(FileType fileType, String fileName) {
		return fileType.compareExtensions(FilenameUtils.getExtension(fileName));

	}
}
