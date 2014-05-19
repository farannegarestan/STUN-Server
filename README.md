STUN Server Implementation in Java
==================================

This project is an implementation of Session Traversal Utilities for NAT (STUN) that has been standardized in RFC 5389. STUN protocol is instrumental in applications that require point to point access between machines behind NAT. In its simplest form it uses an external server to find out the public IP address and port binding of the machines behind NAT. This implmenentaion is a Java STUN server and a test client that can connect and to the server and display discovered public IP and port binding.

Setup
-----
To build the project you need to have Apache Ant. To start the build use ant command in the root directory of the application. Upon successful build you will have two executable Jar files in dist directory for server and client. Run the server using:

	java -jar ./dist/stun-server.jar

Make sure server is run on a machine in the publi Internet with a real IP address. Then run the test client and it tries to connect to the server and acquire its public address.
	
	java -jar ./dist/stun-client.jar <server address>

