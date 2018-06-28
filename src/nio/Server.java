package nio;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {

	public static final String serverHost = "127.0.0.1";

	public static final int serverPort = 4444;

	public static void start() {
		Selector selector = null;
		ServerSocketChannel serverSocketChannel = null;
		try {
			selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			ServerSocket server = serverSocketChannel.socket();
			server.bind(new InetSocketAddress(serverHost, serverPort));
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			while (selector.select() > 0) {
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey skey = (SelectionKey) it.next();
					it.remove();
					if (skey.isAcceptable()) {
						SocketChannel socketChannel = serverSocketChannel.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);
					}
					if (skey.isReadable()) {
						ByteBuffer buff = ByteBuffer.allocate(1024);
						ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
						SocketChannel socketChannel = (SocketChannel) skey.channel();
						while (socketChannel.read(buff) > 0) {
							buff.flip();
							byteArrOut.write(buff.array());
							buff.clear();
						}
						System.out.println("get data from server:" + byteArrOut.toString("UTF-8"));
						ByteBuffer buffer = ByteBuffer.wrap(byteArrOut.toByteArray());
						socketChannel.write(buffer);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (serverSocketChannel != null) {
				try {
					serverSocketChannel.close();
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

	// public static void sendToClient(Selector selector, String content) throws
	// IOException {
	// for (SelectionKey key1 : selector.keys()) {
	// Channel targerChannel = key1.channel();
	// if (targerChannel instanceof SocketChannel) {
	// SocketChannel dest = (SocketChannel) targerChannel;
	// sendToClient(dest, content);
	// }
	// }
	// }
	//
	// public static void sendToClient(SocketChannel channel, String data)
	// throws IOException {
	// Charset charse = Charset.forName("UTF-8");
	// channel.write(charse.encode(data));
	// }

	// private static String receiverFromClient(SocketChannel channel,
	// ByteBuffer buffer) throws Exception {
	// String content = "";
	// channel.read(buffer);
	// Charset charse = Charset.forName("UTF-8");
	// CharBuffer cb = charse.decode((ByteBuffer) buffer.flip());
	// content = cb.toString();
	// buffer.clear();
	// return content;
	// }

}
