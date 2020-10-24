//////////////////////////////////////////////////////////////////////////
// TODO:                                                                //
// Uloha1: Do suboru s heslami ulozit aj salt.                           //
// Uloha2: Pouzit vytvorenu funkciu na hashovanie a ulozit heslo        //
//         v zahashovanom tvare.                                        //
//////////////////////////////////////////////////////////////////////////
package passwordsecurity2;

import java.security.NoSuchAlgorithmException;
import passwordsecurity2.Database.MyResult;


public class Registration {
    protected static MyResult registracia(String meno, String heslo) throws NoSuchAlgorithmException, Exception{
        if (passwordsecurity2.Database.exist("hesla.txt", meno)){
            System.out.println("Meno je uz zabrate.");
            return new MyResult(false, "Meno je uz zabrate.");
        }
        else {
            /*
            *   Salt sa obvykle uklada ako tretia polozka v tvare [meno]:[heslo]:[salt].
            */
            passwordsecurity2.Security security= new passwordsecurity2.Security();
            byte[] salt= security.getSalt();
           String hash = security.hash(heslo,salt);
           String salt_string= new sun.misc.BASE64Encoder().encode(salt);

            passwordsecurity2.Database.add("hesla.txt", meno + ":" + hash + ":" + salt_string);
        }
        return new MyResult(true, "");
    }
    
}
