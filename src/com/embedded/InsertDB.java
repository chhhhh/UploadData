package com.embedded;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*将数据插入数据库*/
public class InsertDB {

	private static final String user = "root";
	private static final String pwd = "abc123";
	private static final String url = "jdbc:mysql://10.4.208.78:31089/embedded_cloud_platform?characterEncoding=utf8";
	private static final String driver = "com.mysql.jdbc.Driver";

	// 连接数据库
	public static Connection getCon() {

		Connection con = null;
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, user, pwd);
			if (con != null) {
				System.out.println("你已经连接到数据库：" + con.getCatalog());
			}
		} catch (Exception e) {
			System.out.println("连接数据库失败！");
			e.printStackTrace();
		}

		return con;
	}

	// 插入数据
	public boolean insertDB(String id, double[] data) {
		Connection con = null;
		Statement stm = null;
		boolean flag = false;
		/*
		 * String sql =
		 * "insert into sensor_data(sensor_id,data_type_id,`value`) values('" +
		 * id + "','" + 1 + "','" + data[0] + "')"; //浓度 String sql =
		 * "insert into sensor_data(sensor_id,data_type_id,`value`) values('" +
		 * id + "','" + 2 + "','" + data[1] + "')"; //光强 String sql =
		 * "insert into sensor_data(sensor_id,data_type_id,`value`) values('" +
		 * id + "','" + 3 + "','" + data[2] + "')"; //湿度 String sql =
		 * "insert into sensor_data(sensor_id,data_type_id,`value`) values('" +
		 * id + "','" + 4 + "','" + data[3] + "')"; //温度
		 */
		try {
			con = getCon();
			stm = con.createStatement();
			for (int j = 1; j <= 4; j++) {

				String sql = "insert into sensor_data(sensor_id,data_type_id,`value`) values('" + id + "','" + j + "','"
						+ data[j - 1] + "')";
				int i = stm.executeUpdate(sql);
				if (i > 0) {
					flag = true;
					System.out.println(flag + "插入数据成功！");
				}
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			close(null, stm, con);
		}
		return flag;
	}

	// 关闭连接
	public void close(ResultSet rs, Statement stm, Connection con) {

		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (stm != null)
			try {
				stm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
