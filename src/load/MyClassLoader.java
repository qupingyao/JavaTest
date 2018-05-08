package load;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

	private static final String filePathPre = "src/load/";

	private static final String fileExtName = ".class";

	public MyClassLoader() {
		super();
	}

	public MyClassLoader(ClassLoader parent) {
		super(parent);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String fileNamePre = name.substring(name.lastIndexOf(".") + 1);
		File file = this.getClassFile(fileNamePre);
		byte[] bytes = this.getClassBytes(file);
		Class<?> c = super.defineClass(name, bytes, 0, bytes.length);
		return c;
	}

	private File getClassFile(String name) {
		String fileName = String.format("%s%s%s", filePathPre, name, fileExtName);
		File file = new File(fileName);
		return file;
	}

	private byte[] getClassBytes(File file) {
		byte[] byteArr = null;
		InputStream in = null;
		InputStream brIn = null;
		ByteArrayOutputStream byteArrOut = null;
		try {
			int n = 0;
			in = new FileInputStream(file);
			brIn = new BufferedInputStream(in);
			byteArrOut = new ByteArrayOutputStream();
			while ((n = brIn.read()) != -1) {
				byteArrOut.write(n);
			}
			byteArr = byteArrOut.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (brIn != null) {
				try {
					brIn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (byteArrOut != null) {
				try {
					byteArrOut.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return byteArr;
	}
}
