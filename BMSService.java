package BMS;

import java.rmi.RemoteException;

public interface BMSService extends java.rmi.Remote {
	//����鼮
	String add(String s1,String s2,String s3,String sex,String s4,String s5) throws RemoteException;
	//ɾ���鼮
	String delete(String s1) throws RemoteException;
	//��������鼮����
	String[] getKind() throws RemoteException;
	String addKind(String s1) throws RemoteException;
	//֧�ְ�ID��ѯ��������ģ����ѯ
	String[][] query(String s1,String s2) throws RemoteException;
	String[][] query(String s1) throws RemoteException;
	
	
	
}