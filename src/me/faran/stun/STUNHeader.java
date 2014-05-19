package me.faran.stun;

import java.math.BigInteger;
import java.nio.ByteBuffer;

public class STUNHeader implements STUNElement {
	private short messageType;
	private short messageLength;
	private int magicCookie = Constants.MAGIC_COOKIE;
	private BigInteger transactionId;
	
	public short getMessageType() {
		return messageType;
	}
	public void setMessageType(short messageType) {
		this.messageType = messageType;
	}
	public short getMessageLength() {
		return messageLength;
	}
	public void setMessageLength(short messageLength) {
		this.messageLength = messageLength;
	}
	public int getMagicCookie() {
		return magicCookie;
	}
	public void setMagicCookie(int magicCookie) {
		this.magicCookie = magicCookie;
	}
	public BigInteger getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(BigInteger transactionId) {
		this.transactionId = transactionId;
	}
	
	@Override
	public byte[] convertToByteArray() {
		ByteBuffer buffer = ByteBuffer.allocate(Constants.HEADER_SIZE);
		
		ByteBuffer messageTypeBuffer = ByteBuffer.allocate(2);
		messageTypeBuffer.putShort(this.messageType);
		
		ByteBuffer messageLengthBuffer = ByteBuffer.allocate(2);
		messageLengthBuffer.putShort(this.messageLength);
		
		ByteBuffer magicCookieBuffer = ByteBuffer.allocate(4);
		magicCookieBuffer.putInt(this.magicCookie);
		
		ByteBuffer trxIdBuffer = ByteBuffer.allocate(Constants.TRX_ID_SIZE / 8);
		trxIdBuffer.put(this.transactionId.toByteArray());
		
		buffer.put(messageTypeBuffer.array());
		buffer.put(messageLengthBuffer.array());
		buffer.put(magicCookieBuffer.array());
		buffer.put(trxIdBuffer.array());
		return buffer.array();
	}
	
	@Override
	public void readFromByteArray(byte[] buffer) {
		ByteBuffer messageTypeBuffer = ByteBuffer.allocate(2);
		messageTypeBuffer.put(buffer, 0, 2).rewind();
		this.messageType = messageTypeBuffer.getShort();
		
		ByteBuffer messageLengthBuffer = ByteBuffer.allocate(2);
		messageLengthBuffer.put(buffer, 2, 2).rewind();
		this.messageLength = messageLengthBuffer.getShort();
		
		ByteBuffer magicCookieBuffer = ByteBuffer.allocate(4);
		magicCookieBuffer.put(buffer, 4, 4).rewind();
		this.magicCookie  = magicCookieBuffer.getInt();
		
		ByteBuffer trxIdBuffer = ByteBuffer.allocate(Constants.TRX_ID_SIZE / 8);
		trxIdBuffer.put(buffer, 8, 12).rewind();
		this.transactionId = new BigInteger(trxIdBuffer.array());
	}
}
