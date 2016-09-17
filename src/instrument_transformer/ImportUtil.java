package instrument_transformer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
					//如果是测试数据（以deviation开头的行），则:后面的都作为key的value
					if("deviation".equals(key)){
						value="";
						for (int i = 1; i < pairs.length; i++) {
							value+=pairs[i]+":";
						}
						value=value.substring(0, value.length()-1);//去除最后的:
					}
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
			//插入基本数据和空的测试数据
			Map<String, String> baseInfoMap=new HashMap<>();
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
			Connection conn=DBConnection.getInstance();
			try {
				conn.setAutoCommit(false);
				Statement st=conn.createStatement();
				//统计行数用来计算id
				String countSql="select max(id) as c from i_base_info ";
				ResultSet rs=st.executeQuery(countSql);
				Long count=0L;
				while (rs.next()){
					count=rs.getLong("c");
				}
				Long id=count+1;//计算id
				String create_date=DateUtil.getCurDate();//当前时间
				String name=map.get("huMing");
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
				
				if(certificate_no==null || "".equals(certificate_no)){
					return "证书编号不能为空！";
				}
				//校验证书编号是否存在
				String check_sql="select count(*) as total from u_base_info where certificate_no='"+certificate_no+"' ";
				ResultSet rs1=st.executeQuery(check_sql);
				Long total=-1L;
				while (rs1.next()){
					total=rs1.getLong("total");
				}
				if(total>0){
					return "证书编号已存在！";
				}
				
				String sql="insert into i_base_info "
							+ "(id,name,loop,"
							+ "factory_name_a,factory_name_b,factory_name_c,"
							+ "model_a,model_b,model_c,"
							+ "factory_no_a,factory_no_b,factory_no_c,"
							+ "volume,polarity,transformer_ratio,correctly_level,"
							+ "factory_date,rated_voltage,frequency,"
							+ "no_a,no_b,no_c,"
							+ "certificate_no,tester,test_date,conclusion,create_date) "
						+ "values"
							+ "("+id+",'"+name+"','"+loop+"',"
							+ "'"+factory_name_a+"','"+factory_name_b+"','"+factory_name_c+"',"
							+ "'"+model_a+"','"+model_b+"','"+model_c+"',"
							+ "'"+factory_no_a+"','"+factory_no_b+"','"+factory_no_c+"',"
							+ "'"+volume+"','"+polarity+"','"+transformer_ratio+"','"+correctly_level+"',"
							+ "'"+factory_date+"','"+rated_voltage+"','"+frequency+"',"
							+ "'"+no_a+"','"+no_b+"','"+no_c+"',"
							+ "'"+certificate_no+"','"+tester+"','"+test_date+"','"+conclusion+"','"+create_date+"'"
							+ ") ";
				System.out.println("新增电流基本信息：sql "+sql);
				st.executeUpdate(sql);
				//插入测试数据,一组基础数据对应12条测试数据。
				//12条数据是3个误差（ab，bc，ca）、满载+轻载（2种情况）、比差+角差（2种情况）的组合，3*2*2=12
				String[] deviation={Constant.TEST_DATA_A,Constant.TEST_DATA_B,Constant.TEST_DATA_C};//ab、bc、ca误差
				String[] sn_ln={Constant.SN,Constant.LN};//满载、轻载
				System.out.println("插入电压测试数据:");
				String value_20="";
				String value_50="";
				String value_80="";
				String value_100="";
				String value_120="";
				for (int i = 0; i < deviation.length; i++) {
					for (int j = 0; j < sn_ln.length; j++) {
						//比差
						String sql1="insert into i_test_data(id,base_id,deviation,rate_error,sn_ln,"
								+ "value_20,value_50,value_80,value_100,value_120) "
								+ "values "
								+ "('"+UUID.randomUUID().toString()+"',"+id+",'"+deviation[i]+"','"+Constant.RATE_OR_ANGLE+"','"+sn_ln[j]+"'),"
									+ " ";
						//角差
						String sql2="insert into i_test_data(id,base_id,deviation,angle_error,sn_ln, "
								+ "value_20,value_50,value_80,value_100,value_120) "
								+ "values "
								+ "('"+UUID.randomUUID().toString()+"',"+id+",'"+deviation[i]+"','"+Constant.RATE_OR_ANGLE+"','"+sn_ln[j]+"') "//比差
								+ " ";
						System.out.println(sql1);
						System.out.println(sql2);
						st.executeUpdate(sql1);
						st.executeUpdate(sql2);
						
					}
				}
				conn.commit();
				rs.close();
				st.close();
				return Constant.SUCCESS;
			} catch (SQLException e) {
				System.err.println("电流，新增出错，回滚！");
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.err.println("回滚出错！");
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally{
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					System.err.println("setAutoCommit出错！");
					e.printStackTrace();
				}
				
			}
		}
		if(Constant.FAILED.equals(result)){
			return "导入测试数据失败！请检查导入的文件与数据是否有误。";
		}else {
			return "导入成功。";
		}
	}
}
