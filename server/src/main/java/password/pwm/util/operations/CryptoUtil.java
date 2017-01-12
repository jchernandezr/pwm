package password.pwm.util.operations;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * Encryption and Decryption of String data; PBE(Password Based Encryption and Decryption)
 * @author  Harald Strack Copyright (c) 2017 ssystems (hstrack@ssystems.de)
 *
 */
public class CryptoUtil 
{
    
    // 8-byte Salt
    static byte[] salt = {
        (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
        (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03,
    };
    
    // Iteration count
    static int iterationCount = 19;
    
    public CryptoUtil() 
    { 

    }

    /**
     * 
     * @param secretKey Key used to encrypt data
     * @param plainText Text input to be encrypted
     * @return Returns encrypted text
     * 
     */
    public static String encrypt(final String secretKey, final String plainText) 
            throws NoSuchAlgorithmException, 
            InvalidKeySpecException, 
            NoSuchPaddingException, 
            InvalidKeyException,
            InvalidAlgorithmParameterException, 
            UnsupportedEncodingException, 
            IllegalBlockSizeException, 
            BadPaddingException{
        //Key generation for enc and desc
        final KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
        final SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);        
         // Prepare the parameter to the ciphers
        final AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

        //Enc process
        final Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
        ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);      
        final String charSet="UTF-8";       
        final byte[] in = plainText.getBytes(charSet);
        final byte[] out = ecipher.doFinal(in);
        final String encStr=new sun.misc.BASE64Encoder().encode(out);
        return encStr;
    }
    
     /**     
     * @param secretKey Key used to decrypt data
     * @param encryptedText encrypted text input to decrypt
     * @return Returns plain text after decryption
     */
    public static String decrypt(final String secretKey, final String encryptedText)
     throws NoSuchAlgorithmException, 
            InvalidKeySpecException, 
            NoSuchPaddingException, 
            InvalidKeyException,
            InvalidAlgorithmParameterException, 
            UnsupportedEncodingException, 
            IllegalBlockSizeException, 
            BadPaddingException, 
            IOException{
         //Key generation for enc and desc
        final KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
        final SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);        
         // Prepare the parameter to the ciphers
        final AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
        //Decryption process; same key will be used for decr
        final Cipher dcipher=Cipher.getInstance(key.getAlgorithm());
        dcipher.init(Cipher.DECRYPT_MODE, key,paramSpec);
        final byte[] enc = new sun.misc.BASE64Decoder().decodeBuffer(encryptedText);
        final byte[] utf8 = dcipher.doFinal(enc);
        final String charSet="UTF-8";     
        final String plainStr = new String(utf8, charSet);
        return plainStr;
    }
    
    public static void main(final String[] args) throws Exception {
        final CryptoUtil cryptoUtil=new CryptoUtil();
        final String key="ezeon8547";   
        final String plain="This is an important message";
        final String enc=cryptoUtil.encrypt(key, plain);
        System.out.println("Original text: "+plain);
        System.out.println("Encrypted text: "+enc);
        final String plainAfter=cryptoUtil.decrypt(key, enc);
        System.out.println("Original text after decryption: "+plainAfter);
    }
}

