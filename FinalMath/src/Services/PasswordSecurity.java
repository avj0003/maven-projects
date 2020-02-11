package Services;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Base64;
import java.security.NoSuchAlgorithmException;

public class PasswordSecurity {

    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";
    IvParameterSpec ivParameterSpec;
    SecretKeySpec skeySpec;
    Cipher cipher;

    public PasswordSecurity() throws NoSuchPaddingException, NoSuchAlgorithmException {
        // Create Key and Cipher
        ivParameterSpec = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
        skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }

    // Encryption
    public String encrypt(String plain) throws InvalidKeyException, InvalidAlgorithmParameterException {
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] encrypted = Base64.getEncoder().encode(plain.getBytes());
        return new String(encrypted);
    }

    // Decryption
    public String decrypt(String encrypted) throws InvalidKeyException, InvalidAlgorithmParameterException {
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] plain = Base64.getDecoder().decode(encrypted);
        return new String(plain);
    }
}
