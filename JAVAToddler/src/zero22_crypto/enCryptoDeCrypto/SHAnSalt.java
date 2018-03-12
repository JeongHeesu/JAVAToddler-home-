package zero22_crypto.enCryptoDeCrypto;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class SHAnSalt {
	
    public SHAnSalt() {}

    // alg = "MD5|SHA1|SHA2"
    public static String digest(String alg, byte input[]) throws NoSuchAlgorithmException {
    	// 알고리즘 패턴으로 생성.
        MessageDigest md = MessageDigest.getInstance(alg);
        // 주어진 데이터를 이용한 해싱 코드 산출 처리(해싱 코드 갱신)
        md.update(input);
        // digest() 호출로 계산된 해싱 코드 반환
        byte[] digestRtn = md.digest();
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<digestRtn.length; i++){
        	sb.append(Integer.toString((digestRtn[i]&0xff)+0x100,16).substring(1));
        }
        return sb.toString();
    }

    // 임의의 salt 생성
    public static byte[] createSalt() throws NoSuchAlgorithmException {
    	// 암호용으로 강화된 난수 생성 클래스. 암호화 알고리즘 패턴 설정으로 해당 암호화 알고리즘 대상의 난수 생성.
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[32];
        // 난수 생성 후 salt에 저장
        random.nextBytes(salt);
        return salt;
    }
    
    public static String digestSalt(String inputValue, byte[] salt) throws Exception {
        if(inputValue == null) {
            throw new Exception("암호화 할수 없습니다.");
        } else {
        	MessageDigest md = MessageDigest.getInstance("SHA1");
        	md.reset();
            md.update(salt);
            byte[] digestRtn = md.digest(inputValue.getBytes("UTF-8"));
            
            // 1000번이상 해싱작업을 반복하는것을 권장함.
            for(int i=0; i<1000; i++){
            	md.reset();
            	digestRtn = md.digest(digestRtn);
            }
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<digestRtn.length; i++){
            	sb.append(Integer.toString((digestRtn[i]&0xff)+0x100,16).substring(1));
            }
            return sb.toString();
        }
    }
    
    public static String byteArrayToString(byte[] input){
    	String stringValue = Arrays.toString(input);
    	return stringValue;
    }
    
    public static byte[] StringToByteArray(String input){
    	String[] strings = input.replace("[", "").replace("]", "").split(", ");
		byte result[] = new byte[strings.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Byte.parseByte(strings[i]);
		}
		return result;
    }
}
