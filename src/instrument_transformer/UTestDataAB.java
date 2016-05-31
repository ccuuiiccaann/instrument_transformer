package instrument_transformer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 * 电压测试，ab tab页
 * @author cuican
 *
 */
public class UTestDataAB {

	/**
	 * 获取ab tab页表格的初始数据
	 * @return DefaultTableModel对象，作为Jtable实例化的参数
	 */
	public static DefaultTableModel getUTestDataAB(){
		DefaultTableModel model=new DefaultTableModel();
		Vector<Vector<String>> data=new Vector<>();//table的数据
		Vector<String> column=new Vector<>();//table的列名
		column.add(0, "20");
		column.add(1, "50");
		column.add(2, "80");
		column.add(3, "100");
		column.add(4, "120");
		Connection conn=DBConnection.getInstance();
//		try {
//			Statement st=conn.createStatement();
//			String sql="select top "+Constant.LIMIT+" certificate_no,tester,test_date from u_base_info order by create_date desc ";
//			ResultSet rs=st.executeQuery(sql);
//			while (rs.next()) {
//				String certificate_no=rs.getString(1);
//				String tester=rs.getString(2);
//				String test_date=rs.getString(3);
//				Vector<String> row=new Vector<>();
//				row.add(0, certificate_no);
//				row.add(1, tester);
//				row.add(2, test_date);
//				data.add(row);
//			}
//			//model.setDataVector(data, column);
//			rs.close();
//			st.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		Vector<String> row0=new Vector<>();
		row0.add(0, "0");
		row0.add(1, "1");
		row0.add(2, "2");
		row0.add(3, "3");
		row0.add(4, "4");
		data.add(row0);
		Vector<String> row1=new Vector<>();
		row1.add(0, "0");
		row1.add(1, "1");
		row1.add(2, "2");
		row1.add(3, "3");
		row1.add(4, "4");
		data.add(row1);
		Vector<String> row2=new Vector<>();
		row2.add(0, "0");
		row2.add(1, "1");
		row2.add(2, "2");
		row2.add(3, "3");
		row2.add(4, "4");
		data.add(row2);
		Vector<String> row3=new Vector<>();
		row3.add(0, "0");
		row3.add(1, "1");
		row3.add(2, "2");
		row3.add(3, "3");
		row3.add(4, "4");
		data.add(row3);
		Vector<String> row4=new Vector<>();
		row4.add(0, "0");
		row4.add(1, "1");
		row4.add(2, "2");
		row4.add(3, "3");
		row4.add(4, "4");
//		data.add(row4);
		
		model.setDataVector(data, column);
		return model;
	}
}
