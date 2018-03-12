package zero22_crypto.enCryptoDeCrypto;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAEncryptoNDecrypto {
	// 공개키, 비밀키 생성 및 암호화 처리
	public static Map<String, Object> encryptoMethod(String target){
		Map<String, Object> encryptoValues = new HashMap<String, Object>();
		KeyPairGenerator clsKeyPairGenerator;
		String strCipher;
		try {
			// 공개키 및 비밀키 생성
			clsKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
			clsKeyPairGenerator.initialize(2048);
			
			KeyPair clsKeyPair = clsKeyPairGenerator.genKeyPair();
			Key clsPublicKey = clsKeyPair.getPublic();
			Key clsPrivateKey = clsKeyPair.getPrivate();
			
			KeyFactory fact = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec clsPublicKeySpec = fact.getKeySpec(clsPublicKey, RSAPublicKeySpec.class);
			RSAPrivateKeySpec clsPrivateKeySpec = fact.getKeySpec(clsPrivateKey, RSAPrivateKeySpec.class);

			System.out.println( "public key modulus(" + clsPublicKeySpec.getModulus( ) + ") "
					+ "exponent(" + clsPublicKeySpec.getPublicExponent( ) + ")" );
			System.out.println( "private key modulus(" + clsPrivateKeySpec.getModulus( ) + ") "
					+ "exponent(" + clsPrivateKeySpec.getPrivateExponent( ) + ")" );

			// 암호화(암호화 시 공개키 활용)
			Cipher clsCipher = Cipher.getInstance("RSA");
			clsCipher.init(Cipher.ENCRYPT_MODE, clsPublicKey );
			byte[] arrCipherData = clsCipher.doFinal(target.getBytes() );
			
			strCipher = new String( arrCipherData );
			System.out.println( "cipher(" + strCipher + ")" );
			
			encryptoValues.put("encryptoData", arrCipherData);
			encryptoValues.put("rsaPrivateKey", clsPrivateKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return encryptoValues;
	}
	
	// 복호화 처리
	public static void decryptoMethod(byte[] target, PrivateKey privateKey){
		Cipher clsCipher;
		try {
			clsCipher = Cipher.getInstance("RSA");
			clsCipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] arrData = clsCipher.doFinal(target);

			String strResult = new String(arrData);
			System.out.println("result(" + strResult + ")" );
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	    
	}
}
