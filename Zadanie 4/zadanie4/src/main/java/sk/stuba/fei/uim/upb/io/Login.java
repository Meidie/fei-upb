//////////////////////////////////////////////////////////////////////////
// TODO:                                                                //
// Uloha2: Upravte funkciu na prihlasovanie tak, aby porovnavala        //
//         heslo ulozene v databaze s heslom od uzivatela po            //
//         potrebnych upravach.                                         //
// Uloha3: Vlozte do prihlasovania nejaku formu oneskorenia.            //
//////////////////////////////////////////////////////////////////////////
package sk.stuba.fei.uim.upb.io;

import java.io.IOException;
import java.sql.ResultSet;

import sk.stuba.fei.uim.upb.database.H2Database;
import sk.stuba.fei.uim.upb.database.MyResult;
import sk.stuba.fei.uim.upb.security.Security;

public class Login {
		
	private H2Database database;
	private Security security;
	
	public Login() {
		this.database = new H2Database();
		this.security = new Security();
	}
	
    public MyResult signIn(String username, String password) throws IOException, Exception{

    	MyResult result;
    	
    	// Zistenie ci sa pouzivatel uz nachadza v databaze
    	if(!database.createConnection().exist(username)) {
    		result = new MyResult(false, "Nesprávne meno alebo heslo.");
    	} else {
    		ResultSet rs = database.select(username);
    		rs.next();
    		// Ziskanie hesla a slatu z databazy pre pouzivatela so zadanym menom
    		String dbPassword = rs.getString("password");
    		String dbSalt = rs.getString("salt");
    		
    		// Uloha2: Funkcia na prihlasovanie, ktora porovnava heslo ulozene v databaze s heslom od uzivatela
    		// Uloha3: Oneskorenie prihlasovania "ITERATIONS" urcuje mnozstvo iteracii, ktore sa maju vykonat pred poskytnutim vysledku
    		if(security.checkPasswords(password, dbPassword, dbSalt)) {
    			result = new MyResult(true, "Prihlásenie bolo úspešné.");
    		}else {
    			result = new MyResult(false, "Nesprávne meno alebo heslo.");
    		}
    	}
    	
    	database.closeConnection();
    	return result;
    }
}
