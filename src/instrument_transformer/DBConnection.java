package instrument_transformer;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


/**
 * 数据库连接类。产生一个单例的数据库连接对象供程序使用。
 * 不用手动关闭此数据库连接。
 * 程序退出时关闭连接。防止频繁开启、关闭连接产生性能问题。
 * @author cuican
 */
public class DBConnection {
	private static Connection conn=null;
	
	/**
	 *  对外开放的获取数据库连接对象的方法。
	 * @return 数据库连接对象
	 */
	synchronized public static Connection getInstance(){
		if(conn==null){
			new DBConnection();
		}
		return conn;
	}
	
	
	/**
	 * 重写构造方法，并设为私有的。
	 */
	private DBConnection(){
		String path=System.getProperty("java.class.path");
		String mdbFilePath="";
		String[] s=path.split("\\\\");
		for (int i = 0; i < s.length-1; i++) {
			mdbFilePath+=s[i]+"\\";
		}
		mdbFilePath+="VITest.mdb";//数据库文件名不能更改。
//		System.out.println(mdbFilePath);
//		JOptionPane.showConfirmDialog(null, mdbFilePath);
//		mdbFilePath="C:\\Users\\cuican\\Desktop\\互感器校验程序\\VITest.mdb";//打包后需要注释掉，否则数据库文件路径不对。
		mdbFilePath="C:\\VITest.mdb";//打包后需要注释掉，否则数据库文件路径不对。
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
	        String url = "jdbc:Access:///"+mdbFilePath;
	        conn = DriverManager.getConnection(url);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "获取数据库连接失败。请确认"+path+"/VITest.mdb文件存在。");
		}
		
	}
	/* 
	 * 重写clone方法，防止产生新的实例
	 */
	@Override
	public Connection clone() {
		return getInstance();
	}
	public static void main(String[] args) {
//				try {
//					String path=System.getProperty("java.class.path");
//					Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
//		            String url = "jdbc:Access:///"+path+"//VITest.mdb";
//		            conn = DriverManager.getConnection(url);
////					String dbur1 = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=C://VITest.mdb";
////					String dbur1 = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb,*.accdb)};DBQ=C://VITest.mdb";
////					Connection conn = DriverManager.getConnection(dbur1);
//					Statement stmt = conn.createStatement();
//					ResultSet rs = stmt.executeQuery("select * from u_base_info");
//					while (rs.next()) {
//						System.out.println(rs.getString(1));
//					}
//					rs.close();
//					stmt.close();
//					conn.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				} 
		DBConnection.getInstance();
			}
			

}
