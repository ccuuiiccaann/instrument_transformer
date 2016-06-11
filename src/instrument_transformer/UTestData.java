package instrument_transformer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 * 电压互感器的测试数据
 * @author cuican
 *
 */
public class UTestData {

	/**
	 * 获取ab tab页表格的初始数据
	 * @param baseId 基础信息id
	 * @param tab_abc 选中的是哪个测试数据（a、b or c）
	 * @return DefaultTableModel对象，作为Jtable实例化的参数
	 */
	public static DefaultTableModel getUTestDataAB(String baseId,String tab_abc){
		DefaultTableModel model=new DefaultTableModel();
		Vector<Vector<String>> data=new Vector<>();//table的数据
		Vector<String> column=new Vector<>();//table的列名
		column.add(0, "20");
		column.add(1, "50");
		column.add(2, "80");
		column.add(3, "100");
		column.add(4, "120");
		Vector<String> row1=new Vector<>();//满载-比差
		Vector<String> row2=new Vector<>();//满载-相位差
		Vector<String> row3=new Vector<>();//轻载-比差
		Vector<String> row4=new Vector<>();//轻载-相位差
		Connection conn=DBConnection.getInstance();
		try {
			Statement st=conn.createStatement();
			String sql="select * from u_test_data where base_id='"+baseId+"' and deviation='"+tab_abc+"' ";
			System.out.println(sql);
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
//				String deviation=rs.getString("deviation");//误差类型，是a、b、c
				String rate_error=rs.getString("rate_error");//比差
				String angle_error=rs.getString("angle_error");//相位差
				String sn_ln=rs.getString("sn_ln");//sn:满载，ln：轻载
				String value_20=rs.getString("value_20");
				String value_50=rs.getString("value_50");
				String value_80=rs.getString("value_80");
				String value_100=rs.getString("value_100");
				String value_120=rs.getString("value_120");
				if(Constant.SN.equals(sn_ln)){//取满载的数据
					if(Constant.RATE_OR_ANGLE.equals(rate_error)){//满载的比差
						row1.add(0,value_20);
						row1.add(1,value_50);
						row1.add(2,value_80);
						row1.add(3,value_100);
						row1.add(4,value_120);
					}else if(Constant.RATE_OR_ANGLE.equals(angle_error)){//满载的相位差
						row2.add(0,value_20);
						row2.add(1,value_50);
						row2.add(2,value_80);
						row2.add(3,value_100);
						row2.add(4,value_120);
					}
				}else if(Constant.LN.equals(sn_ln)){//取轻载的数据
					if(Constant.RATE_OR_ANGLE.equals(rate_error)){//轻载的比差
						row3.add(0,value_20);
						row3.add(1,value_50);
						row3.add(2,value_80);
						row3.add(3,value_100);
						row3.add(4,value_120);
					}else if(Constant.RATE_OR_ANGLE.equals(angle_error)){//轻载的相位差
						row4.add(0,value_20);
						row4.add(1,value_50);
						row4.add(2,value_80);
						row4.add(3,value_100);
						row4.add(4,value_120);
					}
				}
				
			}
			//model.setDataVector(data, column);
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		Vector<String> row0=new Vector<>();
//		row0.add(0, "0");
//		row0.add(1, "1");
//		row0.add(2, "2");
//		row0.add(3, "3");
//		row0.add(4, "4");
//		data.add(row0);
//		Vector<String> row1=new Vector<>();
//		row1.add(0, "0");
//		row1.add(1, "1");
//		row1.add(2, "2");
//		row1.add(3, "3");
//		row1.add(4, "4");
//		data.add(row1);
//		Vector<String> row2=new Vector<>();
//		row2.add(0, "0");
//		row2.add(1, "1");
//		row2.add(2, "2");
//		row2.add(3, "3");
//		row2.add(4, "4");
//		data.add(row2);
//		Vector<String> row3=new Vector<>();
//		row3.add(0, "0");
//		row3.add(1, "1");
//		row3.add(2, "2");
//		row3.add(3, "3");
//		row3.add(4, "4");
//		data.add(row3);
//		Vector<String> row4=new Vector<>();
//		row4.add(0, "0");
//		row4.add(1, "1");
//		row4.add(2, "2");
//		row4.add(3, "3");
//		row4.add(4, "4");
////		data.add(row4);
		data.add(row1);
		data.add(row2);
		data.add(row3);
		data.add(row4);
		model.setDataVector(data, column);
		return model;
	}
}
