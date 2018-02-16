package org.coreocto.dev.hf.pgjavaext;

import org.postgresql.pljava.annotation.Function;

import static org.postgresql.pljava.annotation.Function.Effects.IMMUTABLE;
import static org.postgresql.pljava.annotation.Function.OnNullInput.RETURNS_NULL;

import java.util.BitSet;
import java.util.Base64;
import java.util.Base64.Decoder;

public class Chlh1PgExt {
	@Function(onNullInput=RETURNS_NULL, effects=IMMUTABLE)
	public static int Search(String dataBitSet, String queryBitSet) {
		
		Decoder b64Decoder = Base64.getDecoder();
		
		BitSet dBitSet = BitSet.valueOf(b64Decoder.decode(dataBitSet));
		BitSet qBitSet = BitSet.valueOf(b64Decoder.decode(queryBitSet));
		
		dBitSet.and(qBitSet);
		
		if (dBitSet.equals(qBitSet)){
			return 1;
		}else{
			return 0;
		}
    }
}