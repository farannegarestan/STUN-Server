package me.faran.stunclient;
import me.faran.stun.Constants;
import me.faran.stun.STUNHeader;
import me.faran.stun.STUNRequest;
import me.faran.stun.Util;

/**
 * This class builds a STUN request to be sent to a stun server.
 * @author Faran
 *
 */
public class RequestBuilder {
	
	public STUNRequest buildRequest() {
		STUNRequest request = new STUNRequest();
		STUNHeader header = request.getRequestHeader();
		header.setMessageType(Constants.MESSAGE_TYPE_BINDING_REQUEST);
		header.setTransactionId(Util.generateTrxId());
		return request;
	}
}
