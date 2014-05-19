package me.faran.stunclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import me.faran.stun.Constants;
import me.faran.stun.STUNHeader;
import me.faran.stun.STUNRequest;
import me.faran.stun.STUNSucessResponse;
import me.faran.stun.Util;

public class STUNClient {
	
	private static RequestBuilder requestBuilder = new RequestBuilder();

	public static void main(String[] args) {
		STUNRequest request = requestBuilder.buildRequest(); 
		STUNHeader header = request.getRequestHeader();
		 
		byte[] message = request.convertToByteArray();
		
		try {
			// Make a request
			Socket socket = new Socket(args[0], Constants.STUN_PORT);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			out.write(message);
			out.flush();
			
			/* Get back the response header back */
			byte[] responseHeaderBuffer = new byte[Constants.HEADER_SIZE];
			in.readFully(responseHeaderBuffer, 0, Constants.HEADER_SIZE);
			STUNHeader responseHeader = new STUNHeader();
			responseHeader.readFromByteArray(responseHeaderBuffer);
			System.out.println(responseHeader.getMessageLength());
			
			/* Read response attributes */
			byte[] attrBuffer = new byte[responseHeader.getMessageLength()];
			in.readFully(attrBuffer, 0, attrBuffer.length);
			if (responseHeader.getMessageType() == 
					Constants.MESSAGE_TYPE_BINDING_RESPONSE_SUCCESS) {
				STUNSucessResponse succResponse = new STUNSucessResponse();
				succResponse.readFromByteArray(attrBuffer);
				
				System.out.println("My Public IP Address is "
								+ Util.formatIPAddress(succResponse.getClientAddress()) 
								+ " on port " + succResponse.getClientPort() );
			}		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
