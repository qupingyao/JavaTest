package templateEngine.freemarker;

import java.io.File;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class Test {

	public static final String filePath = "src/templateEngine/freemarker/view/";

	public static final String fileName = "index.ftl";

	public static void main(String[] args) {

		Configuration config = new Configuration();
		try {
			File file = new File(filePath);
			config.setDirectoryForTemplateLoading(file);
			Template t = config.getTemplate(fileName);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("content", "hello Tom");
			p.put("time", new Date());
			p.put("strList",Arrays.asList("A","B","C"));
			StringWriter writer = new StringWriter();
			t.process(p, writer);
			System.out.println(writer);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}