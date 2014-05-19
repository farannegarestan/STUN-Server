package me.faran.stun;

public class STUNRequest extends STUNMessage {
	
	@Override
	public byte[] convertToByteArray() {
		short messageLength = 0;
		this.getRequestHeader().setMessageLength(messageLength);
		return this.getRequestHeader().convertToByteArray();
	}
	
	@Override
	public void readFromByteArray(byte[] buffer) {
		
	}
}
