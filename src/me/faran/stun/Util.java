package me.faran.stun;

import java.math.BigInteger;
import java.util.Random;

public class Util {
	private static Random rnd = new Random();
	
	public static BigInteger generateTrxId() {
		return new BigInteger(Constants.TRX_ID_SIZE - 1, rnd);
	}
	
	public static String formatIPAddress(byte[] addr) {
		StringBuilder buffer = new StringBuilder();
		for(int i=0; i<4; i++) {
			buffer.append(addr[i] & 0xFF).append(".");
		}
		return buffer.toString();
	}
}
