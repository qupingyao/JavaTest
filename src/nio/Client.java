package nio;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {

	public static void start() {
		Selector selector = null;
		SocketChannel channel = null;
		Scanner scan = new Scanner(System.in);
		try {
			selector = Selector.open();
			channel = SocketChannel.open();
			channel.connect(new InetSocketAddress(Server.serverHost, Server.serverPort));
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_READ);
			new ClientReadThread(selector).start();
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				ByteBuffer buffer = ByteBuffer.wrap(line.getBytes("UTF-8"));
				channel.write(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scan.close();
			if (channel != null) {
				try {
					channel.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (selector != null) {
				try {
					selector.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class ClientReadThread extends Thread {

		private Selector selector;

		public ClientReadThread(Selector selector) {
			this.selector = selector;
		}

		@Override
		public void run() {
			try {
				while (selector.select() > 0) {
					for (SelectionKey selectionKey : selector.selectedKeys()) {
						selector.selectedKeys().remove(selectionKey);
						if (selectionKey.isReadable()) {
							SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
							ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
							ByteBuffer buff = ByteBuffer.allocate(1024);
							while (socketChannel.read(buff) > 0) {
								buff.flip();
								byteArrOut.write(buff.array());
								buff.clear();
							}
							System.out.println("get data from server:" + byteArrOut.toString("UTF-8"));
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					selector.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
