package me.faran.stun;

public class Constants {
	static public int STUN_PORT = 1111;
	
	/*Size of the Transaction ID field in bits */
	static public int TRX_ID_SIZE = 96;
	
	/* Size of the STUN request header in bytes */
	static public short HEADER_SIZE = 20;
	
	/* HEADER MESSAGE TYPE: Binding/Request  */
	static public short MESSAGE_TYPE_BINDING_REQUEST = 0X0001;
	
	/* HEADER MESSAGE TYPE: Binding/Response Success */
	static public short MESSAGE_TYPE_BINDING_RESPONSE_SUCCESS = 0X0101;
	
	/* HEADER MESSAGE TYPE: Binding/Response Error */
	static public short MESSAGE_TYPE_BINDING_RESPONSE_ERROR = 0X0111;
	
	/* Constant magic cookie in the header */
	static public int MAGIC_COOKIE = 0X2112A442 ;
	
	static public byte IPv4 = 0X01;
	static public byte IPv6 = 0X02;

}
