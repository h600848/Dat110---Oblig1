package no.hvl.dat110.messaging;

import no.hvl.dat110.TODO;

public class Message {
	// the up to 127 bytes of data (payload) that a message can hold
	private byte[] data;

	// construction a Message with the data provided
	public Message(byte[] data) {
		// TODO - START
		// Sjekker om data != null og ikke lengre enn 127 bytes.
		if (data != null && data.length <= 127)
			this.data = data;
	}

	public byte[] getData() {
		return this.data;
	}
}
