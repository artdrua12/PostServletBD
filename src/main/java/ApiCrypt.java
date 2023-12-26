import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

;

public class ApiCrypt {
    public static String createCrypt(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(message.getBytes(StandardCharsets.UTF_8));
            String myHash = DatatypeConverter.printHexBinary(messageDigest).toUpperCase();
            return myHash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 должен быть поддержан вашей Java Virtual Machine.", e);
        }
    }
}
