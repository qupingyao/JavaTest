package network.socket.longConnection.example.client;

public class ClientHeartBeatAckDataProcesor implements ClientDataProcesor {

	private Client client;

	public ClientHeartBeatAckDataProcesor(Client client) {
		this.client = client;
	}

	@Override
	public void Process() {
		if (!client.isClose()) {
			client.setLastHeartBeatAckDataRevTime(System.currentTimeMillis());
		}
	}

}
