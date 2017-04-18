package co.edu.ucatolica.sd.imageTreatment.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import co.edu.ucatolica.sd.imageTreatment.model.InterfaceRMIFilters;
import co.edu.ucatolica.sd.imageTreatment.view.MainWindow;

public class Client {
	public InterfaceRMIFilters impl;
	public MainWindow mainWindow;

	public Client() throws MalformedURLException, RemoteException, NotBoundException {
		impl = (InterfaceRMIFilters) Naming.lookup("rmi://192.168.1.128:29000/FiltroRMI");
		mainWindow = new MainWindow();
		mainWindow.setClient(this);
	}

	public static void main(String[] args) {
		try {
			new Client();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
