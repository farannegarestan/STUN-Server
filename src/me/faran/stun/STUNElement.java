package me.faran.stun;

public interface STUNElement {
	
	/**
	 * This method converts the underlying object into a stream of bytes 
	 * suitable for transfer over the network.
	 */
	byte[] convertToByteArray();
	
	/**
	 * Reads a buffer of bytes ad converts it to the fields of 
	 * the underlying object
	 * @param buffer
	 */
	public void readFromByteArray(byte[] buffer);
}
