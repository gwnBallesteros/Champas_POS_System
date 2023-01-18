import com.mysql.cj.protocol.Message;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 Hashing
 */
public class Encryptor
{
    public static String encryptString(String input) throws NoSuchAlgorithmException
    {
        //MessageDigest works with MD2, MD5, SHA-1, SHA-224, SHA-256
        //SHA-384, SHA-512

        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDigest = md.digest(input.getBytes());

        BigInteger bigInt = new BigInteger(1, messageDigest);

        return bigInt.toString(16);
    }

}
