package instrument_transformer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class ExportUtil {

	/**
	 * 导出电压数据
	 */
	public static void exportU(Long baseId) {
		try {
			String templatePath = System.getProperty("user.dir");
			File templete = new File(templatePath);
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			Map<String, String> dataMap =getData(baseId);
			configuration.setDirectoryForTemplateLoading(templete);// FTL文件所存在的位置
			Template t = null;
			t = configuration.getTemplate("Utemplate.ftl"); // 文件名
			File dir = new File("C:/测试报告");
			if (!dir.exists() && !dir.isDirectory()) {
				dir.mkdirs();
			}
			File outFile = new File(dir + "/test0001.doc");
			outFile.createNewFile();
			Writer out = null;
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
			t.process(dataMap, out);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private static Map<String,String> getData(Long baseId) {
		Map<String,String> map=new HashMap<>();
		map.put("ceShiJieLun", "标题");
		return map;
	}

}
