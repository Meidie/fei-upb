//////////////////////////////////////////////////////////////////////////
// TODO:                                                                //
// Uloha1: Do suboru s heslami ulozit aj sal.                           //
// Uloha2: Pouzit vytvorenu funkciu na hashovanie a ulozit heslo        //
//         v zahashovanom tvare.                                        //
//////////////////////////////////////////////////////////////////////////
package sk.stuba.fei.uim.upb.io;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import sk.stuba.fei.uim.upb.database.H2Database;
import sk.stuba.fei.uim.upb.database.MyResult;
import sk.stuba.fei.uim.upb.security.Security;

public class Registration {
	
	private H2Database database;
	private Security security;

	public Registration() {
		this.database = new H2Database();
		this.security = new Security();
	}

	public MyResult register(String username, String password) throws NoSuchAlgorithmException, Exception {

		MyResult result;
		
		//Zistenie ci sa pouzivatel uz nachadza v databaze
		if (database.createConnection().exist(username)) {
			result = new MyResult(false, "Meno je uz zabrate.");
		} else {

			// Vygenerovanie saltu
			byte[] salt = security.generatSalt();
			// Uloha2: Pouzitie funkcie na hashovanie a ziskanie hesla v zahashovanom tvare
			String hashedPassword = security.generateHashedPassword(password, salt);

			// Uloha1: Ulozenie noveho pouzivatela do databazy (uklada sa meno, hashovane heslo a salt)
			database.insert(username, hashedPassword, Base64.getEncoder().encodeToString(salt));
			result = new MyResult(true, "");
		}

		database.closeConnection();
		return result;
	}
}
