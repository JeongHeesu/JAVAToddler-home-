package zero22_crypto;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Map;

import zero22_crypto.enCryptoDeCrypto.RSAEncryptoNDecrypto;
import zero22_crypto.enCryptoDeCrypto.SHAnSalt;

public class TestMain {

	public static void main(String[] args) {
		// salt 미사용
		try {
			System.out.println("MD5 : " + SHAnSalt.digest("MD5", "가나다".getBytes()));
			System.out.println("SHA1 : " + SHAnSalt.digest("SHA1", "가나다".getBytes()));
			System.out.println("SHA-256 : " + SHAnSalt.digest("SHA-256", "가나다".getBytes()));
			
			// salt의 활용. (한국정보보호협회의 권장사항 SHA1+salt의 조합)
			// 단방향 암호화 알고리즘을 이용한 회원의 패스워드 암호화를 위해 동일한 문자열이지만 salt값에의해 실제 암호화된
			// 값이 달라지므로 회원 패스워드 저장시 salt 패턴도 저장되도록 하여 인증시 사용함.
			byte[] salt1 = SHAnSalt.createSalt();
			System.out.println("SHA1+salt 임의 : " + SHAnSalt.digestSalt("가나다", SHAnSalt.createSalt()));
			
			// salt 값이 동일하면 동일한 데이타의 암호화 패턴이 작성되므로 랜덤한 salt를 저장하여 원데이타 여부를 파악할수 있음. 
			System.out.println("SHA1+salt 지정1: " + SHAnSalt.digestSalt("가나다", salt1));
			System.out.println("SHA1+salt 지정2: " + SHAnSalt.digestSalt("가나다", salt1));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// RSA 암호화/복호화
		Map<String, Object> result = RSAEncryptoNDecrypto.encryptoMethod("asdfasdf");
		RSAEncryptoNDecrypto.decryptoMethod((byte[])result.get("encryptoData"), 
				(PrivateKey)result.get("rsaPrivateKey"));
	}

}
