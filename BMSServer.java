package BMS;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class BMSServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
    	BMSServiceImpl bmsServerImpl = new BMSServiceImpl();
        LocateRegistry.createRegistry(8889);
        Naming.bind("rmi://localhost:8889/BMSService",bmsServerImpl);
        System.out.println("ComputingService is online.");
    }
}
