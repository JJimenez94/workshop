package co.edu.ucatolica.sd.imageTreatment.controler;

import java.rmi.Naming;
import co.edu.ucatolica.sd.imageTreatment.model.*;

public class Server {

	public Server() {
		try {
			System.out.println("Inicializando servidor");
			InterfaceRMIFilters i = new ImplRMIFilters();
			System.out.println("Interfaz implementada");
			java.rmi.registry.LocateRegistry.createRegistry(29000);
			System.out.println("Registro RMI creado");
			Naming.rebind("rmi://192.168.1.128:29000/FiltroRMI", i);
			System.out.println("Servidor iniciado correctamente");
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
