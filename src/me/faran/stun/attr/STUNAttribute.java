package me.faran.stun.attr;

import java.nio.ByteBuffer;

import me.faran.stun.STUNElement;

public abstract class STUNAttribute implements STUNElement {
	
	/* STADARD ATTRIBUTES */
	public static final short MAPPED_ADDRESS = 0x0001;
	
	private short attributeType;
	private short attributeLength;
	
	public short getAttributeType() {
		return attributeType;
	}
	public void setAttributeType(short attributeType) {
		this.attributeType = attributeType;
	}
	public short getAttributeLength() {
		return attributeLength;
	}
	public void setAttributeLength(short attributeLength) {
		this.attributeLength = attributeLength;
	}
	
	@Override
	public byte[] convertToByteArray() {
		ByteBuffer buffer = ByteBuffer.allocate(4);
		
		ByteBuffer attrTypeBuffer = ByteBuffer.allocate(2);
		attrTypeBuffer.putShort(this.attributeType);
		
		ByteBuffer attrLenBuffer = ByteBuffer.allocate(2);
		attrLenBuffer.putShort(this.attributeType);
		
		buffer.put(attrTypeBuffer.array());
		buffer.put(attrLenBuffer.array());
		return buffer.array();
	}
	
	public abstract int getAttributeSize() ;
}
