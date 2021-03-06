package load;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader {

	private final static String filePathPre = "src/load/";

	private final static String fileExtName = ".class";

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
		byte[] bytes = null;
		try {
			bytes = this.getClassBytes(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Class<?> c = super.defineClass(name, bytes, 0, bytes.length);
		return c;
	}

	private File getClassFile(String name) {
		String fileName = String.format("%s%s%s", filePathPre, name, fileExtName);
		File file = new File(fileName);
		return file;
	}

	private byte[] getClassBytes(File file) throws Exception {
		int n = 0;
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((n = br.read()) != -1) {
			bos.write(n);
		}
		br.close();
		return bos.toByteArray();
	}
}
