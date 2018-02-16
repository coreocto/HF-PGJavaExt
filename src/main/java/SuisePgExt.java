package org.coreocto.dev.hf.pgjavaext;

import org.postgresql.pljava.annotation.Function;

import static org.postgresql.pljava.annotation.Function.Effects.IMMUTABLE;
import static org.postgresql.pljava.annotation.Function.OnNullInput.RETURNS_NULL;

import java.security.MessageDigest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

class HmacMd5{
	private static final String HMAC_MD5_ALGORITHM = "HmacMD5";
	private static Mac cachedMac = null;

    public byte[] getHash(byte[] key, byte[] data) throws NoSuchAlgorithmException, InvalidKeyException {
		if (cachedMac==null){
			cachedMac = Mac.getInstance(HMAC_MD5_ALGORITHM);	
		}
		
		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_MD5_ALGORITHM);
		cachedMac.init(signingKey);
        return cachedMac.doFinal(data);
    }
}

public class SuisePgExt {
	
	private static final HmacMd5 keyedHashFunc = new HmacMd5();
	private static final Decoder b64Decoder = Base64.getDecoder();
	private static final Encoder b64Encoder = Base64.getEncoder();
	
	@Function(onNullInput=RETURNS_NULL, effects=IMMUTABLE)
	public static int H(String index, String searchToken) {
		
		if (index==null || index.isEmpty()){
			return 0;
		}else if (searchToken==null || searchToken.isEmpty()){
			return 0;
		}else{
			/* Decoder b64Decoder = Base64.getDecoder(); */
			byte[] indexBytes = b64Decoder.decode(index);
			
			if (indexBytes==null || indexBytes.length<32){
				return 0;
			}
			
			byte[] searchTokenBytes = b64Decoder.decode(searchToken);
			
			if (searchTokenBytes==null || searchTokenBytes.length<16){
				return 0;
			}
			
			byte[] randomBytes = new byte[16];
			byte[] hashBytes = new byte[16];
			
			//first 16 bytes are the hash value
            System.arraycopy(indexBytes, 0, hashBytes, 0, 16);
			
			//last 16 bytes are the random number
            System.arraycopy(indexBytes, 16, randomBytes, 0, 16);
			
			byte[] hashedTokenBytes = null;
			
			try{
				hashedTokenBytes = keyedHashFunc.getHash(searchTokenBytes, randomBytes);
			}catch(Exception ex){
				return 0;
			}

			if (Arrays.equals(hashBytes, hashedTokenBytes)) {
				return 1;
			}
			return 0;
		}
    }
	
	@Function(onNullInput=RETURNS_NULL, effects=IMMUTABLE)
	public static String getRandomBytes(String index){
		if (index==null || index.isEmpty()){
			return null;
		}else{
			byte[] indexBytes = b64Decoder.decode(index);
			byte[] randomBytes = new byte[16];
			//last 16 bytes are the random number
            System.arraycopy(indexBytes, 16, randomBytes, 0, 16);
			return b64Encoder.encodeToString(randomBytes);
		}
	}
	
	@Function(onNullInput=RETURNS_NULL, effects=IMMUTABLE)
	public static String getHashBytes(String index){
		if (index==null || index.isEmpty()){
			return null;
		}else{
			byte[] indexBytes = b64Decoder.decode(index);
			byte[] hashBytes = new byte[16];
			//first 16 bytes are the hash value
            System.arraycopy(indexBytes, 0, hashBytes, 0, 16);
			return b64Encoder.encodeToString(hashBytes);
		}
	}
}