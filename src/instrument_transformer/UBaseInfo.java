package instrument_transformer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 * 电压，基本数据操作
 * @author cuican
 *
 */
public class UBaseInfo {

	/**
	 * 电压，从数据库查询左侧table的数据
	 * @return DefaultTableModel对象，作为Jtable实例化的参数
	 */
	public static DefaultTableModel getUTableData(){
		DefaultTableModel model=new DefaultTableModel();
		Vector<Vector<String>> data=new Vector<>();//table的数据
		Vector<String> column=new Vector<>();//table的列名
		column.add(0, "ID");//主键
		column.add(1, "证书编号");
		column.add(2, "测试日期");
		Connection conn=DBConnection.getInstance();
		try {
			Statement st=conn.createStatement();
			//access不能像mysql那样limit只支持类似于oracle的top
			String sql="select top "+Constant.LIMIT+" id,certificate_no,test_date from u_base_info order by test_date desc ";
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				String id=rs.getString(1);
				String certificate_no=rs.getString(2);
				String test_date=rs.getString(3);
				Vector<String> row=new Vector<>();
				row.add(0, id);
				row.add(1, certificate_no);
				row.add(2, test_date);
				data.add(row);
			}
			
			model.setDataVector(data, column);
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return model;
	}
	
	/**
	 * 查询电压测试的基本信息
	 * @param baseId
	 * @return HashMap,key为JTextField 变量名，value为该变量对应的值
	 */
	public static HashMap<String,String> getUBaseInfo(String baseId){
		HashMap<String,String> map=new HashMap<>();
		Connection conn=DBConnection.getInstance();
		try {
			Statement st=conn.createStatement();
			String sql="select *  from u_base_info where id='"+baseId+"'";
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				String huMing=rs.getString("name");
				String huiLuMingCheng=rs.getString("loop");
				String changMing_a=rs.getString("factory_name_a");
				String changMing_b=rs.getString("factory_name_b");
				String changMing_c=rs.getString("factory_name_c");
				String xingShi_a=rs.getString("model_a");
				String xingShi_b=rs.getString("model_b");
				String xingShi_c=rs.getString("model_c");
				String changHao_a=rs.getString("factory_no_a");
				String changHao_b=rs.getString("factory_no_b");
				String changHao_c=rs.getString("factory_no_c");
				String eDingRongLiang=rs.getString("volume");//额定容量
				String jiXing=rs.getString("polarity");//极性
				String bianBi=rs.getString("transformer_ratio");//变比
				String zhunQueDengJi=rs.getString("correctly_level");
				String chuChangNianYue=rs.getString("factory_date");
				String dianYa=rs.getString("rated_voltage");//额定电压（电压）
				String pinLv=rs.getString("frequency");//频率
				String juBianHao_a=rs.getString("no_a");
				String juBianHao_b=rs.getString("no_a");
				String juBianHao_c=rs.getString("no_a");
				map.put("huMing", huMing);
				map.put("huiLuMingCheng", huiLuMingCheng);
				map.put("changMing_a", changMing_a);
				map.put("changMing_b", changMing_b);
				map.put("changMing_c", changMing_c);
				map.put("xingShi_a", xingShi_a);
				map.put("xingShi_b", xingShi_b);
				map.put("xingShi_c", xingShi_c);
				map.put("changHao_a", changHao_a);
				map.put("changHao_b", changHao_b);
				map.put("changHao_c", changHao_c);
				map.put("eDingRongLiang", eDingRongLiang);
				map.put("jiXing", jiXing);
				map.put("bianBi", bianBi);
				map.put("zhunQueDengJi", zhunQueDengJi);
				map.put("chuChangNianYue", chuChangNianYue);
				map.put("dianYa", dianYa);
				map.put("pinLv", pinLv);
				map.put("juBianHao_a", juBianHao_a);
				map.put("juBianHao_b", juBianHao_b);
				map.put("juBianHao_c", juBianHao_c);
			}
			rs.close();
			st.close();
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
