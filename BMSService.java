package BMS;

import java.rmi.RemoteException;

public interface BMSService extends java.rmi.Remote {
	//添加书籍
	String add(String s1,String s2,String s3,String sex,String s4,String s5) throws RemoteException;
	//删除书籍
	String delete(String s1) throws RemoteException;
	//添加与获得书籍分类
	String[] getKind() throws RemoteException;
	String addKind(String s1) throws RemoteException;
	//支持按ID查询和书名的模糊查询
	String[][] query(String s1,String s2) throws RemoteException;
	String[][] query(String s1) throws RemoteException;
	
	
	
}