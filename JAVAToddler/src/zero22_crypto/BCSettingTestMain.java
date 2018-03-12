package zero22_crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class BCSettingTestMain {
	 public static void main(String[] args) {

		 String providerName = "BC";
		 KeyGenerator keyGenerator = null;
		 if(Security.getProvider(providerName) == null)
			 System.out.println(providerName + " provider not installed");
		 else
			 System.out.println(providerName + " is installed");

		 // Blowfish는 블록 암호화 방식
		 System.out.println("Attepting to get a Blowfish key...");
		 try {
			 keyGenerator = KeyGenerator.getInstance("Blowfish");
		 } catch (NoSuchAlgorithmException e) {
		 }

		 keyGenerator.init(128);
		 SecretKey key = keyGenerator.generateKey();
		 System.out.println("OK");

		 //암호화 한다
		 System.out.println("Attempting to get a Cipher and encrypt...");
		 Cipher cipher = null;
		 byte[] ciphertext = null;
		 try {
		 	cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		 } catch (NoSuchAlgorithmException e) {} 
		 catch (NoSuchPaddingException e) {}

		 try {
			 cipher.init(Cipher.ENCRYPT_MODE, key);
		 } catch (InvalidKeyException e) {}

		 try {
			 ciphertext = cipher.doFinal("This is a test".getBytes("UTF8"));
		 } catch (IllegalBlockSizeException e) {} 
		 catch (BadPaddingException e) {} 
		 catch (UnsupportedEncodingException e) {}
		 
		 System.out.println("OK");
		 System.out.println("Test completed successfully : " + ciphertext);
	 }
}
