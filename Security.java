//////////////////////////////////////////////////////////////////////////
// TODO:                                                                //
// Uloha1: Vytvorit funkciu na bezpecne generovanie saltu.              //
// Uloha2: Vytvorit funkciu na hashovanie.                              //
// Je vhodne vytvorit aj dalsie pomocne funkcie napr. na porovnavanie   //
// hesla ulozeneho v databaze so zadanym heslom.                        //
//////////////////////////////////////////////////////////////////////////
package passwordsecurity2;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class Security {
    
    public static String hash(String password, byte[] salt) throws Exception{
        /*
        *   Pred samotnym hashovanim si najskor musite ulozit instanciu hashovacieho algoritmu.
        *   Hash sa uklada ako bitovy retazec, takze ho nasledne treba skonvertovat na String (napr. cez BigInteger);
        */
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-256");

        md.update(salt);

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashedPassword)
            sb.append(String.format("%02x", b));

        System.out.println(sb);

        return sb.toString();

    }
    
    public static byte[] getSalt() {
        /*
        *   Salt treba generovat cez secure funkciu.
        */
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;

    }
}

