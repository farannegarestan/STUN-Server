package me.faran.stunserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;

import me.faran.stun.Constants;
import me.faran.stun.STUNHeader;
import me.faran.stun.STUNSucessResponse;
import me.faran.stun.Util;

public class RequestServerThread extends Thread {
	private Socket client;

	public RequestServerThread(Socket client) {
		super();
		this.client = client;
	}

	@Override
	public void run()  {
		try {
			// Create a STUN request from the socket
			DataInputStream in = new DataInputStream(client.getInputStream());
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			byte[] requesHeader = new byte[Constants.HEADER_SIZE];
			in.readFully(requesHeader, 0, Constants.HEADER_SIZE);

			STUNHeader header = new STUNHeader();
			header.readFromByteArray(requesHeader);

			int clientPort = client.getPort();
			byte[] address = null;
			if (client.getInetAddress() instanceof Inet4Address)
				address = ((Inet4Address) client.getInetAddress()).getAddress();
			
			System.out.println("Serving request from " + Util.formatIPAddress(address));
			
			STUNSucessResponse response = new STUNSucessResponse(clientPort,
					address);
			response.getRequestHeader().setTransactionId(header.getTransactionId());
			byte[] responseBytes = response.convertToByteArray();
			out.write(responseBytes);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
