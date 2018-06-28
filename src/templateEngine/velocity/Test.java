package templateEngine.velocity;

import java.io.StringWriter;
import java.util.Arrays;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class Test {

	public static final String filePath = "src/templateEngine/velocity/";

	public static final String fileName = "index.vm";

	public static void main(String[] args) {
		VelocityEngine engine = new VelocityEngine();
		engine.addProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, filePath);
		engine.init();
		Template t = engine.getTemplate(fileName);
		VelocityContext ctx = new VelocityContext();
		ctx.put("name", "tom");
		ctx.put("list", Arrays.asList("line1", "line2"));
		StringWriter writer = new StringWriter();
		t.merge(ctx, writer);
		System.out.println(writer);
	}

}
