package nio;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class TestBuff {
	
	public static void main(String[] args) {
		try{
			String str = "abcde";
			ByteBuffer buf = ByteBuffer.allocate(1024);
			System.out.println("---------------------");
			System.out.println(buf.position());
			System.out.println(buf.limit());
			System.out.println(buf.capacity());
			System.out.println("---------------------");
			buf.put(str.getBytes("UTF-8"));
			System.out.println(buf.position());
			System.out.println(buf.limit());
			System.out.println(buf.capacity());
			System.out.println("---------------------");
			buf.put(str.getBytes("UTF-8"));
			System.out.println(buf.position());
			System.out.println(buf.limit());
			System.out.println(buf.capacity());
			System.out.println("---------------------");
			buf.flip();
			System.out.println(buf.position());
			System.out.println(buf.limit());
			System.out.println(buf.capacity());
			System.out.println("---------------------");
			Charset charset = Charset.forName("UTF-8");
			System.out.println(charset.decode(buf));
			System.out.println(buf.position());
			System.out.println(buf.limit());
			System.out.println(buf.capacity());
			System.out.println("---------------------");
			buf.put(str.getBytes("UTF-8"));
			System.out.println(buf.position());
			System.out.println(buf.limit());
			System.out.println(buf.capacity());
			//3.利用flip()切换成读数据模式
//			buf.flip();
//			System.out.println("-----------flip()----------");
//			System.out.println(buf.position());
//			System.out.println(buf.limit());
//			System.out.println(buf.capacity()); //0 5 1024
			
			//4.利用get()读取缓冲区中的数据
//			byte[] dst = new byte[buf.limit()];
//			buf.get(dst);
//			System.out.println(new String(dst, 0, dst.length));
//			System.out.println("-----------get()----------");
//			System.out.println(buf.position());
//			System.out.println(buf.limit());
//			System.out.println(buf.capacity()); //5 5 1024
			
			//5.rewind()可重复读数据
//			buf.rewind();
//			System.out.println("-----------rewind()----------");
//			System.out.println(buf.position());
//			System.out.println(buf.limit());
//			System.out.println(buf.capacity()); //0 5 1024
			
			//6.清空缓冲区，但是缓冲区里面的数据依然存在，数据存在被遗忘状态
//			buf.clear();
//			System.out.println("-----------clear()----------");
//			System.out.println(buf.position());
//			System.out.println(buf.limit());
//			System.out.println(buf.capacity()); //0 1024 1024
			
//			System.out.println((char)buf.get());//a
			
			
			//--------------------------------------------------------------
//			String str2 = "abcde";
//			ByteBuffer buf2 = ByteBuffer.allocate(1024);
//			buf2.put(str2.getBytes());
			
//			buf2.flip();
			
//			byte[] dst2 = new byte[buf.limit()];
//			buf2.get(dst2, 0, 2);
			
//			System.out.println(new String(dst2, 0, 2));
//			System.out.println(buf2.position());//2
			
			//mark() 标记
			//buf2.mark();
			
//			buf2.get(dst2, 2, 2);
//			System.out.println(new String(dst2, 2, 2));
//			System.out.println(buf2.position());//4
			
			//reset()恢复到mark的位置
//			buf2.reset();
//			System.out.println(buf2.position());//2
			
			//--------------------------------------------------------------
			//分配直接缓冲区
//			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
//			System.out.println(buffer.isDirect()); //判断是否是直接缓冲区
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
