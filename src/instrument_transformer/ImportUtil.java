package instrument_transformer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 导入的工具类
 * @author cuican
 *
 */
public class ImportUtil {

	public static String ImportITestData(String filePath){
		String result=Constant.FAILED;
		String[] s=filePath.split("\\.");
		String txt=s[s.length-1];
		if(!"txt".equals(txt.toLowerCase())){
			return "导入的文件必须是txt格式的，请重新选择。";
		}
		try {
            String encoding="utf-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int line=0;//行号
                while((lineTxt = bufferedReader.readLine()) != null){
                	lineTxt=lineTxt.trim();
                	String[] pairs=lineTxt.split(":");
                	String key=pairs[0];
                	String value=pairs[1];
                	System.out.println(key+"="+value);
                	if(line==0){
                		if(!"BH".equals(key)){
                			return "文件内容格式有误，请重新选择。";
                		}
                	}
                }
                read.close();
    }else{
        System.out.println("找不到指定的文件");
    }
    } catch (Exception e) {
        System.out.println("读取文件内容出错");
        e.printStackTrace();
    }
		return result;
	}
}
