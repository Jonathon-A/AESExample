package aesexample;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESExample {

    private static final String Algorithm = "AES";
    private final byte[] keyValue;

    public static void main(String[] args) {
        try {
            AESExample aes = new AESExample("abcdefghijklmnop");//16//24//32 letters
            String encdata = aes.Encrypt("player password");
            System.out.println("Encrypted data: " + encdata);
            String decdata = aes.Decrypt(encdata);
            System.out.println("Decrypted data: " + decdata);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public AESExample(String key) {
        keyValue = key.getBytes();
    }

    public String Encrypt(String Data) throws Exception {
        Key key = GenerateKey();
        Cipher cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] EncyptedValueBytes = cipher.doFinal(Data.getBytes());
        String EncyptedValue = new BASE64Encoder().encode(EncyptedValueBytes);
        return EncyptedValue;
    }

    public String Decrypt(String EncyptedData) throws Exception {
        Key key = GenerateKey();
        Cipher cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] DecodedValue = new BASE64Decoder().decodeBuffer(EncyptedData);
        byte[] DecryptedValueBytes = cipher.doFinal(DecodedValue);
        String DecryptedValue = new String(DecryptedValueBytes);
        return DecryptedValue;
    }

    private Key GenerateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, Algorithm);
        return key;
    }

}
