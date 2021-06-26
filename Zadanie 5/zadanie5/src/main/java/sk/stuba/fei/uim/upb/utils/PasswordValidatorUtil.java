package sk.stuba.fei.uim.upb.utils;

import org.passay.DictionaryRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.passay.dictionary.ArrayWordList;
import org.passay.dictionary.WordListDictionary;
import org.passay.dictionary.sort.ArraysSort;

import sk.stuba.fei.uim.upb.database.MyResult;

public class PasswordValidatorUtil {
	
	public PasswordValidatorUtil() {
		new Dictionary().setUpDictionary();
	}
	
	public MyResult validate(String password) {
		
		MyResult result; 
		
		if (password.length() < 10) { //Dlzka aspon 8 znakov
			result = new MyResult(false, "Minimálna dĺžka hesla je 10 znakov");
		} else if (containsWhiteSpaceCharacter(password)) {
			result = new MyResult(false, "Heslo nesmie obsahovat medzery");
		} else if (!password.matches("^(.*[a-z].*)")) { //Aspon jedno male pismeno
			result = new MyResult(false, "Minimálny počet malých písmen je 1");
		} else if (!password.matches("^(.*[A-Z].*)")) {
			result = new MyResult(false, "Minimálny počet veľkých písmen je 1");
		} else if (!password.matches("^(.*[0-9].*)")) {
			result = new MyResult(false, "Minimálny počet číslic je 1");
		} else if (isDictionaryWord(password)) {
			result = new MyResult(false, "Použitie slovníkových slov je zakázané");
		} else {
			result = new MyResult(true, "");
		}
		
		return result;
	}
	
	private boolean isDictionaryWord(String password) {
		
		String[] words = Dictionary.DICTIONARY.toArray(new String[]{});
		new ArraysSort().sort(words);
		WordListDictionary wordListDictionary = new WordListDictionary(new ArrayWordList(words));
		DictionaryRule dictionaryRule = new DictionaryRule(wordListDictionary);
		PasswordData passwordData = new PasswordData(password);
		PasswordValidator passwordValidator = new PasswordValidator(dictionaryRule);
		RuleResult ruleResult = passwordValidator.validate(passwordData);
		
		return !ruleResult.isValid();
	}
	
	private boolean containsWhiteSpaceCharacter(String password) {
		
		  Rule rule = new WhitespaceRule();  
		  
	      PasswordData passwordData = new PasswordData(password);
	      PasswordValidator passwordValidator = new PasswordValidator(rule);    
	      RuleResult ruleResult = passwordValidator.validate(passwordData);
	      
	      return !ruleResult.isValid();
	}
}
