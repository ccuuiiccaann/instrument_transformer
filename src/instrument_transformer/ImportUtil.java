package instrument_transformer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 导入的工具类
 * 
 * @author cuican
 * 
 */
public class ImportUtil {

	/**
	 * 导入一组电流测试数据
	 * 
	 * @param filePath
	 * @return
	 */
	public static String ImportITestData(String filePath) {
		String result = Constant.FAILED;
		String[] s = filePath.split("\\.");
		Map<String, String> map = new HashMap<>();
		String txt = s[s.length - 1];
		if (!"txt".equals(txt.toLowerCase())) {
			return "导入的文件必须是txt格式的，请重新选择。";
		}
		try {
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				int line = 0;// 行号
				while ((lineTxt = bufferedReader.readLine()) != null) {
					lineTxt = lineTxt.trim();
					String[] pairs = lineTxt.split(":");
					String key = pairs[0];
					String value = pairs[1];
					System.out.println(key + "=" + value);
					if (line == 0) {
						if (!"BH".equals(key)) {
							return "文件内容格式有误，请重新选择。";
						}
					}
					map.put(key, value);//将导入的数据存入map

				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
			return "导入测试数据失败！请检查导入的文件与数据是否有误。";
		}
		if(map!=null){
			Map<String, String> baseInfoMap=new HashMap<>();
			/*
			 * String name=map.get("huMing");
			String loop=map.get("huiLuMingCheng");
			String factory_name_a=map.get("changMing_a");
			String factory_name_b=map.get("changMing_b");
			String factory_name_c=map.get("changMing_c");
			String model_a=map.get("xingShi_a");
			String model_b=map.get("xingShi_b");
			String model_c=map.get("xingShi_c");
			String factory_no_a=map.get("changHao_a");
			String factory_no_b=map.get("changHao_b");
			String factory_no_c=map.get("changHao_c");
			String volume=map.get("eDingRongLiang");//额定容量
			String polarity=map.get("jiXing");//极性
			String transformer_ratio=map.get("bianBi");//变比（变压比）
			String correctly_level=map.get("zhunQueDengJi");//准确等级
			String factory_date=map.get("chuChangNianYue");//出厂年月
			String rated_voltage=map.get("dianYa");//电压（额定电压）
			String frequency=map.get("pinLv");//频率
			String no_a=map.get("juBianHao_a");
			String no_b=map.get("juBianHao_b");
			String no_c=map.get("juBianHao_c");
			String certificate_no=map.get("zhengShuBianHao");//证书编号
			String tester=map.get("ceShiRen");
			String test_date=map.get("ceShiRiQi");
			String conclusion=map.get("ceShiJieLun");//测试结论
			 */
			baseInfoMap.put("huMing", map.get("name")+"");
			baseInfoMap.put("huiLuMingCheng", map.get("loop")+"");
			baseInfoMap.put("changMing_a", map.get("factory_name_a")+"");
			baseInfoMap.put("changMing_b", map.get("factory_name_b")+"");
			baseInfoMap.put("changMing_c", map.get("factory_name_c")+"");
			baseInfoMap.put("xingShi_a", map.get("model_a")+"");
			baseInfoMap.put("xingShi_b", map.get("model_b")+"");
			baseInfoMap.put("xingShi_c", map.get("model_c")+"");
			baseInfoMap.put("changHao_a", map.get("factory_no_a")+"");
			baseInfoMap.put("changHao_b", map.get("factory_no_b")+"");
			baseInfoMap.put("changHao_c", map.get("factory_no_c")+"");
			baseInfoMap.put("eDingRongLiang", map.get("volume")+"");
			baseInfoMap.put("jiXing", map.get("polarity")+"");
			baseInfoMap.put("bianBi", map.get("transformer_ratio")+"");
			baseInfoMap.put("zhunQueDengJi", map.get("DJ")+"");
			baseInfoMap.put("chuChangNianYue", map.get("factory_date")+"");
			baseInfoMap.put("pinLv", map.get("frequency")+"");
			baseInfoMap.put("juBianHao_a", map.get("no_a")+"");
			baseInfoMap.put("juBianHao_b", map.get("no_b")+"");
			baseInfoMap.put("juBianHao_c", map.get("no_c")+"");
			baseInfoMap.put("zhengShuBianHao", map.get("BH")+"");
			baseInfoMap.put("ceShiRen", map.get("tester")+"");
			baseInfoMap.put("ceShiRiQi", map.get("Time")+"");
			baseInfoMap.put("ceShiJieLun", map.get("conclusion")+"");
			String r=IBaseInfo.addIBaseInfo(baseInfoMap);
			if(Constant.SUCCESS.equals(r)){//插入基本数据和空的测试数据成功，更新空的测试数据
				
			}
		}
		if(Constant.FAILED.equals(result)){
			return "导入测试数据失败！请检查导入的文件与数据是否有误。";
		}else {
			return "导入成功。";
		}
	}
}
