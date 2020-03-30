package BMS;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.rmi.RemoteException;
import java.sql.*;

public class BMSServiceImpl extends java.rmi.server.UnicastRemoteObject implements BMSService {
	private Connection con = null;
	String driver = "com.mysql.cj.jdbc.Driver";
	public BMSServiceImpl() throws RemoteException {
		super();
		
	}
	public String add(String s1, String s2, String s3, String sex, String s4, String s5) throws RemoteException {
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bms" 
			+ "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true","root", "root");
			if (!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			try {
				String sql = "INSERT INTO book(bookName,bookNo,bookAuthor,authorSex,bookKind,bookDesc) VALUES (?,?,?,?,?,?)";
				PreparedStatement presta = con.prepareStatement(sql);
				presta.setString(1, s1);
				presta.setString(2, s2);
				presta.setString(3, s3);
				presta.setString(4, sex);
				presta.setString(5, s4);
				presta.setString(6, s5);
				presta.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace(); // 数据库连接失败异常处理
		} catch (Exception e) {
			e.printStackTrace(); // TODO: handle exception
		} finally {
			System.out.println("成功为数据库增添书籍！！");
		}
		return "success";
	}
	
	public String[][] query(String s1, String s2) throws RemoteException {
		String[][] paras = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bms" 
			+ "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true","root", "root");
			if (!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			try {
				String sql = new String();
				sql = "select * from book where bookNo='" + s1 + "' and bookName like '%" + s2 + "%'";
				System.out.println(sql);
				PreparedStatement presta = con.prepareStatement(sql);
				ResultSet retsult = presta.executeQuery();
				
				int rowCount = 0;
				while (retsult.next()) {
					rowCount++;
				}
				retsult.beforeFirst();
				
				paras = new String[rowCount][6];
				int count = 0;
				while (retsult.next()) {
					for (int i = 0; i < 6; i++) {
						paras[count][i] = retsult.getString(i + 1);
					}
					count++;
				} // 显示数据
				retsult.close();
				presta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace(); // 数据库连接失败异常处理
		} catch (Exception e) {
			e.printStackTrace(); // TODO: handle exception
		} finally {
			System.out.println("成功获取数据库数据！！");
		}
		return paras;
	}
	
	public String[][] query(String s1) throws RemoteException {
		String[][] paras = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bms" 
			+ "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true","root", "root");
			if (!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			try {
				String sql = new String();
				sql = "select * from book where bookName like '%" + s1 + "%'";
				System.out.println(sql);
				PreparedStatement presta = con.prepareStatement(sql);
				ResultSet retsult = presta.executeQuery();
				
				int count=0;
				int rowCount = 0;
				while (retsult.next()) {
					rowCount++;
				} 
				retsult.beforeFirst();
				paras = new String[rowCount][6];
				
				while (retsult.next()) {
					for (int i = 0; i < 6; i++) {
						paras[count][i] = retsult.getString(i + 1);
					}
					count++;
				} // 显示数据
				retsult.close();
				presta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 3.ResultSet类，用来存放获取的结果集！！
			con.close();
		} catch (SQLException e) {
			e.printStackTrace(); // 数据库连接失败异常处理
		} catch (Exception e) {
			e.printStackTrace(); // TODO: handle exception
		} finally {
			System.out.println("成功获取数据库数据！！");
		}
		return paras;
	}

	public String delete(String s1) {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bms" 
			+ "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true","root", "root");
			if (!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			try {
				String sql = "delete from book where bookNo=?";
				PreparedStatement presta = con.prepareStatement(sql);
				presta.setString(1, s1);
				presta.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con.close();
		}catch (SQLException e) {
			e.printStackTrace(); // 数据库连接失败异常处理
		} catch (Exception e) {
			e.printStackTrace(); // TODO: handle exception
		} finally {
			System.out.println("成功删除数据库数据！！");
		}
		return "success";
	}

	public String[] getKind() throws RemoteException {
		String[] str = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bms" 
			+ "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true","root", "root");
			if (!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			
			try {
				String sql = new String();
				sql = "select * from kind";
				PreparedStatement presta = con.prepareStatement(sql);
				ResultSet retsult = presta.executeQuery();
				int count = 0;
				
				int rowCount = 0;
				while (retsult.next()) {
					rowCount++;
				} // 显示数据
				retsult.beforeFirst();
				str = new String[rowCount];
				
				while (retsult.next()) {
					str[count] = retsult.getString(1);
					count++;
				} // 显示数据
				retsult.close();
				presta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace(); // 数据库连接失败异常处理
		} catch (Exception e) {
			e.printStackTrace(); // TODO: handle exception
		} finally {
			System.out.println("数据库数据成功获取！！");
		}
		return str;
	}
	
	public String addKind(String s1) throws RemoteException {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bms" 
			+ "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true","root", "root");
			if (!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			try {
				String sql = "INSERT INTO kind(bookKind) VALUES (?)";
				PreparedStatement presta = con.prepareStatement(sql);
				presta.setString(1, s1);
				presta.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con.close();
		}  catch (SQLException e) {
			e.printStackTrace(); // 数据库连接失败异常处理
		} catch (Exception e) {
			e.printStackTrace(); // TODO: handle exception
		} finally {
			System.out.println("成功向数据库添加书籍分类！！");
		}
		return "success";
	}
}