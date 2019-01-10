package jBlock;

import java.util.Date;
import com.google.gson.GsonBuilder;

public class Block {
	
	public String hash;
	public String previousHash; 
	private String data;
	private long timeStamp;
	private int nonce;

	public Block(String data, String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	public String calculateHash() {
		String calculatedhash = StringUtil.sha256( previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data );
		return calculatedhash;
	}
	
	public ResponseObject mineBlock(int difficulty) {
		String target = StringUtil.getDificultyString(difficulty); 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		return (new ResponseObject( hash , true ));
	}
	
}
