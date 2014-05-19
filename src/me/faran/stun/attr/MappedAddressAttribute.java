package me.faran.stun.attr;

import java.nio.ByteBuffer;

public class MappedAddressAttribute extends STUNAttribute{
	private byte padding = 0X00;
	private byte family;
	private int port;
	private int address;
	
	public byte getPadding() {
		return padding;
	}
	public void setPadding(byte padding) {
		this.padding = padding;
	}
	public byte getFamily() {
		return family;
	}
	public void setFamily(byte family) {
		this.family = family;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	@Override
	public byte[] convertToByteArray() {
		byte[] header = super.convertToByteArray();
		
		ByteBuffer buffer = ByteBuffer.allocate(header.length + 8);
		
		ByteBuffer paddingBuffer = ByteBuffer.allocate(1);
		paddingBuffer.put(this.padding);
		
		ByteBuffer familyBuffer = ByteBuffer.allocate(1);
		familyBuffer.put(this.family);
		
		ByteBuffer portBuffer = ByteBuffer.allocate(4);
		portBuffer.putInt(this.port);
		
		ByteBuffer addrBuffer = ByteBuffer.allocate(4);
		addrBuffer.putInt(this.address);
		
		buffer.put(header);
		buffer.put(paddingBuffer.array());
		buffer.put(familyBuffer.array());
		buffer.put(portBuffer.get(2)).put(portBuffer.get(3));
		buffer.put(addrBuffer.array());
		
		return buffer.array();
	}
	@Override
	public void readFromByteArray(byte[] buffer) {
		
	}
	@Override
	public int getAttributeSize() {
		return 12;
	}
	
	
	
}
