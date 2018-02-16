package org.coreocto.dev.hf.pgjavaext;

import org.postgresql.pljava.annotation.Function;

import static org.postgresql.pljava.annotation.Function.Effects.IMMUTABLE;
import static org.postgresql.pljava.annotation.Function.OnNullInput.RETURNS_NULL;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.BitSet;

public class ChlhPgExt {
	
	@Function(onNullInput=RETURNS_NULL, effects=IMMUTABLE)
	public static int match(String data, String query){
		Decoder b64Decoder = Base64.getDecoder();
		BitSet dataBitSet = BitSet.valueOf(b64Decoder.decode(data));
		BitSet queryBitSet = BitSet.valueOf(b64Decoder.decode(query));
		dataBitSet.and(queryBitSet);
		if (dataBitSet.equals(queryBitSet)){
			return 1;
		}else{
			return 0;
		}
	}
	
}