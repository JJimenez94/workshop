package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplRMI extends UnicastRemoteObject implements InterfaceRMI {

	public ImplRMI() throws RemoteException {
		super();
	}
	
	public String saludar(String nombre) throws RemoteException {
		return ("Hola " + nombre);
	}

}
