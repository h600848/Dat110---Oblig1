package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import no.hvl.dat110.TODO;

public class MessageConnection {
	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public MessageConnection(Socket socket) {
		try {
			this.socket = socket;
			outStream = new DataOutputStream(socket.getOutputStream());
			inStream = new DataInputStream(socket.getInputStream());
		} catch (IOException ex) {
			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {
		byte[] data;

		// TODO - START
		// encapsulate the data contained in the Message and write to the output stream
		data = MessageUtils.encapsulate(message);

		try {
			outStream.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Message receive() {
		Message message = null;
		byte[] data;

		// TODO - START
		// read a segment from the input stream and decapsulate data into a Message
		try {
			data = new byte[128];
			for (int i = 0; i < data.length; i++) {
				data[i] = inStream.readByte();
			}
			message = MessageUtils.decapsulate(data);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return message;
	}

	// close the connection by closing streams and the underlying socket
	public void close() {
		try {
			outStream.close();
			inStream.close();
			socket.close();
		} catch (IOException ex) {
			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}