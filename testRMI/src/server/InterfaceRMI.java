package server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMI extends Remote {
	public String saludar(String nombre) throws RemoteException;
}
