package org.coreocto.dev.hf.pgjavaext;

import org.postgresql.pljava.annotation.Function;

import static org.postgresql.pljava.annotation.Function.Effects.IMMUTABLE;
import static org.postgresql.pljava.annotation.Function.OnNullInput.RETURNS_NULL;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Base64;
import java.util.Base64.Decoder;

public class SuisePgExt {
	
	@Function(onNullInput=RETURNS_NULL, effects=IMMUTABLE)
	public static String H(String searchToken, String pseudorandomVal) {
		
		Decoder b64Decoder = Base64.getDecoder();
		
		byte[] a = b64Decoder.decode(searchToken);
		byte[] b = b64Decoder.decode(pseudorandomVal);

		byte[] c = new byte[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);

		MessageDigest md = null;
		byte[] digest = null;
		try {
			md = MessageDigest.getInstance("MD5");
			digest = md.digest(c);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		if (digest == null) {
			return null;
		}

		return Base64.getEncoder().encodeToString(digest);
    }
	
	@Function(onNullInput=RETURNS_NULL, effects=IMMUTABLE)
	public static String R(int seed) {
		byte[] bytes = new byte[16];
        Random rnd = new Random(seed);
        rnd.nextBytes(bytes);
		return Base64.getEncoder().encodeToString(bytes);
    }
}