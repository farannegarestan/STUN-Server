package me.faran.stun;

import java.nio.ByteBuffer;

import me.faran.stun.attr.MappedAddressAttribute;
import me.faran.stun.attr.STUNAttribute;

public class STUNSucessResponse extends STUNMessage {
	private int clientPort;
	private byte[] clientAddress;

	public STUNSucessResponse(int clientPort, byte[] clientAddress) {
		super();
		this.clientPort = clientPort;
		this.clientAddress = clientAddress;
		ByteBuffer addrBuffer = ByteBuffer.allocate(4);
		addrBuffer.put(this.clientAddress).rewind();
		
		/* Set success in the header */
		this.getRequestHeader().setMessageType(Constants.MESSAGE_TYPE_BINDING_RESPONSE_SUCCESS);
		
		/* Add attribute of public address and port */
		MappedAddressAttribute successResponse = new MappedAddressAttribute();
		successResponse.setAttributeType(STUNAttribute.MAPPED_ADDRESS);
		successResponse.setFamily(Constants.IPv4);
		successResponse.setPort(this.clientPort);
		successResponse.setAddress(addrBuffer.getInt());
		this.addAttribute(successResponse);
	}
	
	public STUNSucessResponse() {
		super();
	}

	@Override
	public byte[] convertToByteArray() {
		int attrSize = 0 ;
		for(STUNAttribute attr : this.getAttributes()) {
			attrSize += attr.getAttributeSize();
		}
		
		ByteBuffer buffer = ByteBuffer.allocate(Constants.HEADER_SIZE + attrSize);
		this.getRequestHeader().setMessageLength((short) attrSize);
		byte[] header = this.getRequestHeader().convertToByteArray();
		buffer.put(header);
		
		for(STUNAttribute attr : this.getAttributes()) {
			buffer.put(attr.convertToByteArray());
		}
		
		return buffer.array();
	}

	@Override
	public void readFromByteArray(byte[] buffer) {	
		ByteBuffer portBuffer = ByteBuffer.allocate(4);
		ByteBuffer addrBuffer = ByteBuffer.allocate(4);
		
		portBuffer.put(2, buffer[6]);
		portBuffer.put(3, buffer[7]);
		addrBuffer.put(buffer, 8, 4).rewind();
		
		this.clientPort = portBuffer.getInt();
		this.clientAddress = addrBuffer.array();
	}

	public int getClientPort() {
		return clientPort;
	}

	public byte[] getClientAddress() {
		return clientAddress;
	}
	
	

}
