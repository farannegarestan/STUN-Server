package me.faran.stunserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import me.faran.stun.Constants;

public class STUNServer {

	public static void main(String[] args) {
		System.out.println("STUN server is starting ...");
		try {
			ServerSocket serverSocket = new ServerSocket(
					Constants.STUN_PORT);
			while (true) {
				Socket client = serverSocket.accept();
				RequestServerThread thread = new RequestServerThread(client);
				thread.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
