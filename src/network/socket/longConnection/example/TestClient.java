package network.socket.longConnection.example;

import network.socket.longConnection.example.client.Client;

public class TestClient {
	
	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}
	
}
