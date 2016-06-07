package instrument_transformer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class UBaseInfo {

	/**
	 * 电压主界面，左侧table的数据获取
	 * @return DefaultTableModel对象，作为Jtable实例化的参数
	 */
	public static DefaultTableModel getUTableData(){
		DefaultTableModel model=new DefaultTableModel();
		Vector<Vector<String>> data=new Vector<>();//table的数据
		Vector<String> column=new Vector<>();//table的列名
		column.add(0, "证书编号");//“证书编号”列
		column.add(1, "测试人员");//“测试人员”列
		column.add(2, "测试日期");//“测试日期”列
		Connection conn=DBConnection.getInstance();
		try {
			Statement st=conn.createStatement();
			//access不能像mysql那样limit只支持类似于oracle的top
			String sql="select top "+Constant.LIMIT+" certificate_no,tester,test_date from u_base_info order by create_date desc ";
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				String certificate_no=rs.getString(1);
				String tester=rs.getString(2);
				String test_date=rs.getString(3);
				Vector<String> row=new Vector<>();
				row.add(0, certificate_no);
				row.add(1, tester);
				row.add(2, test_date);
				data.add(row);
			}
			
//			Vector<String> row1=new Vector<>();
//			row1.add(0,"100001");
//			row1.add(1,"张三");
//			row1.add(2,"2016-01-01");
//			data.add(row1);
//			Vector<String> row2=new Vector<>();
//			row2.add(0,"100001");
//			row2.add(1,"小明");
//			row2.add(2,"2016-01-01");
//			data.add(row2);
//			Vector<String> row3=new Vector<>();
//			row3.add(0,"100001");
//			row3.add(1,"王五");
//			row3.add(2,"2016-01-01");
//			data.add(row3);
			model.setDataVector(data, column);
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return model;
	}
}
