package sk.stuba.fei.uim.upb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

import sk.stuba.fei.uim.upb.PasswordSecurityApp;
import sk.stuba.fei.uim.upb.exceptions.DictionaryException;

public class Dictionary {
	
	public static final List<String> DICTIONARY = new ArrayList<String>();
	
	public void setUpDictionary() {
				
		try (InputStream engStream = PasswordSecurityApp.class.getResourceAsStream("/dictionaries/eng_dictionary.txt");
				InputStream svkStream = PasswordSecurityApp.class.getResourceAsStream("/dictionaries/sk_dictionary.txt");
				BufferedReader engReader = new BufferedReader(new InputStreamReader(engStream));
				BufferedReader svkReader = new BufferedReader(new InputStreamReader(svkStream))) {

			readFile(engReader);
			readFile(svkReader);

		} catch (IOException e) {
			throw new DictionaryException(e);
		}
	}
	
	private void readFile(BufferedReader br) {
		try {
			String word;
			while ((word = br.readLine()) != null) {
				if(word.length() >= 8) {
					DICTIONARY.add(word);
				}
			}

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
