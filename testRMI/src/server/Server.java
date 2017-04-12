package server;
import java.rmi.Naming;

public class Server {

	public Server() {
		try {
			InterfaceRMI i = new ImplRMI();
			Naming.rebind("rmi://localhost:29000//saludar", i);
		} catch (Exception e) {
			System.out.println("Error en el servidor " + e.getMessage());
			System.out.println("El stack trace es: ");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Server();
	}

}
