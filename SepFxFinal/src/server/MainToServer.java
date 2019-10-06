package server;

import java.net.MalformedURLException;
import java.nio.channels.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import server.sever.Server;
import shared.IServer;

public class MainToServer {
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException, java.rmi.AlreadyBoundException {
		
		IServer server = new Server();
		LocateRegistry.createRegistry(1099);
		Naming.bind("server", server);
		System.out.println("Server started...");
	}

}
